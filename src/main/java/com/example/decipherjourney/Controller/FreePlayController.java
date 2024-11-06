package com.example.decipherjourney.Controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.decipherjourney.Model.*;
import com.example.decipherjourney.Service.CookieService;
import com.example.decipherjourney.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * FreePlayController is a menu controller that allows users to select a cipher type and learn and train.
 * 
 * Author: Oskar Schiedewitz
 */
@Controller
public class FreePlayController {

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
     * Function to load the freeplay view.
     * 
     * @param request   HTTP request made by a client.
     * @param model     The model to set attributes for the view.
     * 
     * @return The free play view.
     */
    @RequestMapping("/freeplay")
    public String showFreePlayMenu(HttpServletRequest request, Model model) {
        System.out.println("Switched to freeplay page.");
        
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

        return "freeplay";
    }

}