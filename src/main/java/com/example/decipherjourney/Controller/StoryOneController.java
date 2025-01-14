package com.example.decipherjourney.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.decipherjourney.Model.StoryMode;
import com.example.decipherjourney.Model.StoryOne;
import com.example.decipherjourney.Model.User;
import com.example.decipherjourney.Service.CookieService;
import com.example.decipherjourney.Service.StoryModeService;
import com.example.decipherjourney.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * StoryOneController implements all neede functions for the first story part.
 * 
 * Author: Oskar Schiedewitz
 */
@Controller
public class StoryOneController {
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
     * Function to load the first story view.
     * 
     * @param request HTTP request made by a client.
     * @param model   The model to set the attributes on.
     * 
     * @return The first story view dependending on the current checkpoint.
     */
    @RequestMapping("/storyOne")
    public String showStoryOne(HttpServletRequest request, Model model) {
        System.out.println("Switched to story 1.");

        // Check if user is logged in if not switch to login page where he can login or register

        if (!cookieService.isCookieSet(request, "user-cookie")) {
            System.out.println("Not logged in. Redirecting to login page.");
            return "redirect:/login";
        }
        
        User currentUser = userService.getCurrentUser(request);
        StoryMode storyMode = currentUser.getStoryMode();
        StoryOne storyOne = (StoryOne) storyMode.getStoryParts().get(0);
        String checkpoint = storyOne.getCheckpoint();

        // Add checkpoint to the model
        model.addAttribute("checkpoint", checkpoint);

        // Return the current Story View
        switch(checkpoint) {
            case "Kapitel 1: Der Anfang einer Freundschaft":
                System.out.println("Story 1 Part 0.");
                model.addAttribute("dialogs", storyOne.getDialogs().get(0));
            return "storyOne";

            default:
            return "storyOne";
        }
  
    }   
}
