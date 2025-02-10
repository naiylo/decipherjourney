package com.example.decipherjourney.Controller;

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
 * VigenereCipherController is a controller that allows users to learn and train the vigenere cipher with different random generated examples.
 * 
 * @author Oskar Schiedewitz
 */
@Controller
public class VigenereCipherController {

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
     * Attribute to safe the current vigenere cipher
     */
    @Autowired
    VigenereCipherService vigenereCipherService;

    /**
     * Attribute to safe the current highscore
     */
    @Autowired
    HighscoreService highscoreService;

    /**
     * Function to load the vigenere cipher view.
     * 
     * @param request   HTTP request made by a client.
     * @param model     The model to set attributes for the view.
     * 
     * @return The vigenere view.
     */
    @RequestMapping("/freeplay/vigenere")
    public String showFreePlayMenu(HttpServletRequest request, Model model) {
        System.out.println("Switched to vigenere page.");
        
        // Check if user is logged in if not switch to login page where he can login or register

        if (!cookieService.isCookieSet(request, "user-cookie")) {
            System.out.println("Not logged in yet switch to login page.");
            return "redirect:/login";
        } else {
            try {
                User user = userService.getCurrentUser(request);
                System.out.println("Cookie was succesful.");

                // Check if user is currently working on a vigenere cipher and load it if so

                if (user.getVigenereCipher() == null) {
                    System.out.println("Create new vigenere cipher!");
                    VigenereCipher cipher = vigenereCipherService.createRandomVigenereCipher();
                    userService.changeVigenereCipher(user.getUsername(), cipher);;
                    model.addAttribute("cipher", cipher);
                    model.addAttribute("testnumber", 1);
                } else {
                    VigenereCipher cipher = user.getVigenereCipher();
                    model.addAttribute("cipher", cipher);
                    model.addAttribute("testnumber", 1);
                }
                
    
            } catch (Exception e) {
                System.out.println("Cookie was not succesful.");
            }
        }

        model.addAttribute("highscore", userService.getCurrentUser(request).getHighscore().getVigenereHighscore());
        
        return "vigenere";
    }

    /**
     * Function to try out a specific keyword for the cipher
     * 
     * @param newKey                The key the user is trying out.
     * @param request               HTTP request made by a client.
     * @param redirectAttributes    Object to redirect Attributes 
     * @param model                 The model to set attributes for the view.    
     * 
     * @return The updated vigenerecipher view.
     */
    @RequestMapping("/freeplay/vigenerecipher/tryKey")
    public String tryKey(@RequestParam String newKey, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {

        String key = newKey.toUpperCase();

        if (key != null && !key.isEmpty() && key.matches("^[A-Z]+$")) {

            User user = userService.getCurrentUser(request);
            VigenereCipher cipher = user.getVigenereCipher();
            String cipheredText = cipher.getCipheredText();
            String decipheredText = vigenereCipherService.decipherText(cipheredText, key);

            System.out.println(decipheredText);

            // If correct update highscore and return feedback
            if (decipheredText.equals(cipher.getOriginalText())) {
                redirectAttributes.addFlashAttribute("successMessage", "Glückwunsch, deine Übersetzung ist korrekt!");
                Highscore currentHighscore = userService.getCurrentUser(request).getHighscore();
                userService.changeHighscore(userService.getCurrentUser(request).getUsername(), highscoreService.updateVigenereHighscore(currentHighscore, cipher.getErrorCounter(), cipher.getHints()));

            // If incorrect update error counter and reveal hints
            } else {
                userService.changeVigenereCipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseErrorCounter(cipher));
                Integer errors = cipher.getErrorCounter();
                System.out.println(errors);
                System.out.println(cipher.getHints());
 
                // Give Feedback and hints and update the hints counter
                if (errors < 3) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider ist das nicht korrekt!");
                } else if (errors == 3) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, probiere Häufige Buchstaben zu vergleichen.");
                    userService.changeVigenereCipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseHints(cipher));
                } else if (errors == 4) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, Suche ein typisches Schlüsselwort wie KEY oder einfach Buchstaben.");
                    userService.changeVigenereCipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseHints(cipher));
                } else if (errors == 5) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, Suche ein typisches Schlüsselwort wie KEY, TEST oder einfach Buchstaben.");
                    userService.changeVigenereCipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseHints(cipher)); 
                } else if (errors == 6) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, Suche ein typisches Schlüsselwort wie KEY, TEST, ABC, oder einfach Buchstaben.");
                    userService.changeVigenereCipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseHints(cipher));   
                } else if (errors == 7) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Nutze den Modulo um mit dem Schlüssel zu verschieben. Denk daran den Schlüssel an die Länge anzupassen.");
                    userService.changeVigenereCipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseHints(cipher));
                } else if (errors > 7){
                    redirectAttributes.addFlashAttribute("errorMessage2", "Die Schlüssellänge ist: " + cipher.getKeyword().length());
                }
            }
        
            redirectAttributes.addFlashAttribute("tryoutText", decipheredText);

        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Der Schlüssel darf nur aus Buchstaben ohne Leerzeichen bestehen.");
        }

        return "redirect:/freeplay/vigenere";
    }

    /**
     * Function to reveal the original Text and load the congrats and disclaimer.
     * 
     * @param request               HTTP request made by a client.
     * @param redirectAttributes    Object to redirect Attributes.
     * 
     * @return The updated vigenerecipher view.
     */
    @RequestMapping("/freeplay/vigenerecipher/revealOriginal")
    public String revealOriginal(HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) {

        VigenereCipher newCipher = userService.getCurrentUser(request).getVigenereCipher();
        newCipher.setCipheredText(newCipher.getOriginalText());
        
        redirectAttributes.addFlashAttribute("tryoutText", newCipher.getOriginalText());
        redirectAttributes.addFlashAttribute("successMessage", "Glückwunsch, hier siehst du den richtigen Text.");

        return "redirect:/freeplay/vigenere";
    }

    /**
     * Function to try out a new vigenere cipher.
     * 
     * @param request  HTTP request made by a client.
     * 
     * @return The updated vigenerecipher view with a new vigenere cipher.
     */
    @RequestMapping("/freeplay/vigenerecipher/tryNew")
    public String tryNew(HttpServletRequest request,
                         RedirectAttributes redirectAttributes) {

        VigenereCipher newCipher = vigenereCipherService.createRandomVigenereCipher();
        userService.changeVigenereCipher(userService.getCurrentUser(request).getUsername(), newCipher);

        redirectAttributes.addFlashAttribute("successMessage", "Hier ist dein neuer verschlüsselter Text. Viel Erfolg!");

        return "redirect:/freeplay/vigenere";
    }

    /**
     * Function to submit your own solution
     *  
     * @param redirectAttributes    Object to redirect Attributes.
     * @param request               HTTP request made by a client.
     * @param tryOutText            The submitted text to compare to the solution.
     * 
     * @return The updated vigenerecipher view.
     */
    @RequestMapping("/freeplay/vigenerecipher/trySolution")
    public String trySolution(RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              @RequestParam("tryOutText") String tryOutText) {


            VigenereCipher cipher = userService.getCurrentUser(request).getVigenereCipher();

            if (cipher.getOriginalText().equals(tryOutText)) {
                redirectAttributes.addFlashAttribute("successMessage", "Glückwunsch, deine Übersetzung ist korrekt!");
                Highscore currentHighscore = userService.getCurrentUser(request).getHighscore();
                userService.changeHighscore(userService.getCurrentUser(request).getUsername(), highscoreService.updateVigenereHighscore(currentHighscore, cipher.getErrorCounter(), cipher.getHints()));

            } else {
                userService.changeVigenereCipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseErrorCounter(cipher));
                Integer errors = cipher.getErrorCounter();
                System.out.println(errors);
                System.out.println(cipher.getHints());
 
                // Give Feedback and hints and update the hints counter
                if (errors < 3) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider ist das nicht korrekt!");
                } else if (errors == 3) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, probiere Häufige Buchstaben zu vergleichen.");
                    userService.changeVigenereCipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseHints(cipher));
                } else if (errors == 4) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, Suche ein typisches Schlüsselwort wie KEY oder einfach Buchstaben.");
                    userService.changeVigenereCipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseHints(cipher));
                } else if (errors == 5) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, Suche ein typisches Schlüsselwort wie KEY, TEST oder einfach Buchstaben.");
                    userService.changeVigenereCipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseHints(cipher)); 
                } else if (errors == 6) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider falsch, Suche ein typisches Schlüsselwort wie KEY, TEST, ABC, oder einfach Buchstaben.");
                    userService.changeVigenereCipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseHints(cipher));   
                } else if (errors == 7) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Nutze den Modulo um mit dem Schlüssel zu verschieben. Denk daran den Schlüssel an die Länge anzupassen.");
                    userService.changeVigenereCipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseHints(cipher));
                } else if (errors > 7){
                    redirectAttributes.addFlashAttribute("errorMessage2", "Die Schlüssellänge ist: " + cipher.getKeyword().length());
                }       
            }

        return "redirect:/freeplay/vigenere";   
    }
    
}
