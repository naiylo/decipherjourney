package com.example.decipherjourney.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Random;

/**
 * Creator Service to implement all functions that are needed to create or chose random anspects for a cipher
 * 
 * @author Oskar Schiedewitz
 */
@Service
public class CreatorService {

    /**
     * Attribute to save all the possible texts
     */
    private List<String> exampleTextsCaesar = List.of(
        "ROBOTER",
        "KRYPTOGRAHIE",
        "SOFTWARE",
        "PUZZLE",
        "LONDON",
        "ROM",
        "FLIEGEN",
        "FERTIG",
        "ESSEN"
    );

    /**
     * Attribute to save all the possible texts
     */
    private List<String> exampleTextsVigenere = List.of(
        "ROBOTER",
        "KRYPTOGRAHIE",
        "SOFTWARE",
        "PUZZLE",
        "LONDON",
        "ROM",
        "FLIEGEN",
        "FERTIG",
        "ESSEN"
    );


    /**
     * Function to get a random number between zero and the input 
     * 
     * @param max   The maximum number possible.
     * 
     * @return      Random number between zero and max.
     */
    public int getRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    /**
     * Function to get a random text out of the examples
     * 
     * @return String out of the exampleText choosen by a random generator.
     */
    public String getRandomTextCaesar() {

        int number = this.exampleTextsCaesar.size();
        int randomNumber = getRandomNumber(number);
        String text = this.exampleTextsCaesar.get(randomNumber);

        return text;
    }

    /**
     * Function to get a random text out of the examples
     * 
     * @return String out of the exampleText choosen by a random generator.
     */
    public String getRandomTextVigenere() {

        int number = this.exampleTextsVigenere.size();
        int randomNumber = getRandomNumber(number);
        String text = this.exampleTextsVigenere.get(randomNumber);

        return text;
    }

    /**
     * Function to get a random keyword out of the examples.
     * 
     * @return A randomly chosen keyword from the list of example keywords.
     */
    public String getRandomKeyword() {

        // List of example keywords
        List<String> keywords = List.of("A", "AB", "ABC", "KEY", "CBA", "TRY", "KIP");

        int numberOfKeywords = keywords.size();
        int randomIndex = getRandomNumber(numberOfKeywords); // Generate a random index
        String keyword = keywords.get(randomIndex); // Retrieve the keyword at the random index

        return keyword;
    }
    
}
