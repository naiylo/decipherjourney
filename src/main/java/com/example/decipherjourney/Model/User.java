package com.example.decipherjourney.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User class represents a user entity in the application.
 * This class is mapped to the "user" collection in MongoDB.
 * 
 * @author Oskar Schiedewitz
 */
@Document(collection = "user")
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    private String id;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The level associated with the user.
     */
    private String level;

    // Getter and Setter methods

    /**
     * Gets the unique identifier of the user.
     * 
     * @return The id of the user.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     * 
     * @param id The new id to set for the user.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     * 
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the user.
     * 
     * @param username The new username to set for the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the level of the user.
     * 
     * @return The level of the user.
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets the level for the user.
     * 
     * @param level The new level to set for the user.
     */
    public void setLevel(String level) {
        this.level = level;
    }
}
