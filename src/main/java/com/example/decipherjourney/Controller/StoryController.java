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
 * StoryController is a menu controller that allows users to either start a old gamestate of the story or start from new.
 * 
 * Author: Oskar Schiedewitz
 */
@Controller
public class StoryController {

    /**
     * Attribute to connect the view to the user database.
     */ 
    @Autowired
    UserService userService;

    /**
     * Attribute to safe the current cookie.
     */
    @Autowired
    CookieService cookieService;

    /**
     * Function to load the story view.
     * 
     * @param request   HTTP request made by a client.
     * @param model     The model to set attributes for the view.
     * 
     * @return The story play view.
     */
    @RequestMapping("/story")
    public String showStoryMenu(HttpServletRequest request, Model model) {
        System.out.println("Switched to story mode page.");

        // Check if user is logged in if not switch to login page where he can login or register

        if (!cookieService.isCookieSet(request, "user-cookie")) {
            System.out.println("Not logged in. Redirecting to login page.");
            return "redirect:/login";
        }


        return "story";
    }
}

