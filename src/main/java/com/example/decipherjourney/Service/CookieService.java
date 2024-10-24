package com.example.decipherjourney.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

/**
 * Cookie Service to implement all functions we need to get and set cookies to store userdata in the browser
 *
 * @author Oskar Schiedewitz
 */
@RestController
public class CookieService {

    /**
     * Checks if a specific cookie is set in the provided HttpServletRequest object.
     *
     * @param request    The HttpServletRequest object from which to retrieve cookies.
     * 
     * @param cookieName The name of the cookie to check for.
     * 
     * @return True if the cookie with the given name is present in the request, false otherwise.
     */
    public boolean isCookieSet(HttpServletRequest request, String cookieName) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(cookieName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retrieves the value of the specified cookie from the HttpServletRequest.
     *
     * @param request    The HttpServletRequest object from which to retrieve cookies.
     * @param cookieName The name of the cookie whose value needs to be retrieved.
     * 
     * @return The value of the cookie if found, or null if the cookie is not found.
     */
    public String getCookieValue(HttpServletRequest request, String cookieName) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Sets a secure, HTTP-only cookie to store the user's ID.
     * This method creates a cookie named "user-cookie" with the provided user ID as its value.
     * The cookie is configured to be secure, HTTP-only, and is set to expire after 7 days.
     * The cookie is set with the path attribute to "/", making it accessible throughout the application.
     *
     * @param response The HttpServletResponse object to which the cookie will be added.
     * @param userId   The user ID to be stored in the cookie.
     */
    public void setUserCookie(HttpServletResponse response, String userId) {
        Cookie cookie = new Cookie("user-cookie", userId);
        // Cookie age is 7 days
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

   /**
     * Deletes specific cookies from the HTTP request.
     * This method targets only the cookies named "user-cookie" and "admin-cookie".
     * It sets their values to an empty string and their max age to 0, effectively instructing the browser to delete them.
     *
     * @param request  The HttpServletRequest object containing the client's request.
     * @param response The HttpServletResponse object containing the response to the client.
     */
    public void deleteAllCookies(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("user-cookie".equals(cookie.getName()) || "admin-cookie".equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

}
