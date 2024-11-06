package com.example.decipherjourney.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.example.decipherjourney.Model.*;
import com.example.decipherjourney.Service.CookieService;
import com.example.decipherjourney.Service.UserService;
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
                model.addAttribute("username", user.getUsername());
                System.out.println("Cookie was succesful.");
    
            } catch (Exception e) {
                System.out.println("Cookie was not succesful.");
            }
        }

        return "caesarcipher";
    }
    
}
