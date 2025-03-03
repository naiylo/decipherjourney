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
import com.example.decipherjourney.Model.StoryMode;

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
        StoryMode storyMode = currentUser.getStoryMode();

        Integer currentStoryPart = storyModeService.getCurrentStoryPart(storyMode);

        System.out.println("currentStoryPart: " + currentStoryPart);

        // Return the curent Story View
        switch(currentStoryPart) {
            case 1:
            System.out.println("Part 1. Redirecting to the StoryOneController.");
            return "redirect:/storyOne";

            case 2:
            System.out.println("Part 2. Redirecting to the StoryTwoController.");
            return "redirect:/storyTwo";

            case 3:
            System.out.println("Part3. Redirecting to the StoryThreeController.");
            return "redirect:/storyThree";

            default:
            return "endscreen";
        }

    }

        /**
     * Function to switch to the next story part
     * 
     * @param request HTTP request made by a client.
     * 
     * @return Reload the StoryMode to switch to next story part.
     */
    @RequestMapping("/nextPart")
    public String nextPart(HttpServletRequest request) {

        User currentUser = userService.getCurrentUser(request);
        StoryMode storyMode = currentUser.getStoryMode();
        StoryMode newStoryMode = storyModeService.nextStoryPart(storyMode);
        userService.changeStoryMode(currentUser.getUsername(), newStoryMode);

        return "redirect:/story/play";
    } 
    
}
