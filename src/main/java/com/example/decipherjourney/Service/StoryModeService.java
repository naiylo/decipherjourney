package com.example.decipherjourney.Service;

import com.example.decipherjourney.Model.StoryMode;
import com.example.decipherjourney.Model.StoryOne;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * StoryModeService handles the initialization and progression of stories 
 * within the application. It provides functionality to move between story parts
 * and manage the user's progress.
 * 
 * @author Oskar Schiedewitz
 */
@Service
public class StoryModeService {

    /**
     * Initializes the story mode with story parts.
     */
    public StoryMode initializeStoryParts() {
        StoryOne storyOne = new StoryOne();

        // Initialize all of the story parts
        
        storyOne.setDialogs(List.of(List.of(
            "Unbekannter Roboter: Guten Morgen, Reisender! Kannst du mich hören? Mein Name ist Kip. ≧◉◡◉≦",
            "Kip: Falls du dich nicht mehr daran erinnern kannst, wir befinden uns auf einer Reise durch Zeit und Raum und ich bin dein mechanischer Begleiter.",
            "Kip: Mein Erbauer, dein Vater, hat uns eine wichtige Mission anvertraut.",
            "Kip: Wir sollen den Tresor hier neben mir an der Wand öffnen. Aber der Weg dorthin ist voller Rätsel und Geheimnisse.",
            "Kip: Die einzelnen Türen des Tresors lassen sich durch das Lösen von Chiffren öffnen.",
            "Kip: Was Chiffren sind? Okay ich probiere es dir zu erklären!",
            "Kip: Chiffren sind dafür da um Texte zu verschlüsseln beziehungsweise zu entschlüsseln.",
            "Kip: Stell dir vor du hast das Wort Roboter. Wenn wir jetzt jeden einzelnen Buchstaben um zwei verschieben. Wird das R zu einem T und immer so weiter.",
            "Kip: Wir haben dann das Wort Roboter mit Tqdqvgt ersetzt und es nicht mehr so leicht erkennbar welches Wort wohl dahinter steckt.",
            "Kip: Unsere erste Station ist das alte Rom. Dort liegt der Ursprung einer der faszinierendsten Verschlüsselungsmethoden: der Caesar-Chiffre.",
            "Kip: Bist du bereit, in die Welt der alten Römer einzutauchen und die Geheimnisse zu entschlüsseln, die Julius Caesar selbst genutzt hat?",
            "Kip: Die Caesar-Chiffre ist eine Verschlüsselungstechnik, bei der Buchstaben im Alphabet um eine bestimmte Anzahl von Stellen verschoben werden. Genau so wie ich es dir gerade kurz geziegt habe!",
            "Kip: In Rom werden wir herausfinden, wie sie funktioniert, indem wir selbst Rätsel lösen.",
            "Kip: Lass uns hier rüber gehen und ich öffne das Portal. Halte dich fest, Reisender – es wird ein wenig holprig!",
            "Kip: Auf geht's nach Rom! Und vergiss nicht Reisender ich bin hier um dir zu helfen. Los geht’s!"
        )
        ));
        storyOne.setStoryParts(List.of(
            "Kapitel 1: Das Erwachen"
        ));
        storyOne.setCheckpoint("Teil 1: Der Anfang einer Freundschaft");

        StoryMode storyMode = new StoryMode();
    
        List<Object> partList = new ArrayList<>();
        partList.add(storyOne);
        System.out.println("Added Part 1.");

        storyMode.setStoryParts(partList);
        storyMode.setPart("0");

        return storyMode;
    }

    /**
     * Gets the current story part the user is on.
     * 
     * @param StoryMode storyMode The storyMode of the user.
     * 
     * @return The current story part as an Object.
     */
    public int getCurrentStoryPart(StoryMode storyMode) {
        return Integer.parseInt(storyMode.getPart());
    }

    /**
     * Moves to the next story part if available.
     * 
     * @param StoryMode storyMode The storyMode of the user.
     * 
     * @return True if the user successfully moved to the next part, False if no more parts.
     */
    public boolean nextStoryPart(StoryMode storyMode) {
        try {
            int currentPart = getCurrentStoryPart(storyMode);
            currentPart += 1;
            storyMode.setPart(Integer.toString(currentPart));
            return true;

        } catch(Exception e) {
            return false;
        }
    }

    /**
     * Resets the story to the beginning.
     */
    public void resetStory() {
        initializeStoryParts();
    }

}
