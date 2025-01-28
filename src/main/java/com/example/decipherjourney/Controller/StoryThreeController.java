package com.example.decipherjourney.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.decipherjourney.Model.StoryMode;
import com.example.decipherjourney.Model.StoryThree;
import com.example.decipherjourney.Model.User;
import com.example.decipherjourney.Service.CookieService;
import com.example.decipherjourney.Service.StoryModeService;
import com.example.decipherjourney.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * StoryThreeController implements all needed functions for the third story part.
 * 
 * Author: Oskar Schiedewitz
 */
@Controller
public class StoryThreeController {
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
     * Function to load the third story view.
     * 
     * @param request HTTP request made by a client.
     * @param model   The model to set the attributes on.
     * 
     * @return The third story view dependending on the current checkpoint.
     */
    @RequestMapping("/storyThree")
    public String showStoryTwo(HttpServletRequest request, Model model) {
        System.out.println("Switched to story 3.");

        // Check if user is logged in if not switch to login page where he can login or register

        if (!cookieService.isCookieSet(request, "user-cookie")) {
            System.out.println("Not logged in. Redirecting to login page.");
            return "redirect:/login";
        }
        
        User currentUser = userService.getCurrentUser(request);
        StoryMode storyMode = currentUser.getStoryMode();
        StoryThree storyThree = (StoryThree) storyMode.getStoryParts().get(2);
        String checkpoint = storyThree.getCheckpoint();

        // Add checkpoint to the model
        model.addAttribute("checkpoint", checkpoint);

        // Return the current Story View
        switch(checkpoint) {
            case "Kapitel 3: London im 16ten Jahrhundert":
                System.out.println("Story 3 Part 0.");
                model.addAttribute("dialogs", storyThree.getDialogs().get(0));
                model.addAttribute("part", 0);
                System.out.println(storyThree.getDialogs().get(0));
                return "storyThree";

            default:
            return "storyThree";
        }
  
    }
    
}
