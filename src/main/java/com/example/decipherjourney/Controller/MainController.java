package com.example.decipherjourney.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * @param request               HTTP request made by a client.
     * @param model                 The model to set the attributes on.
     * 
     * @return  The landing page view.
     */
    @GetMapping("/")
    public String main(HttpServletRequest request, 
                       Model model) {
        System.out.println("Start-Application");

        try {
            User user = userService.getCurrentUser(request);
            model.addAttribute("username", user.getUsername());
            model.addAttribute("feedbackmessage", "Login");
            return "index";
        } catch (Exception e) {}

        model.addAttribute("feedbackmessage", "You are not logged in yet. Go to My-Account to login or register.");
        return "index";
    }

    /**
     * Logout of your account.
     * 
     * @param request   HTTP request made by a client.
     * @param response  HTTP response to the client.
     * 
     * @return  The landing page view.
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        cookieService.deleteAllCookies(request, response);

        return "redirect:/";
    }
    
}
