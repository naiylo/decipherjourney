package com.example.decipherjourney.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.decipherjourney.Service.UserService;
import com.example.decipherjourney.Service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Register Controller to handle user registration functionality.
 * 
 * @author Oskar Schiedewitz
 */
@Controller
public class RegisterController {

    /**
     * Attribute to connect the view to the user database.
     */
    @Autowired
    UserService userService;

    /**
     * Attribute to manage cookies for users.
     */
    @Autowired
    CookieService cookieService;

    /**
     * Function to load the registration view.
     * 
     * @param request HTTP request made by a client.
     * @param model   The model to set the attributes on.
     * 
     * @return The registration view.
     */
    @RequestMapping("/register")
    public String Register(HttpServletRequest request, Model model) {
        System.out.println("Switched to register page.");
        return "Register"; 
    }

    /**
     * Function to handle registration data.
     * 
     * @param username The username provided by the client.
     * @param password The password provided by the client.
     * @param response The HTTP response to set cookies.
     * 
     * @return Redirect to the main menu or back to registration if registration failed.
     */
    @RequestMapping("/submitRegistration")
    public String submitRegistration(@RequestParam("username") String username, 
                                     @RequestParam("password") String password, 
                                     HttpServletResponse response) {
        System.out.println("Attempting to register username: " + username);

        // Try to create the new user
        try {
            userService.createUser(username, password, "0"); 
            String userId = userService.getUserByUsername(username).getId();

            // Set the cookie for the new user and navigate back to main menu
            cookieService.setUserCookie(response, userId);

            System.out.println("User registration successful.");

            return "redirect:/"; 
        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
            return "redirect:/register";
        }
    }
}


