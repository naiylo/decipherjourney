package com.example.decipherjourney.Model;

/**
 * StoryThree class represents the third story in the application.
 * It extends the generalized Story class and adds a specific VigenereCipher attribute.
 * 
 * @author Oskar Schiedewitz
 */
public class StoryThree extends Story {

    /**
     * The Vigenere cipher specific to StoryThree.
     */
    private VigenereCipher vigenereCipher;

    // Getter and Setter methods

    /**
     * Gets the Vigenere cipher associated with StoryThree.
     * 
     * @return The Vigenere cipher of StoryOne.
     */
    public VigenereCipher getVigenereCipher() {
        return vigenereCipher;
    }

    /**
     * Sets the Vigenere cipher associated with StoryThree.
     * 
     * @param vigenereCipher The new Vigenere cipher to set for StoryThree.
     */
    public void setVigenereCipher(VigenereCipher vigenereCipher) {
        this.vigenereCipher = vigenereCipher;
    }
}
