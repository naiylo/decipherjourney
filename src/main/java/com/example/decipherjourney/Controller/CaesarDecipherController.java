package com.example.decipherjourney.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import com.example.decipherjourney.Model.*;
import com.example.decipherjourney.Service.*;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CaesardecipherController is a controller that allows users to learn and train the ceasar cipher with different random generated examples.
 * 
 * Author: Oskar Schiedewitz
 */
@Controller
public class CaesarDecipherController {

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
    @RequestMapping("/freeplay/caesardecipher")
    public String showCaesarDecipher(HttpServletRequest request, Model model) {
        System.out.println("Switched to caesardecipher page.");
        
        // Check if user is logged in if not switch to login page where he can login or register

        if (!cookieService.isCookieSet(request, "user-cookie")) {
            System.out.println("Not logged in yet switch to login page.");
            return "redirect:/login";
        } else {
            try {
                User user = userService.getCurrentUser(request);
                System.out.println("Cookie was succesful.");

                // Check if user is currently working on a caesar cipher and load it if so

                if (user.getCaesarDecipher() == null) {
                    System.out.println("Create new caesar decipher!");
                    CaesarCipher cipher = caesarCipherService.createRandomCaesarDecipher();
                    userService.changeCaesarDecipher(user.getUsername(), cipher);
                    model.addAttribute("cipher", cipher);
                    model.addAttribute("mappingParams", cipher.getMap());
                    model.addAttribute("shift", cipher.getShiftNumber());
                } else {
                    CaesarCipher cipher = user.getCaesarDecipher();
                    model.addAttribute("cipher", cipher);
                    model.addAttribute("mappingParams", cipher.getMap());
                    model.addAttribute("shift", cipher.getShiftNumber());
                }
                
    
            } catch (Exception e) {
                System.out.println("Cookie was not succesful.");
            }
        }

        if (!model.containsAttribute("spindegree")) {
            model.addAttribute("spindegree", 0);
        }

        model.addAttribute("highscore", userService.getCurrentUser(request).getHighscore().getCaesarDecipherHighscore());
        
        return "caesardecipher";
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
    @RequestMapping("/freeplay/caesardecipher/shift")
    public String shiftCipher(@RequestParam("shift") String shiftValue,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request, 
                              Model model) {
                         
        String shiftString = (shiftValue.isEmpty()) ? "0" : shiftValue;
        try {
            int shift = Integer.parseInt(shiftString);

            // Get the existing cipher from the model
            CaesarCipher cipher = userService.getCurrentUser(request).getCaesarDecipher();

            // Use the shift value to generate a new ciphered text
            String shiftedText = caesarCipherService.cipherText(cipher.getCipheredText(), shift);

            redirectAttributes.addFlashAttribute("tryoutText", shiftedText);
            redirectAttributes.addFlashAttribute("shift", shift);

           // If correct update highscore and return feedback
            if (shiftedText.equals(cipher.getOriginalText())) {
                redirectAttributes.addFlashAttribute("successMessage", "Glückwunsch, deine Verschlüsselung ist korrekt!");
                Highscore currentHighscore = userService.getCurrentUser(request).getHighscore();
                userService.changeHighscore(userService.getCurrentUser(request).getUsername(), highscoreService.updateCaesarDecipherHighscore(currentHighscore, 100, 0));
            
            // If incorrect update error counter and reveal hints
            } else {
                userService.changeCaesarDecipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseErrorCounter(cipher));
                Integer errors = cipher.getErrorCounter();
                System.out.println(errors); 

                // Give Feedback and hints and update the hints counter
                if (errors < 3) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider ist das nicht korrekt!");
                } else if (errors == 3) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, Nutze die Disk und verschiebe sie.");
                    userService.changeCaesarDecipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher));
                } else if (errors == 4) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, Nutze die Disk und verschiebe sie um die Zahl im Uhrzeigersinn.");
                    userService.changeCaesarDecipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher));
                } else if (errors >= 5) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, probiere den Shift aus das reduziert deine Punkte aber gibt dir eine Vorstellung für das nächste mal.");
                    userService.changeCaesarDecipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher)); 
                } 
            }
            
            redirectAttributes.addFlashAttribute("spindegree", 360-13.8461538462*shift);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Der Verschiebewert kann nur zwischen 0-25 liegen.");
        }

        return "redirect:/freeplay/caesardecipher";  
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
    @RequestMapping("/freeplay/caesardecipher/decipherMapping")
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
        CaesarCipher newCipher = userService.getCurrentUser(request).getCaesarDecipher();
        newCipher.setMap(cipherMap);
        userService.changeCaesarDecipher(userService.getCurrentUser(request).getUsername(), newCipher);

        // Retrieve the current ciphered text from the user session
        CaesarCipher cipher = userService.getCurrentUser(request).getCaesarDecipher();
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
            userService.changeCaesarDecipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseErrorCounter(cipher));
            System.out.println(cipher.getErrorCounter());
        }

        return "redirect:/freeplay/caesardecipher";
    }

    /**
     * Function to clear the supposed mapping of the cipher.
     * 
     * @param request HTTP request made by a client.
     *  
     * @return The updated caesarcipher view.
     */
    @RequestMapping("/freeplay/caesardecipher/clearMapping")
    public String clearMapping(HttpServletRequest request) {

        // Clear the current supposed map
        CaesarCipher newCipher = userService.getCurrentUser(request).getCaesarDecipher();
        newCipher.setMap(new HashMap<>());
        userService.changeCaesarDecipher(userService.getCurrentUser(request).getUsername(), newCipher);

        return "redirect:/freeplay/caesardecipher";
    }

    /**
     * Function to reveal the original Text and load the congrats and disclaimer.
     * 
     * @param request               HTTP request made by a client.
     * @param redirectAttributes    Object to redirect Attributes.
     * 
     * @return The updated caesarcipher view.
     */
    @RequestMapping("/freeplay/caesardecipher/revealOriginal")
    public String revealOriginal(HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) {

        CaesarCipher newCipher = userService.getCurrentUser(request).getCaesarDecipher();
        
        redirectAttributes.addFlashAttribute("tryoutText", newCipher.getOriginalText());
        redirectAttributes.addFlashAttribute("successMessage", "Hier siehst du den richtigen Text!");

        return "redirect:/freeplay/caesardecipher";
    }

    /**
     * Function to try out a new caesar decipher.
     * 
     * @param request  HTTP request made by a client.
     * 
     * @return The updated caesardecipher view with a new casear decipher.
     */
    @RequestMapping("/freeplay/caesardecipher/tryNew")
    public String tryNew(HttpServletRequest request,
                         RedirectAttributes redirectAttributes) {

        CaesarCipher newCipher = caesarCipherService.createRandomCaesarDecipher();
        userService.changeCaesarDecipher(userService.getCurrentUser(request).getUsername(), newCipher);

        redirectAttributes.addFlashAttribute("successMessage", "Hier ist dein neuer verschlüsselter Text. Viel Erfolg!");

        return "redirect:/freeplay/caesardecipher";
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
    @RequestMapping("/freeplay/caesardecipher/trySolution")
    public String trySolution(RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              @RequestParam("tryOutText") String tryOutText) {


            CaesarCipher cipher = userService.getCurrentUser(request).getCaesarDecipher();

            if (cipher.getOriginalText().equals(tryOutText)) {
                redirectAttributes.addFlashAttribute("successMessage", "Glückwunsch, deine Verschlüsselung ist korrekt!");
                Highscore currentHighscore = userService.getCurrentUser(request).getHighscore();
                System.out.println(currentHighscore.getCaesarDecipherHighscore());
                userService.changeHighscore(userService.getCurrentUser(request).getUsername(), highscoreService.updateCaesarDecipherHighscore(currentHighscore, cipher.getErrorCounter(), cipher.getErrorCounter()));

            } else {
                userService.changeCaesarDecipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseErrorCounter(cipher));
                Integer errors = cipher.getErrorCounter();
                System.out.println(errors); 

                // Give Feedback and hints and update the hints counter
                if (errors < 3) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider ist das nicht korrekt!");
                } else if (errors == 3) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, Nutze die Disk und verschiebe sie.");
                    userService.changeCaesarDecipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher));
                } else if (errors == 4) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, Nutze die Disk und verschiebe sie um die Zahl im Uhrzeigersinn.");
                    userService.changeCaesarDecipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher));
                } else if (errors >= 5) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, probiere den Shift aus das reduziert deine Punkte aber gibt dir eine Vorstellung für das nächste mal.");
                    userService.changeCaesarDecipher(userService.getCurrentUser(request).getUsername(), caesarCipherService.increaseHints(cipher)); 
                }         
            }

        return "redirect:/freeplay/caesardecipher";   
    }
}
