package com.example.decipherjourney.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.decipherjourney.Model.*;

/**
 * VigenereCipherService to implement all functions of a vigenere cipher instance
 */
@Service
public class VigenereCipherService {

    /**
     * Attribute to get random texts
     */
    @Autowired
    private CreatorService creatorService;  

    /**
     * Function to cipher the given text using Vigenere Cipher.
     * 
     * @param text    The text to cipher.
     * @param keyword The keyword to use for ciphering.
     * @return        The ciphered text.
     */
    public String cipherText(String text, String keyword) {
        StringBuilder cipheredText = new StringBuilder();
        keyword = keyword.toUpperCase();
        int keywordIndex = 0;
    
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) { 
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                
                int shift = 'Z' - keyword.charAt(keywordIndex);
                // Buchstaben verschieben und sicherstellen, dass sie im Alphabet bleiben
                char shifted = (char) ((ch - base + shift) % 26 + base);
                cipheredText.append(shifted);
    
                // Zum nächsten Buchstaben im Schlüssel wechseln
                keywordIndex = (keywordIndex + 1) % keyword.length();
            } else {
                // Nicht-Buchstaben unverändert lassen
                cipheredText.append(ch);
            }
        }
        return cipheredText.toString();
    }

    /**
     * Function to decipher the given text using Vigenere Cipher.
     * 
     * @param text      The text to decipher.
     * @param keyword   The keyword used for ciphering/deciphering.
     * 
     * @return          The deciphered text.
     */
    public String decipherText(String text, String keyword) {
        StringBuilder decipheredText = new StringBuilder();
        keyword = keyword.toUpperCase(); 
        int keywordIndex = 0;
    
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) { 
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                int shift = 'Z' - keyword.charAt(keywordIndex);
                // Buchstaben zurückverschieben und sicherstellen, dass sie im Alphabet bleiben
                char shifted = (char) ((ch - base - shift + 26) % 26 + base);
                decipheredText.append(shifted);
    
                // Zum nächsten Buchstaben im Schlüssel wechseln
                keywordIndex = (keywordIndex + 1) % keyword.length();
            } else {
                // Nicht-Buchstaben unverändert lassen
                decipheredText.append(ch);
            }
        }
        return decipheredText.toString();
    }

    /**
     * Function to create a random VigenereCipher object with a random text and keyword.
     * 
     * @return  A populated VigenereCipher object.
     */
    public VigenereCipher createRandomVigenereCipher() {
        VigenereCipher cipher = new VigenereCipher();

        // Get a random example text from CreatorService
        String originalText = creatorService.getRandomTextVigenere();

        String keyword = creatorService.getRandomKeyword();
        System.out.println("The keyword is: " + keyword);

        cipher.setOriginalText(originalText);
        cipher.setKeyword(keyword);
        cipher.setErrorCounter(0);
        cipher.setHints(0);
        
        // Cipher the text
        String cipheredText = cipherText(originalText, keyword);
        cipher.setCipheredText(cipheredText);

        return cipher;
    }

    /**
     * Function to create a random VigenereDecipher object with a random text and keyword.
     * 
     * @return  A populated VigenereDecipher object.
     */
    public VigenereCipher createRandomVigenereDecipher() {
        VigenereCipher cipher = new VigenereCipher();

        // Get a random example text from CreatorService
        String originalText = creatorService.getRandomTextVigenere();

        String keyword = creatorService.getRandomKeyword();
        System.out.println("The keyword is: " + keyword);

        cipher.setKeyword(keyword);
        cipher.setErrorCounter(0);
        cipher.setHints(0);
        
        // Cipher the text
        String cipheredText = cipherText(originalText, keyword);
        cipher.setCipheredText(originalText);
        cipher.setOriginalText(cipheredText);

        return cipher;
    }

    /**
     * Function to create a specific VigenereCipher.
     * 
     * @return  A populated VigenereCipher object.
     */
    public VigenereCipher createSpecificVigenereCipher(String text, String keyword) {
        VigenereCipher cipher = new VigenereCipher();

        cipher.setOriginalText(text);
        cipher.setKeyword(keyword);
        
        // Cipher the text
        String cipheredText = cipherText(text, keyword);
        cipher.setCipheredText(cipheredText);

        return cipher;
    }

    /**
     * Function to increase the error counter by one
     * 
     * @param vigenereCipher The cipher where the counter
     */
    public VigenereCipher increaseErrorCounter(VigenereCipher vigenereCipher) {
        vigenereCipher.setErrorCounter(vigenereCipher.getErrorCounter() + 1);
        return vigenereCipher;
    } 

    /**
     * Function to increase the hints counter by one
     * 
     * @param vigenereCipher The cipher where the hints are increased
     */
    public VigenereCipher increaseHints(VigenereCipher vigenereCipher) {
        vigenereCipher.setHints(vigenereCipher.getHints() + 1);
        return vigenereCipher;
    } 
}
