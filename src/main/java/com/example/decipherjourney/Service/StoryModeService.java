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
            "Kip: Falls du dich nicht mehr daran erinnern kannst, wir befinden uns auf einer Reise durch Zeit und Raum. Ich bin dein mechanischer Begleiter.",
            "Kip: Mein Erbauer, dein Vater, hat uns eine wichtige Mission anvertraut.",
            "Kip: Wir sollen den Tresor am Ende dieser Reise öffnen. Aber der Weg dorthin ist voller Rätsel und Geheimnisse. Gemeinsam werden wir die Schlüssel finden, um die verborgenen Türen der Vergangenheit zu entriegeln.",
            "Kip: Unsere erste Station ist das alte Rom. Dort liegt der Ursprung einer der faszinierendsten Verschlüsselungsmethoden: der Caesar-Chiffre.",
            "Kip: Bist du bereit, in die Welt der alten Römer einzutauchen und die Geheimnisse zu entschlüsseln, die Julius Caesar selbst genutzt hat?",
            "Kip: Die Caesar-Chiffre ist eine Verschlüsselungstechnik, bei der Buchstaben im Alphabet um eine bestimmte Anzahl von Stellen verschoben werden. Einfach, aber genial! In Rom werden wir herausfinden, wie sie funktioniert, indem wir selbst Rätsel lösen.",
            "Kip: Ich öffne nun das Portal. Halte dich fest, Reisender – es wird ein wenig holprig!",
            "Kip: Auf geht's nach Rom! Aber vergiss nicht, ich bin hier, um dir zu helfen, falls du nicht weiterkommst. Los geht’s!"
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
