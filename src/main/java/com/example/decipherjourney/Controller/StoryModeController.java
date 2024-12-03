package com.example.decipherjourney.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.decipherjourney.Model.User;
import com.example.decipherjourney.Service.CookieService;
import com.example.decipherjourney.Service.StoryModeService;
import com.example.decipherjourney.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * StoryModeController is a controller to switch betwwen the different story Parts depending on the current part.
 * 
 * Author: Oskar Schiedewitz
 */
@Controller
public class StoryModeController {

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
     * Attribute to safe the current story mode.
    */
    @Autowired
    StoryModeService storyModeService;

    @RequestMapping("/story/play")
    public String showCurrentStoryPart(HttpServletRequest request, Model model) {
        System.out.println("Switched to story mode page.");

        // Check if user is logged in if not switch to login page where he can login or register

        if (!cookieService.isCookieSet(request, "user-cookie")) {
            System.out.println("Not logged in. Redirecting to login page.");
            return "redirect:/login";
        }

        User currentUser = userService.getCurrentUser(request);
        int currentStoryPart = storyModeService.getCurrentStoryPart(currentUser.getStoryMode());

        // Return the curent Story View
        switch(currentStoryPart) {
            case 1:
            System.out.println("Part 1.");
            return "redirect:/storyOne";

            default:
            return "redirect:/storyOne";
        }

    }
    
}
