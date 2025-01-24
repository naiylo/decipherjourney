package com.example.decipherjourney.Model;

/**
 * VigenereCipher Class represents a gamestate of a Vigenère cipher instance.
 * 
 * @author 
 */
public class VigenereCipher {

    /**
     * The keyword used for the Vigenère cipher.
     */
    private String keyword;

    /**
     * The original text before encryption.
     */
    private String originalText;

    /**
     * The ciphered text after encryption.
     */
    private String cipheredText;

    /**
     * The error counter for failed attempts
     */
    private Integer errorCouter;

    /**
     * Gets the keyword used for the Vigenère cipher.
     * 
     * @return The keyword for this cipher instance.
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Sets the keyword for the Vigenère cipher.
     * 
     * @param keyword The keyword to set for encryption.
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

}