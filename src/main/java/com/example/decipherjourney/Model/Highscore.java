package com.example.decipherjourney.Model;

/**
 * Highscore Class represents all highscores of the user regarding the free play mode
 * 
 * @author Oskar Schiedewitz
 */
public class Highscore {

    /**
     * The current Highscore of the user in using the Caesar cipher
     */
    private Integer caesarHighscore;

    /**
     * The current Highscore of the user in using the Vigenere cipher
     */
    private Integer vigenereHighscore;

    /**
     * The current Highscore of the user in deciphering the Caesar cipher
     */
    private Integer caesarDecipherHighscore;

    /**
     * The current Highscore of the user in deciphering the Vigenere cipher
     */
    private Integer vigenereDecipherHighscore;

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

    /**
     * Gets the high score achieved in deciphering the Caesar cipher.
     * 
     * @return The high score for deciphering the Caesar cipher.
     */
    public Integer getCaesarDecipherHighscore() {
        return caesarDecipherHighscore;
    }

    /**
     * Sets the high score achieved in deciphering the Caesar cipher.
     * 
     * @param caesarDecipherHighscore The high score to set for deciphering the Caesar cipher.
     */
    public void setCaesarDecipherHighscore(Integer caesarDecipherHighscore) {
        this.caesarDecipherHighscore = caesarDecipherHighscore;
    }

    /**
     * Gets the high score achieved in deciphering the Vigenere cipher.
     * 
     * @return The high score for deciphering the Vigenere cipher.
     */
    public Integer getVigenereDecipherHighscore() {
        return vigenereDecipherHighscore;
    }

    /**
     * Sets the high score achieved in deciphering the Vigenere cipher.
     * 
     * @param vigenereDecipherHighscore The high score to set for deciphering the Vigenere cipher.
     */
    public void setVigenereDecipherHighscore(Integer vigenereDecipherHighscore) {
        this.vigenereDecipherHighscore = vigenereDecipherHighscore;
    }
}
