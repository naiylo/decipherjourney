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

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The story progress of the user.
     */
    private StoryMode storyMode;

    /**
     * The Highscore in the free play mode
     */
    private Highscore highscore;

    /**
     * The current caesar cipher the user is working with in free play
     */
    private CaesarCipher caesarCipher;

    /**
     * The current vigenere cipher the user is working with in free play
     */
    private VigenereCipher vigenereCipher;

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

    /**
     * Getter for the password of the user.
     * 
     * @return  The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password of the user.
     * 
     * @param password The password to set for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the story mode progress of the user.
     * 
     * @return The story mode progress of the user.
     */
    public StoryMode getStoryMode() {
        return storyMode;
    }

    /**
     * Sets the story mode progress for the user.
     * 
     * @param storyMode The new story mode progress to set for the user.
     */
    public void setStoryMode(StoryMode storyMode) {
        this.storyMode = storyMode;
    }

    /**
     * Gets the highscore in the free play mode.
     * 
     * @return The highscore of the user.
     */
    public Highscore getHighscore() {
        return highscore;
    }

    /**
     * Sets the highscore in the free play mode.
     * 
     * @param highscore The new highscore to set for the user.
     */
    public void setHighscore(Highscore highscore) {
        this.highscore = highscore;
    }

    /**
     * Getter for the caesar cipher of the user.
     * 
     * @return  The caesar cipher of the user.
     */
    public CaesarCipher getCaesarCipher() {
        return caesarCipher;
    }

    /**
     * Setter for the caesar cipher of the user.
     * 
     * @param cipher The caesar cipher to set for the user.
     */
    public void setCaesarCipher(CaesarCipher cipher) {
        this.caesarCipher = cipher;
    }

    /**
     * Getter for the vigenere cipher of the user.
     * 
     * @return  The vigenere cipher of the user.
     */
    public VigenereCipher getVigenereCipher() {
        return vigenereCipher;
    }

    /**
     * Setter for the vigenere cipher of the user.
     * 
     * @param cipher The vigenere cipher to set for the user.
     */
    public void setVigenereCipher(VigenereCipher cipher) {
        this.vigenereCipher = cipher;
    }
    
}
