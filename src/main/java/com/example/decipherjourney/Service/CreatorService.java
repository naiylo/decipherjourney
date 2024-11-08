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
    "THE WIND WHISTLED THROUGH THE TREES AS WE WALKED ALONG THE PATH. IS THIS THE RIGHT WAY? I ASKED. NOBODY ANSWERED. A STRANGE SILENCE SURROUNDED US.",
    
    "SUDDENLY, THERE WAS A LOUD NOISE! WE TURNED TO LOOK, BUT SAW NOTHING. WHO IS THERE? I WHISPERED. ALL WE COULD HEAR WAS ECHOES.",
    
    "SOMETIMES, WE FACE CHALLENGES THAT SEEM IMPOSSIBLE. BUT WE MUST KEEP MOVING FORWARD. OUR STRENGTH AND COURAGE CAN HELP US ACHIEVE GREAT THINGS!",
    
    "THE NIGHT SKY WAS FILLED WITH STARS, EACH ONE SHINING BRIGHTLY. THE AIR WAS COLD AND QUIET. WE FELT A SENSE OF PEACE AND WONDER.",
    
    "AS THE SUN ROSE, WE SAW THE MOUNTAINS AHEAD. WE HAVE ARRIVED! SOMEONE SHOUTED WITH JOY. IT WAS A LONG JOURNEY, BUT WORTH EVERY STEP.",
    
    "OUR JOURNEY TOOK US ACROSS DESERTS AND MOUNTAINS. EVERY STEP BROUGHT US CLOSER TO OUR GOAL. CAN WE MAKE IT? WE ALL WONDERED TOGETHER.",
    
    "ONCE UPON A TIME, IN A FARAWAY LAND, THERE LIVED A BRAVE ADVENTURER. HIS DREAM WAS TO FIND THE LOST CITY HIDDEN IN THE JUNGLE.",
    
    "THE MYSTERIOUS FIGURE APPEARED IN THE MOONLIGHT. WHO COULD IT BE? WE WHISPERED TO EACH OTHER. THE NIGHT FELT FULL OF SECRETS AND HIDDEN DANGERS.",
    
    "THE GREAT FOREST STRETCHED OUT BEFORE US, SILENT AND STILL. WE TOOK A DEEP BREATH AND WALKED FORWARD, NOT KNOWING WHAT WE MIGHT DISCOVER NEXT.",
    
    "THE SUNSET PAINTED THE SKY IN SHADES OF ORANGE AND PURPLE. AS NIGHT FELL, WE GATHERED BY THE FIRE AND SHARED STORIES OF PAST ADVENTURES."
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
