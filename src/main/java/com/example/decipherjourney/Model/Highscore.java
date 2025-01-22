package com.example.decipherjourney.Model;

/**
 * Highscore Class represents all highscores of the user regarding the free play mode
 * 
 * @author Oskar Schiedewitz
 */
public class Highscore {

    /**
     * The current Highscore of the user in using the caesar cipher
     */
    private Integer caesarHighscore;

    /**
     * The current Highscore of the user in using the vigenere cipher
     */
    private Integer vigenereHighscore;

    /**
     * Gets the high score achieved in using the Caesar cipher.
     * 
     * @return The high score for the Caesar cipher game.
     */
    public Integer getCaesarHighscore() {
        return caesarHighscore;
    }

    /**
     * Sets the high score achieved in using the Caesar cipher.
     * 
     * @param caesarHighscore The high score to set for the Caesar cipher game.
     */
    public void setCaesarHighscore(Integer caesarHighscore) {
        this.caesarHighscore = caesarHighscore;
    }

    /**
     * Gets the high score achieved in using the Vigenere cipher.
     * 
     * @return The high score for the Vigenere cipher game.
     */
    public Integer getVigenereHighscore() {
        return vigenereHighscore;
    }

    /**
     * Sets the high score achieved in using the Vigenere cipher.
     * 
     * @param vigenereHighscore The high score to set for the Vigenere cipher game.
     */
    public void setVigenereHighscore(Integer vigenereHighscore) {
        this.vigenereHighscore = vigenereHighscore;
    }
    
}
