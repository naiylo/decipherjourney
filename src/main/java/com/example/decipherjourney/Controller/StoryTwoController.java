package com.example.decipherjourney.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.decipherjourney.Model.StoryMode;
import com.example.decipherjourney.Model.StoryTwo;
import com.example.decipherjourney.Model.User;
import com.example.decipherjourney.Service.CookieService;
import com.example.decipherjourney.Service.StoryModeService;
import com.example.decipherjourney.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * StoryTwoController implements all needed functions for the second story part.
 * 
 * Author: Oskar Schiedewitz
 */
@Controller
public class StoryTwoController {
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
     * Function to load the second story view.
     * 
     * @param request HTTP request made by a client.
     * @param model   The model to set the attributes on.
     * 
     * @return The second story view dependending on the current checkpoint.
     */
    @RequestMapping("/storyTwo")
    public String showStoryTwo(HttpServletRequest request, Model model) {
        System.out.println("Switched to story 2.");

        // Check if user is logged in if not switch to login page where he can login or register

        if (!cookieService.isCookieSet(request, "user-cookie")) {
            System.out.println("Not logged in. Redirecting to login page.");
            return "redirect:/login";
        }
        
        User currentUser = userService.getCurrentUser(request);
        StoryMode storyMode = currentUser.getStoryMode();
        StoryTwo storyTwo = (StoryTwo) storyMode.getStoryParts().get(1);
        String checkpoint = storyTwo.getCheckpoint();

        // Add checkpoint to the model
        model.addAttribute("checkpoint", checkpoint);

        // Return the current Story View
        switch(checkpoint) {
            case "Kapitel 2: Das antike Reich":
                System.out.println("Story 2 Part 0.");
                model.addAttribute("dialogs", storyTwo.getDialogs().get(0));
                model.addAttribute("part", 0);
                System.out.println(storyTwo.getDialogs().get(0));
                return "storyTwo";

            default:
            return "storyTwo";
        }
  
    }
    
}
