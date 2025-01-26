package com.example.decipherjourney.Service;

import org.springframework.stereotype.Service;
import com.example.decipherjourney.Model.Highscore;

/**
 * HighscoreService handles the initialization and progression of highscores
 * within the application. It provides functionality to move between story parts
 * and manage the user's progress.
 * 
 * @author Oskar Schiedewitz
 */
@Service
public class HighscoreService {

    private static final int MAX_HIGHSCORE = 999;

    /**
     * Initializes a new Highscore object with default values.
     * 
     * @return A Highscore object with default values (0 for both caesarHighscore and vigenereHighscore).
     */
    public Highscore initializeHighscore() {
        Highscore highscore = new Highscore();
        highscore.setCaesarHighscore(0);
        highscore.setVigenereHighscore(0);
        return highscore;
    }

    /**
     * Calculates the highscore based on the given number of errors and hints.
     * 
     * The formula deducts points from the maximum score based on the number of errors and hints.
     * Each error deducts 10 points, and each hint deducts 20 points.
     * 
     * @param errors The number of errors made by the user.
     * @param hints The number of hints used by the user.
     * @return The calculated highscore, with a minimum value of 0.
     */
    public int calculateHighscore(int errors, int hints) {
        int deduction = (errors * 10) + (hints * 20);
        return Math.max(0, MAX_HIGHSCORE - deduction);
    }

    /**
     * Calculate the Caesar Cipher highscore for the user.
     * 
     * @param highscore The Highscore object of the user.
     * @param errors The number of errors made by the user.
     * @param hints The number of hints used by the user.
     * 
     * @return The new highest highscore.
     */
    public Highscore updateCaesarHighscore(Highscore highscore, Integer errors, Integer hints) {
        int newHighscore = calculateHighscore(errors, hints);
        Integer currentHighscore = highscore.getCaesarHighscore();

        // Update the highscore only if the new one is higher
        highscore.setCaesarHighscore(Math.max(newHighscore, currentHighscore));

        return highscore;
    }

    /**
     * Calculate the Viginere Cipher highscore for the user.
     * 
     * @param highscore The Highscore object of the user.
     * @param errors The number of errors made by the user.
     * @param hints The number of hints used by the user.
     * 
     * @return The new highest highscore.
     */
    public Highscore  updateVigenereHighscore(Highscore highscore, Integer errors, Integer hints) {
        int newHighscore = calculateHighscore(errors, hints);
        Integer currentHighscore = highscore.getVigenereHighscore();

        // Update the highscore only if the new one is higher
        highscore.setVigenereHighscore(Math.max(newHighscore, currentHighscore));

        return highscore;
    }
}
