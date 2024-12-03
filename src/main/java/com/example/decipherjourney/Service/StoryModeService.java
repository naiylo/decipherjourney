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
            "Unbekannter Roboter: Guten Morgen Reisender, kannst du mich hören? Mein Name ist Kip. ≧◉◡◉≦",
            "Kip: Falls du dich nicht mehr daran erinner kannst, wir sind zusammen auf einer Reise durch Zeit und Raum.",
            "Kip: Ich bin dein mechanischer Begleiter und zusammen erforschen wir die Tiefen der Galaxy. Dein Vater, Mein Erbauer hat uns für diese lange Reise ein Aufgabe gegeben.",
            "Kip: Wir sollen den Tresor am Ende dieses Raumes irgendwie aufbekommen. ",
            "Kip: Dazu müssen wir Schritt für Schritt verschieden Rätsel Lösen. Doch bis jetzt weiß ich auch nicht wie wir das Schaffen sollen? "
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
