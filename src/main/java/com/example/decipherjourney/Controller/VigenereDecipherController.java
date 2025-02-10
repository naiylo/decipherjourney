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
 * VigenereDecipherController is a controller that allows users to learn and train the vigenere cipher with different random generated examples.
 * 
 * @author Oskar Schiedewitz
 */
@Controller
public class VigenereDecipherController {

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
     * Function to load the vigenere decipher view.
     * 
     * @param request   HTTP request made by a client.
     * @param model     The model to set attributes for the view.
     * 
     * @return The vigenere decipher view.
     */
    @RequestMapping("/freeplay/vigeneredecipher")
    public String showFreePlayMenu(HttpServletRequest request, Model model) {
        System.out.println("Switched to vigenere decipher page.");
        
        // Check if user is logged in if not switch to login page where he can login or register

        if (!cookieService.isCookieSet(request, "user-cookie")) {
            System.out.println("Not logged in yet switch to login page.");
            return "redirect:/login";
        } else {
            try {
                User user = userService.getCurrentUser(request);
                System.out.println("Cookie was succesful.");

                // Check if user is currently working on a vigenere cipher and load it if so

                if (user.getVigenereDecipher() == null) {
                    System.out.println("Create new vigenere cipher!");
                    VigenereCipher cipher = vigenereCipherService.createRandomVigenereDecipher();
                    userService.changeVigenereDecipher(user.getUsername(), cipher);;
                    model.addAttribute("cipher", cipher);
                    model.addAttribute("key", cipher.getKeyword());
                    model.addAttribute("testnumber", 0);
                } else {
                    VigenereCipher cipher = user.getVigenereDecipher();
                    model.addAttribute("cipher", cipher);
                    model.addAttribute("key", cipher.getKeyword());
                    model.addAttribute("testnumber", 0);
                }
                
    
            } catch (Exception e) {
                System.out.println("Cookie was not succesful.");
            }
        }

        model.addAttribute("highscore", userService.getCurrentUser(request).getHighscore().getVigenereDecipherHighscore());
        
        return "vigeneredecipher";
    }

    /**
     * Function to try out a specific keyword for the cipher
     * 
     * @param newKey                The key the user is trying out.
     * @param request               HTTP request made by a client.
     * @param redirectAttributes    Object to redirect Attributes 
     * @param model                 The model to set attributes for the view.    
     * 
     * @return The updated vigeneredecipher view.
     */
    @RequestMapping("/freeplay/vigeneredecipher/tryKey")
    public String tryKey(@RequestParam String newKey, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {

        String key = newKey.toUpperCase();

        if (key != null && !key.isEmpty() && key.matches("^[A-Z]+$")) {

            User user = userService.getCurrentUser(request);
            VigenereCipher cipher = user.getVigenereDecipher();
            String cipheredText = cipher.getCipheredText();
            String decipheredText = vigenereCipherService.cipherText(cipheredText, key);

            // If correct update highscore and return feedback
            if (decipheredText.equals(cipher.getOriginalText())) {
                redirectAttributes.addFlashAttribute("successMessage", "Glückwunsch, deine Übersetzung ist korrekt!");
                Highscore currentHighscore = userService.getCurrentUser(request).getHighscore();
                userService.changeHighscore(userService.getCurrentUser(request).getUsername(), highscoreService.updateVigenereDecipherHighscore(currentHighscore, cipher.getErrorCounter(), cipher.getHints()));

            // If incorrect update error counter and reveal hints
            } else {
                userService.changeVigenereDecipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseErrorCounter(cipher));
                Integer errors = cipher.getErrorCounter();
                System.out.println(errors);
                System.out.println(cipher.getHints());
 
                // Give Feedback and hints and update the hints counter
                if (errors < 3) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider ist das nicht korrekt!");
                } else if (errors == 3) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Denk daran den Schlüssel immer zu wiederholen, so dass die Länge passt.");
                    userService.changeVigenereDecipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseHints(cipher));
                } else if (errors >= 4) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Rechne die Buchstaben Stücl für Stück plus das passende Schlüsselzeichen mir dem Modulo Rechner.");
                    userService.changeVigenereDecipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseHints(cipher));
                } 
            }

        
            redirectAttributes.addFlashAttribute("tryoutText", decipheredText);

        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Der Schlüssel darf nur aus Buchstaben ohne Leerzeichen bestehen.");
        }

        return "redirect:/freeplay/vigeneredecipher";
    }

    /**
     * Function to reveal the original Text and load the congrats and disclaimer.
     * 
     * @param request               HTTP request made by a client.
     * @param redirectAttributes    Object to redirect Attributes.
     * 
     * @return The updated vigeneredecipher view.
     */
    @RequestMapping("/freeplay/vigeneredecipher/revealOriginal")
    public String revealOriginal(HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) {

        VigenereCipher newCipher = userService.getCurrentUser(request).getVigenereDecipher();
        newCipher.setCipheredText(newCipher.getOriginalText());
        
        redirectAttributes.addFlashAttribute("tryoutText", newCipher.getOriginalText());
        redirectAttributes.addFlashAttribute("successMessage", "Glückwunsch, hier siehst du den richtigen Text.");

        return "redirect:/freeplay/vigeneredecipher";
    }

    /**
     * Function to try out a new vigenere decipher.
     * 
     * @param request  HTTP request made by a client.
     * 
     * @return The updated vigeneredecipher view with a new vigenere cipher.
     */
    @RequestMapping("/freeplay/vigeneredecipher/tryNew")
    public String tryNew(HttpServletRequest request,
                         RedirectAttributes redirectAttributes) {

        VigenereCipher newCipher = vigenereCipherService.createRandomVigenereDecipher();
        userService.changeVigenereDecipher(userService.getCurrentUser(request).getUsername(), newCipher);

        redirectAttributes.addFlashAttribute("successMessage", "Hier ist dein neuer verschlüsselter Text. Viel Erfolg!");

        return "redirect:/freeplay/vigeneredecipher";
    }

    /**
     * Function to submit your own solution
     *  
     * @param redirectAttributes    Object to redirect Attributes.
     * @param request               HTTP request made by a client.
     * @param tryOutText            The submitted text to compare to the solution.
     * 
     * @return The updated vigeneredecipher view.
     */
    @RequestMapping("/freeplay/vigeneredecipher/trySolution")
    public String trySolution(RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              @RequestParam("tryOutText") String tryOutText) {


            VigenereCipher cipher = userService.getCurrentUser(request).getVigenereDecipher();

            if (cipher.getOriginalText().equals(tryOutText)) {
                redirectAttributes.addFlashAttribute("successMessage", "Glückwunsch, deine Übersetzung ist korrekt!");
                Highscore currentHighscore = userService.getCurrentUser(request).getHighscore();
                userService.changeHighscore(userService.getCurrentUser(request).getUsername(), highscoreService.updateVigenereDecipherHighscore(currentHighscore, cipher.getErrorCounter(), cipher.getHints()));

            } else {
                userService.changeVigenereDecipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseErrorCounter(cipher));
                Integer errors = cipher.getErrorCounter();
                System.out.println(errors);
                System.out.println(cipher.getHints());
 
                // Give Feedback and hints and update the hints counter
                if (errors < 3) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Leider ist das nicht korrekt!");
                } else if (errors == 3) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Denk daran den Schlüssel immer zu wiederholen, so dass die Länge passt.");
                    userService.changeVigenereDecipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseHints(cipher));
                } else if (errors >= 4) {
                    redirectAttributes.addFlashAttribute("errorMessage2", "Rechne die Buchstaben Stücl für Stück plus das passende Schlüsselzeichen mir dem Modulo Rechner.");
                    userService.changeVigenereDecipher(userService.getCurrentUser(request).getUsername(), vigenereCipherService.increaseHints(cipher));
                } 
            }

        return "redirect:/freeplay/vigeneredecipher";   
    }
    
}
