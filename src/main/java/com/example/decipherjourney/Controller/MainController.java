package com.example.decipherjourney.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.decipherjourney.Service.CookieService;
import com.example.decipherjourney.Service.UserService;
import com.example.decipherjourney.Model.*;
import org.springframework.ui.Model;


/**
 * Main Controller to start the application and redirect to the index. 
 * 
 * @author Oskar Schiedewitz
 */
@Controller
public class MainController {

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
     * Function to redirect to the "index.html" and set a user if their is a cookie.
     * 
     * @return  The landing page view.
     */
    @GetMapping("/")
    public String main(HttpServletRequest request, Model model) {
        System.out.println("Start-Application");

        try {
            User user = userService.getCurrentUser(request);
            model.addAttribute("username", user.getUsername());
            System.out.println("Cookie was succesful.");

        } catch (Exception e) {
            System.out.println("Cookie was not succesful.");
        }

        return "index";
    }
    
}
