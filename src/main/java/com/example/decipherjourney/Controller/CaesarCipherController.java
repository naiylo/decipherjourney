package com.example.decipherjourney.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import com.example.decipherjourney.Model.*;
import com.example.decipherjourney.Service.*;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CaesarcipherController is a controller that allows users to learn and train the ceasar cipher with different random generated examples.
 * 
 * Author: Oskar Schiedewitz
 */
@Controller
public class CaesarCipherController {

    /**
     * Attribute to connect the view to the user database
     */ 
    @Autowired
    UserService userService;

    /**
     * Attribute to safe the current cookie.
     */
    @Autowired
    CookieService cookieService;

    /**
     * Attribute to safe the current caesarcipher
     */
    @Autowired
    CaesarCipherService caesarCipherService;

    /**
     * Attribute to safe the current highscore
     */
    @Autowired
    HighscoreService highscoreService;

    /**
     * Function to load the caesar cipher view.
     * 
     * @param request   HTTP request made by a client.
     * @param model     The model to set attributes for the view.
     * 
     * @return The caesarcipher view.
     */
    @RequestMapping("/freeplay/caesarcipher")
    public String showCaesarCipher(HttpServletRequest request, Model model) {
        System.out.println("Switched to caesarcipher page.");
        
        // Check if user is logged in if not switch to login page where he can login or register

        if (!cookieService.isCookieSet(request, "user-cookie")) {
            System.out.println("Not logged in yet switch to login page.");
            return "redirect:/login";
        } else {
            try {
                User user = userService.getCurrentUser(request);
                System.out.println("Cookie was succesful.");

                // Check if user is currently working on a caesar cipher and load it if so

                if (user.getCaesarCipher() == null) {
                    System.out.println("Create new caesar cipher!");
                    CaesarCipher cipher = caesarCipherService.createRandomCaesarCipher();
                    userService.changeCaesarCipher(user.getUsername(), cipher);;
                    model.addAttribute("cipher", cipher);
                    model.addAttribute("mappingParams", cipher.getMap());
                } else {
                    CaesarCipher cipher = user.getCaesarCipher();
                    model.addAttribute("cipher", cipher);
                    model.addAttribute("mappingParams", cipher.getMap());
                }
                
    
            } catch (Exception e) {
                System.out.println("Cookie was not succesful.");
            }
        }

        if (!model.containsAttribute("spindegree")) {
            model.addAttribute("spindegree", 0);
        }

        model.addAttribute("highscore", userService.getCurrentUser(request).getHighscore().getCaesarHighscore());
        
        return "caesarcipher";
    }

    /**
     * Function to try out a shift for the displayed cipher text
     * 
     * @param shift                 The shift the user wants to try.
     * @param redirectAttributes    Object to redirect Attributes.
     * @param request               HTTP request made by a client.
     * @param model                 The model to set attributes for the view.
     * 
     * @return The updated caesarcipher view.
     */
    @RequestMapping("/freeplay/caesarcipher/shift")
    public String shiftCipher(@RequestParam("shift") String shiftValue,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request, 
                              Model model) {

        String shiftString = (shiftValue.isEmpty()) ? "0" : shiftValue;
        try {
            int shift = Integer.parseInt(shiftString);

            // Get the existing cipher from the model
            CaesarCipher cipher = userService.getCurrentUser(request).getCaesarCipher();

            // Use the shift value to generate a new ciphered text
            String shiftedText = caesarCipherService.decipherText(cipher.getCipheredText(), shift);

            redirectAttributes.addFlashAttribute("tryoutText", shiftedText);
            redirectAttributes.addFlashAttribute("shift", shift);

            // If correct update highscore and return feedback
            if (shiftedText.equals(cipher.getOriginalText())) {
                redirectAttributes.addFlashAttribute("successMessage", "Glückwunsch, deine Übersetzung ist korrekt!");
                Highscore currentHighscore = userService.getCurrentUser(request).getHighscore();
                userService.changeHighscore(userService.getCurrentUser(request).getUsername(), highscoreService.updateCaesarHighscore(currentHighscore, cipher.getErrorCounter(), cipher.getHints()));

            // If incorrect update error counter and reveal hints
            } else {
                userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseErrorCounter(cipher));
                Integer errors = cipher.getErrorCounter();
 
                // Give Feedback and hints and update the hints counter
                if (errors < 3) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider ist das nicht korrekt!");
                } else if (errors == 3) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, probiere Häufige Buchstaben zu vergleichen.");
                    userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher));
                } else if (errors == 4) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, guck dir kurze Wörter wie UND oder DAS an und suche nach der passenden Verschiebung.");
                    userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher));
                } else if (errors == 5) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, guck dir kurze Wörter wie UND oder DAS an und suche nach der passenden Verschiebung.");
                    userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher)); 
                } else if (errors == 6) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, guck dir kurze Wörter wie UND oder DAS an und suche nach der passenden Verschiebung.");
                    userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher));   
                } else if (errors == 7) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Der leichteste Weg ist alle 25 Möglichkeiten zu Testen. Darunter leidet aber dein Highscore!");
                    userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher));
                } else if (errors > 7){
                    redirectAttributes.addFlashAttribute("errorMessage2", "Der leichteste Weg ist alle 25 Möglichkeiten zu Testen. Darunter leidet aber dein Highscore!");
                }
            }
            redirectAttributes.addFlashAttribute("spindegree", 13.8461538462*shift);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Der Verschiebewert kann nur zwischen 0-25 liegen.");
        }

        return "redirect:/freeplay/caesarcipher";  
    }

    /**
     * Function to translate the cipher text with the input letters that are the supposed map of the cipher.
     * 
     * @param mappingParams         The Mapping of all inputs for the supposed map of the cipher.
     * @param redirectAttributes    Object to redirect Attributes.
     * @param request               The updated caesarcipher view.
     * @param model                 The model to set attributes for the view.
     * 
     * @return The updated caesarcipher view.
     */
    @RequestMapping("/freeplay/caesarcipher/decipherMapping")
    public String decipherWithMapping(@RequestParam Map<String, String> mappingParams,
                                      RedirectAttributes redirectAttributes,
                                      HttpServletRequest request,
                                      Model model) {

        // Create the map from the form inputs
        Map<String, String> cipherMap = new HashMap<>();
        // Pattern to check if a character is a valid uppercase letter A-Z
        Pattern pattern = Pattern.compile("[A-Z]");
        
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            String key = "map" + letter;
            String value = mappingParams.get(key);
            
            // Add to map if a mapping was provided for the letter
            if (value != null && !value.isEmpty()) {
                
                // Check if the value contains only one uppercase letter
                Matcher matcher = pattern.matcher(value.toUpperCase());
                if (value.length() == 1 && matcher.matches()) {
                    cipherMap.put(String.valueOf(letter), value.toUpperCase());
                } else {
                    // Throw an exception if the value is not a valid single letter
                    redirectAttributes.addFlashAttribute("errorMessage", "Die Buchstaben können nur A-Z sein.");
                }
            }
        }

        // Update the current supposed map
        CaesarCipher newCipher = userService.getCurrentUser(request).getCaesarCipher();
        newCipher.setMap(cipherMap);
        userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), newCipher);

        // Retrieve the current ciphered text from the user session
        CaesarCipher cipher = userService.getCurrentUser(request).getCaesarCipher();
        String cipheredText = cipher.getCipheredText();

        // Use the cipher map to decode the ciphered text
        String decipheredText = caesarCipherService.decipherSpecificLetter(cipherMap, cipheredText);

        // Redirect with the deciphered result
        redirectAttributes.addFlashAttribute("tryoutText", decipheredText);
        if (decipheredText.equals(cipher.getOriginalText())) {
            redirectAttributes.addFlashAttribute("successMessage", "Glückwunsch, deine Übersetzung ist korrekt!");
            Highscore currentHighscore = userService.getCurrentUser(request).getHighscore();
            userService.changeHighscore(userService.getCurrentUser(request).getUsername(), highscoreService.updateCaesarHighscore(currentHighscore, cipher.getErrorCounter(), cipher.getErrorCounter()));
        } else {
            userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseErrorCounter(cipher));
            System.out.println(cipher.getErrorCounter());
        }

        return "redirect:/freeplay/caesarcipher";
    }

    /**
     * Function to clear the supposed mapping of the cipher.
     * 
     * @param request HTTP request made by a client.
     *  
     * @return The updated caesarcipher view.
     */
    @RequestMapping("/freeplay/caesarcipher/clearMapping")
    public String clearMapping(HttpServletRequest request) {

        // Clear the current supposed map
        CaesarCipher newCipher = userService.getCurrentUser(request).getCaesarCipher();
        newCipher.setMap(new HashMap<>());
        userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), newCipher);

        return "redirect:/freeplay/caesarcipher";
    }

    /**
     * Function to reveal the original Text and load the congrats and disclaimer.
     * 
     * @param request               HTTP request made by a client.
     * @param redirectAttributes    Object to redirect Attributes.
     * 
     * @return The updated caesarcipher view.
     */
    @RequestMapping("/freeplay/caesarcipher/revealOriginal")
    public String revealOriginal(HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) {

        CaesarCipher newCipher = userService.getCurrentUser(request).getCaesarCipher();
        
        redirectAttributes.addFlashAttribute("tryoutText", newCipher.getOriginalText());
        redirectAttributes.addFlashAttribute("successMessage", "Hier siehst du den richtigen Text!");

        return "redirect:/freeplay/caesarcipher";
    }

    /**
     * Function to try out a new caesar cipher.
     * 
     * @param request  HTTP request made by a client.
     * 
     * @return The updated caesarcipher view with a new casear cipher.
     */
    @RequestMapping("/freeplay/caesarcipher/tryNew")
    public String tryNew(HttpServletRequest request,
                         RedirectAttributes redirectAttributes) {

        CaesarCipher newCipher = caesarCipherService.createRandomCaesarCipher();
        userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), newCipher);

        redirectAttributes.addFlashAttribute("successMessage", "Hier ist dein neuer verschlüsselter Text. Viel Erfolg!");

        return "redirect:/freeplay/caesarcipher";
    }

    /**
     * Function to submit your own solution
     *  
     * @param redirectAttributes    Object to redirect Attributes.
     * @param request               HTTP request made by a client.
     * @param tryOutText            The submitted text to compare to the solution.
     * 
     * @return The updated caesarcipher view.
     */
    @RequestMapping("/freeplay/caesarcipher/trySolution")
    public String trySolution(RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              @RequestParam("tryOutText") String tryOutText) {


        CaesarCipher cipher = userService.getCurrentUser(request).getCaesarCipher();

        if (cipher.getOriginalText().equals(tryOutText)) {
            redirectAttributes.addFlashAttribute("successMessage", "Glückwunsch, deine Übersetzung ist korrekt!");
            Highscore currentHighscore = userService.getCurrentUser(request).getHighscore();
            userService.changeHighscore(userService.getCurrentUser(request).getUsername(), highscoreService.updateCaesarHighscore(currentHighscore, cipher.getErrorCounter(), cipher.getHints()));

        } else {
            userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseErrorCounter(cipher));
            Integer errors = cipher.getErrorCounter();
            System.out.println(errors);
            System.out.println(cipher.getHints());

            // Give Feedback and hints and update the hints counter
            if (errors < 3) {
                redirectAttributes.addFlashAttribute("errorMessage2", "Leider ist das nicht korrekt!");
            } else if (errors == 3) {
                redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, probiere Häufige Buchstaben zu vergleichen.");
                userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher));
            } else if (errors == 4) {
                redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, guck dir kurze Wörter wie UND oder DAS an und suche nach der passenden Verschiebung.");
                userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher));
            } else if (errors == 5) {
                redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, guck dir kurze Wörter wie UND oder DAS an und suche nach der passenden Verschiebung.");
                userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher)); 
            } else if (errors == 6) {
                redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, guck dir kurze Wörter wie UND oder DAS an und suche nach der passenden Verschiebung.");
                userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher));   
            } else if (errors == 7) {
                redirectAttributes.addFlashAttribute("errorMessage2", "Der leichteste Weg ist alle 25 Möglichkeiten zu Testen. Darunter leidet aber dein Highscore!");
                userService.changeCaesarCipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher));
            } else if (errors > 7){
                redirectAttributes.addFlashAttribute("errorMessage2", "Der leichteste Weg ist alle 25 Möglichkeiten zu Testen. Darunter leidet aber dein Highscore!");
            }
        }          

        return "redirect:/freeplay/caesarcipher";
    }
    
}
