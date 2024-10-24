package com.example.decipherjourney.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.example.decipherjourney.Model.*;
import com.mongodb.client.result.UpdateResult;
import java.util.Optional;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService {

    /**
     * Attribute to save the mongoTemplate
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Attribute to save the userRepository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Service to manage user cookies.
     */
    @Autowired
    private CookieService cookieService;


    /**
     * Creates a new user in the database.
     * 
     * @param user The user object to be created.
     * 
     * @return The created user.
     */
    public void createUser(String username, String password, String level) {
        
        if (!userRepository.findByUsername(username).isEmpty()) {
            System.out.println("Username already taken.");
            return;
        }

        try {

            System.out.println("Attempting to register: " + username);

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setLevel(level);
            newUser.setPassword(password);

            userRepository.save(newUser);

            // Verify user creation
            User createdUser = userRepository.findByUsername(username).stream().findFirst().orElse(null);
            if (createdUser != null) {
                System.out.println("User created succesfully: " + createdUser.getUsername());
            } else {
                System.err.println("Something went wrong while trying to create the user: " + username);
            }

        } catch (Exception e) {
            System.out.println("Something went wrong while trying to create the user.");
            return;
        }

    }

    /**
     * Deletes an existing user from the database.
     *
     * @param user The user to be deleted.
     */
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    /**
     * Retrieves the current user based on the value of a specific cookie from the HTTP request.
     *
     * @param request The HttpServletRequest object that contains the request the client has made
     *                to the servlet.
     * 
     * @return The `User` object associated with the email retrieved from the "user-cookie".
     */
    public User getCurrentUser(HttpServletRequest request) {
        String userId = cookieService.getCookieValue(request, "user-cookie");
        return getUserByUsername(getUsernameById(userId));
    }

    /**
     * Retrieves all users from the database.
     * 
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves the email address of a user by their ID.
     *
     * @param userId The ID of the user whose email is to be retrieved.
     * 
     * @return The email address of the user, or null if the user is not found or an error occurs.
     * 
     * @throws IllegalArgumentException If the provided userId is null or empty.
     */
    public String getUsernameById(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }

        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return user.getUsername();
            }
        } catch (Exception e) {
            System.err.println("An error occurred while trying to find the username by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Finds users by their username.
     * 
     * @param username The username of the user to find.
     * 
     * @return A list of users with the specified username.
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).getFirst();
    }

    /**
     * Updates a specific field of the user identified by the given email.
     *
     * @param username       The username of the user to be updated.
     * @param field          The field to be updated.
     * @param value          The new value to set for the field.
     * @param successMessage The message to print if the update is successful.
     */
    private void updateField(String username, String field, Object value, String successMessage) {
        if (username == null || username.isEmpty()) {
            System.out.println("Usersname can not be empty.");
            return;
        }

        Query query = new Query(Criteria.where("username").is(username));
        Update update = new Update().set(field, value);
        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);

        if (result.getModifiedCount() > 0) {
            System.out.println(successMessage);
        } else {
            System.out.println("Nothing was changed while trying to update the field: " + field);
        }
    }

    /**
     * Updates the username of the user identified by the username
     * 
     * @param username      The current username of the user
     * @param newUsername   The new username of the user
     */
    public void changeUsername(String username, String newUsername) {
        if (username == null || username.isEmpty()) {
            System.out.println("Username can not be empty.");
            return;
        }

        if (userRepository.findByUsername(newUsername).size() > 1) {
            System.out.println("Username did not change or is already taken.");
            return;
        }

        updateField(username, "username", newUsername, "Updated username succesfully.");
    }

    /**
     * Updates the level of the user identified by the username
     * 
     * @param username      The current username of the user
     * @param level         The new level of the user
     */
    public void changeLevel(String username, String level) {
        if (username == null || username.isEmpty()) {
            System.out.println("Username can not be empty.");
            return;
        }
        updateField(username, "level", level, "Updated level succesfully.");
    }

}

