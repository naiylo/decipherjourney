package com.example.decipherjourney.Model;

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


}
