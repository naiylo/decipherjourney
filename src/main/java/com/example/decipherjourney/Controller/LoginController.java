package com.example.decipherjourney.Controller;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.decipherjourney.Service.CookieService;
import com.example.decipherjourney.Service.UserService;

/**
 * Login Controller to handle user login functionality.
 * 
 * @author Oskar Schiedewitz
 */
@Controller
public class LoginController {

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
     * Function to load the login view.
     * 
     * @param request   HTTP request made by a client.
     * @param model     The model to set the attributes on.
     * 
     * @return  The login view.
     */
    @RequestMapping("/login")
    public String Login(HttpServletRequest request, Model model) {
        System.out.println("Switched to login page.");

        return "Login";
    }

    /**
     * Function to check the login information
     * 
     * @param username              The username the client is trying to login to.
     * @param password              The password the client is trying to login with.
     * @param response              The cookie to set for the client.
     * @param redirectAttributes    The direct feedback to be sent to the view.
     * 
     * @return A redirection to the landing page either with a set cookie or without.
     */
    @RequestMapping("/checkLogin")
    public String checkLogin(@RequestParam("username") String username, 
                             @RequestParam("password") String password, 
                             HttpServletResponse response,
                             RedirectAttributes redirectAttributes) {
        System.out.println("Checking username: " + username);

        // Authenticate the user data
        try {
            if (userService.getUserByUsername(username) != null) {
                String userId = userService.getUserByUsername(username).getId();

                // Checking if password is correct
                if(password.equals(userService.getUserByUsername(username).getPassword())) {
                    // If Login is succesful set the user cookie and redirect to the main menu
                    cookieService.setUserCookie(response, userId);
                    return "redirect:/";
                } 
            }

            redirectAttributes.addFlashAttribute("errorMessage", "The password or username is wrong. Please try again.");

            return "redirect:/login";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "There is an unknown error. Please try again.");
        } 
        
        return "redirect:/";  
    }

}
