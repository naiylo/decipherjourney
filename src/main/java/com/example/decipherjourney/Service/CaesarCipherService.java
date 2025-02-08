package com.example.decipherjourney.Service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.decipherjourney.Model.*;

/**
 * CaesarCipherService to implement all functions of a caesar cipher instance
 */
@Service
public class CaesarCipherService {

    /**
     * Attribute to get random texts
     */
    @Autowired
    private CreatorService creatorService;  

    /**
     * Function to cipher the given text using Caesar Cipher.
     * 
     * @param text      The text to cipher.
     * @param shift     The shift number for ciphering.
     * @return          The ciphered text.
     */
    public String cipherText(String text, int shift) {
        StringBuilder cipheredText = new StringBuilder();
        
        for (char ch : text.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                // Shift the letter while ensuring it remains a capital letter
                char shifted = (char) ((ch - 'A' + shift) % 26 + 'A');
                cipheredText.append(shifted);
            } else {
                // If the character is a space or punctuation, leave it as is
                cipheredText.append(ch);
            }
        }
        return cipheredText.toString();
    }

    /**
     * Function to decipher the given text using Caesar Cipher.
     * 
     * @param text      The text to decipher.
     * @param shift     The shift number for deciphering.
     * 
     * @return          The deciphered text.
     */
    public String decipherText(String text, int shift) {
        return cipherText(text, 26 - shift); 
    }

    /**
     * Function to decipher the given text using the provided map of the cipher.
     * 
     * @param map   The mapping for the cipher, where each key-value pair represents the substitution.
     * @param text  The text to decipher.
     * @return      The deciphered text.
     */
    public String decipherSpecificLetter(Map<String, String> map, String text) {
        StringBuilder decipheredText = new StringBuilder();

        for (char ch : text.toCharArray()) {
            String charAsString = String.valueOf(ch);

            // Check if the character is a line break, space, or non-alphabet character
            if (ch == '\n' || ch == ' ' || !Character.isLetter(ch)) {
                // Preserve line breaks, spaces, and non-alphabet characters
                decipheredText.append(ch);
            } else if (map.containsKey(charAsString)) {
                // Replace with the mapped value if it exists
                decipheredText.append(map.get(charAsString));
            } else {
                // Substitute missing mappings with an underscore
                decipheredText.append("_");
            }
        }

        return decipheredText.toString();
    }

    /**
     * Function to create a random CaesarCipher object with a random text and shift.
     * 
     * @return  A populated CaesarCipher object.
     */
    public CaesarCipher createRandomCaesarCipher() {
        CaesarCipher cipher = new CaesarCipher();

        // Initialize an empty mapping
        cipher.setMap(new HashMap<>());

        // Get a random example text from CreatorService
        String originalText = creatorService.getRandomTextCaesar();

        // Generate a random shift value between 1 and 25 (not 26 because that would equal a shift of 0)
        int shift = creatorService.getRandomNumber(24) + 1;
        System.out.println("The shift is: " + shift);

        cipher.setOriginalText(originalText);
        cipher.setShiftNumber(shift);
        cipher.setErrorCounter(0);
        
        // Cipher the text
        String cipheredText = cipherText(originalText, shift);
        cipher.setCipheredText(cipheredText);

        return cipher;
    }

    /**
     * Function to create a random CaesarDecipher object with a random text and shift.
     * 
     * @return  A populated CaesarDecipher object.
     */
    public CaesarCipher createRandomCaesarDecipher() {
        CaesarCipher cipher = new CaesarCipher();

        // Initialize an empty mapping
        cipher.setMap(new HashMap<>());

        // Get a random example text from CreatorService
        String originalText = creatorService.getRandomTextCaesar();

        // Generate a random shift value between 1 and 25 (not 26 because that would equal a shift of 0)
        int shift = creatorService.getRandomNumber(24) + 1;
        System.out.println("The shift is: " + shift);

        cipher.setShiftNumber(shift);
        cipher.setErrorCounter(0);
        
        // Cipher the text
        String cipheredText = cipherText(originalText, shift);
        cipher.setCipheredText(originalText);
        cipher.setOriginalText(cipheredText);

        System.out.println(cipher.getCipheredText());
        System.out.println(cipher.getOriginalText());


        return cipher;
    }

    /**
     * Function to create a specific CaesarCipher.
     * 
     * @return  A populated CaesarCipher object.
     */
    public CaesarCipher createSpecificCaesarCipher(String text, Integer shift) {
        CaesarCipher cipher = new CaesarCipher();

        // Initialize an empty mapping
        cipher.setMap(new HashMap<>());

        cipher.setOriginalText(text);
        cipher.setShiftNumber(shift);
        cipher.setErrorCounter(0);
        
        // Cipher the text
        String cipheredText = cipherText(text, shift);
        cipher.setCipheredText(cipheredText);

        return cipher;
    }

    /**
     * Function to increase the error counter by one
     * 
     * @param caesarCipher The cipher where the counter is increased
     */
    public CaesarCipher increaseErrorCounter(CaesarCipher caesarCipher) {
        caesarCipher.setErrorCounter(caesarCipher.getErrorCounter() + 1);
        return caesarCipher;
    } 

    /**
     * Function to increase the hints counter by one
     * 
     * @param caesarCipher The cipher where the hints are increased
     */
    public CaesarCipher increaseHints(CaesarCipher caesarCipher) {
        caesarCipher.setHints(caesarCipher.getHints() + 1);
        return caesarCipher;
    } 
}
