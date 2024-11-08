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
     * Function to freeplay the login view.
     * 
     * @param request   HTTP request made by a client.
     * @param model     The model to set attributes for the view.
     * 
     * @return The caesarcipher view.
     */
    @RequestMapping("/freeplay/caesarcipher")
    public String showFreePlayMenu(HttpServletRequest request, Model model) {
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
                } else {
                    CaesarCipher cipher = user.getCaesarCipher();
                    model.addAttribute("cipher", cipher);
                }
                
    
            } catch (Exception e) {
                System.out.println("Cookie was not succesful.");
            }
        }
        
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
     * @return                      The updated caesarcipher view.
     */
    @RequestMapping("/freeplay/caesarcipher/shift")
    public String shiftCipher(@RequestParam("shift") String shiftValue,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request, 
                              Model model) {

        String shiftString = (shiftValue.isEmpty()) ? "0" : shiftValue;
        try {
            int shift = Integer.parseInt(shiftString);
            System.out.println(shift);

            // Get the existing cipher from the model
            CaesarCipher cipher = userService.getCurrentUser(request).getCaesarCipher();

            // Use the shift value to generate a new ciphered text
            String shiftedText = caesarCipherService.decipherText(cipher.getCipheredText(), shift);

            redirectAttributes.addFlashAttribute("tryoutText", shiftedText);
            redirectAttributes.addFlashAttribute("shift", shift);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The shift value should be a number between 0 and 25");
        }

         
        return "redirect:/freeplay/caesarcipher";  
    }
    
}
