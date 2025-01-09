package com.example.decipherjourney.Controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.decipherjourney.Model.*;
import com.example.decipherjourney.Service.CookieService;
import com.example.decipherjourney.Service.StoryModeService;
import com.example.decipherjourney.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * StoryMenuController is a menu controller that allows users to either start a old gamestate of the story or start from new.
 * 
 * Author: Oskar Schiedewitz
 */
@Controller
public class StoryMenuController {

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
        System.out.println("Switched to story menu page.");

        // Check if user is logged in if not switch to login page where he can login or register

        if (!cookieService.isCookieSet(request, "user-cookie")) {
            System.out.println("Not logged in. Redirecting to login page.");
            return "redirect:/login";
        }

        User currentUser = userService.getCurrentUser(request);

        if (currentUser.getStoryMode() == null) {
            System.out.println("Creating new StoryMode.");
            StoryMode storyMode = storyModeService.initializeStoryParts();
            userService.changeStoryMode(currentUser.getUsername(), storyMode);

        } else {
            StoryMode currentStoryMode = currentUser.getStoryMode();
            model.addAttribute("hasStoryMode", currentStoryMode != null);

        }

        return "story";
    }

    /**
     * Start new StoryMode .
     * 
     * @param request   HTTP request made by a client.
     * 
     * @return The Story from a new beginning.
     */
    @RequestMapping("/story/newgame")
    public String startNewGame(HttpServletRequest request) {
        System.out.println("Starting a new story mode.");
        
        User currentUser = userService.getCurrentUser(request);
        StoryMode newStoryMode = storyModeService.initializeStoryParts();   
        userService.changeStoryMode(currentUser.getUsername(), newStoryMode);

        return "redirect:/story/play";
    }

    /**
     * Resume to your current Story Mode.
     * 
     * @param request   HTTP request made by a client.
     * 
     * @return The story view from the current checkpoint.
     */
    @RequestMapping("/story/resume")
    public String resumeStory(HttpServletRequest request) {
        System.out.println("Resuming current story mode.");
        
        return "redirect:/story/play";
    }
}

