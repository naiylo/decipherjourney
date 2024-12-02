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
    private List<String> exampleTexts = List.of(
        "JEDER MENSCH HAT DAS RECHT SEIN LEBEN NACH SEINEN EIGENEN VORSTELLUNGEN ZU BESCHREITEN.", 

        "KINDER SPIEGELN MEIST DAS AUSSEHEN ODER DAS VERHALTEN IHRER ELTERN WIDER.",
        
        "NEUE, UNBEKANNTE SITUATIONEN SIND NIE EINFACH. VIELE WEGE GEHEN IN DIE RICHTIGE RICHTUNG.",

        "DER TON MACHT DIE MUSIK IST EIN SPRICHWORT, DASS DIE RELEVANZ DER KOMMUNIKATION DEUTLICH MACHT.",

        "WEGE ENTSTEHEN DADURCH, DASS MAN SIE GEHT. DU ALLEIN MUSST DICH ENTSCHEIDEN.",

        "MEIN NAME IST BOND, JAMES BOND. ICH BIN SPION UNTER DER BRITISCHEN KRONE.",

        "ICH KOMME WIEDER, IST EIN KLASSISCHES ZITAT AUS DEM FILM TERMINATOR.",

        "MEINE MUTTER HAT IMMER GESAGT, DAS LEBEN IST WIE EINE SCHACHTEL PRALINEN."
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
    public String getRandomText() {

        int number = this.exampleTexts.size();
        int randomNumber = getRandomNumber(number);
        String text = this.exampleTexts.get(randomNumber);

        return text;
    }
    
}
