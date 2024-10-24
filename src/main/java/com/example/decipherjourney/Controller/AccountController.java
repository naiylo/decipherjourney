package com.example.decipherjourney.Controller;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.decipherjourney.Model.*;
import com.example.decipherjourney.Service.CookieService;
import com.example.decipherjourney.Service.UserService;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * Account Controller to start the application and redirect to the index. 
 * 
 * @author Oskar Schiedewitz
 */
@Controller
public class AccountController {

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
     * Function to load the account view
     * 
     * @param request   HTTP request made by a client.
     * @param model     The model to set the attributes on.
     * 
     * @return  The account view.
     */
    @RequestMapping("/account")
    public String main(HttpServletRequest request, Model model) {
        System.out.println("Switched to account page.");

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

        return "account";
    }

    /**
     * Function to change the user information
     * 
     * @param newUsername The new username to set for the user
     * @param request     HTTP request made by a client.
     * 
     * @return The account view with our without updated information.
     */
    @RequestMapping("/save-account")
    public String saveAccount(@RequestParam("username") String newUsername, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        String username = user.getUsername();

        try {
            userService.changeUsername(username, newUsername);
            System.out.println("Saving new user data worked.");
        } catch (Exception e) {
            System.out.println("Saving new user data did not work accordingly.");
        }

        return "redirect:/account";

    }
    
}
