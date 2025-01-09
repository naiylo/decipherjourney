package com.example.decipherjourney.Model;

/**
 * StoryTwo class represents the second story in the application.
 * It extends the generalized Story class and adds a specific CaesarCipher attribute.
 * 
 * @author Oskar Schiedewitz
 */
public class StoryTwo extends Story {

    /**
     * The Caesar cipher specific to StoryOne.
     */
    private CaesarCipher caesarCipher;

    // Getter and Setter methods

    /**
     * Gets the Caesar cipher associated with StoryOne.
     * 
     * @return The Caesar cipher of StoryOne.
     */
    public CaesarCipher getCaesarCipher() {
        return caesarCipher;
    }

    /**
     * Sets the Caesar cipher associated with StoryOne.
     * 
     * @param caesarCipher The new Caesar cipher to set for StoryOne.
     */
    public void setCaesarCipher(CaesarCipher caesarCipher) {
        this.caesarCipher = caesarCipher;
    }
}
