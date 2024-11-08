package com.example.decipherjourney.Service;

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
     * Function to create a random CaesarCipher object with a random text and shift.
     * 
     * @return  A populated CaesarCipher object.
     */
    public CaesarCipher createRandomCaesarCipher() {
        CaesarCipher cipher = new CaesarCipher();

        // Get a random example text from CreatorService
        String originalText = creatorService.getRandomText();

        // Generate a random shift value between 1 and 25 (not 26 because that would equal a shift of 0)
        int shift = creatorService.getRandomNumber(24) + 1;
        System.out.println("The shift is: " + shift);

        cipher.setOriginalText(originalText);
        cipher.setShiftNumber(shift);
        
        // Cipher the text
        String cipheredText = cipherText(originalText, shift);
        cipher.setCipheredText(cipheredText);

        return cipher;
    }

    /**
     * Function to create a specific CaesarCipher.
     * 
     * @return  A populated CaesarCipher object.
     */
    public CaesarCipher createSpecificCaesarCipher(String text, Integer shift) {
        CaesarCipher cipher = new CaesarCipher();

        cipher.setOriginalText(text);
        cipher.setShiftNumber(shift);
        
        // Cipher the text
        String cipheredText = cipherText(text, shift);
        cipher.setCipheredText(cipheredText);

        return cipher;
    }

}
