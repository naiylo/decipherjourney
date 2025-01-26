package com.example.decipherjourney.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Freeplay Class represents a gamestate of a freeplay instance
 * 
 * @author Oskar Schiedewitz
 */
public class CaesarCipher {

    /**
     * The shift number 
     */
    private Integer shiftNumber;

    /**
     * The original text
     */
    private String originalText;

    /**
     * The ciphered text
     */
    private String cipheredText;

    /**
     * The error counter for failed attempts
     */
    private Integer errorCouter;

    /**
     * The counter for the amount of hints
     */
    private Integer hints;

    /**
     * The supposed dictionary to translate without the shift
     */
    private Map<String, String> map;

    /**
     * Gets the shift number used for the Caesar cipher.
     * 
     * @return The shift number for this cipher instance.
     */
    public Integer getShiftNumber() {
        return shiftNumber;
    }

    /**
     * Sets the shift number for the Caesar cipher.
     * 
     * @param shiftNumber The shift value to set for encryption.
     */
    public void setShiftNumber(Integer shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    /**
     * Gets the original text before encryption.
     * 
     * @return The original text of this cipher instance.
     */
    public String getOriginalText() {
        return originalText;
    }

    /**
     * Sets the original text for encryption.
     * 
     * @param originalText The text to set as the original input.
     */
    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    /**
     * Gets the ciphered text after encryption.
     * 
     * @return The encrypted text of this cipher instance.
     */
    public String getCipheredText() {
        return cipheredText;
    }

    /**
     * Sets the ciphered text after encryption.
     * 
     * @param cipheredText The text to set as the encrypted output.
     */
    public void setCipheredText(String cipheredText) {
        this.cipheredText = cipheredText;
    }

    /**
     * Gets the error counter for failed attempts.
     * 
     * @return The current value of the error counter.
     */
    public Integer getErrorCounter() {
        return errorCouter;
    }

    /**
     * Sets the error counter for failed attempts.
     * 
     * @param errorCounter The value to set for the error counter.
     */
    public void setErrorCounter(Integer errorCounter) {
        this.errorCouter = errorCounter;
    }

    /**
     * Gets the counter for the amount of hints.
     * 
     * @return The current value of the hints counter.
     */
    public Integer getHints() {
        return hints;
    }

    /**
     * Sets the counter for the amount of hints.
     * 
     * @param hints The value to set for the hints counter.
     */
    public void setHints(Integer hints) {
        this.hints = hints;
    }

    /**
     * Gets the supposed map.
     * 
     * @return The supposed map.
     */
    public Map<String, String> getMap() {
        Map<String, String> fullMap = new HashMap<>();
            for (char letter = 'A'; letter <= 'Z'; letter++) {
                String key = String.valueOf(letter);
                fullMap.put(key, map.getOrDefault(key, "")); 
            }
            return fullMap;
        }

    /**
     * Sets the supposed map.
     * 
     * @param newMap The new map to set.
     */
    public void setMap(Map<String, String> newMap) {
        this.map = newMap;
    }


}
