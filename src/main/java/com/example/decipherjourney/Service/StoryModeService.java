package com.example.decipherjourney.Service;

import com.example.decipherjourney.Model.StoryMode;
import com.example.decipherjourney.Model.StoryOne;

import java.util.ArrayList;
import java.util.List;

/**
 * StoryModeService handles the initialization and progression of stories 
 * within the application. It provides functionality to move between story parts
 * and manage the user's progress.
 * 
 * @author Oskar Schiedewitz
 */
public class StoryModeService {

    // Represents the current story mode
    private StoryMode storyMode;

    /**
     * Constructor initializes the StoryModeService with default story parts.
     */
    public StoryModeService() {
        initializeStoryParts();
    }

    /**
     * Initializes the story mode with story parts.
     */
    private void initializeStoryParts() {
        StoryOne storyOne = new StoryOne();

        // Initialize all of the story parts
        
        storyOne.setDialogs(List.of(
            "Welcome to the journey!",
            "Your first challenge awaits...",
            "Decipher the code to proceed."
        ));
        storyOne.setStoryParts(List.of(
            "Part 1: Introduction",
            "Part 2: The Challenge",
            "Part 3: The Revelation"
        ));
        storyOne.setCheckpoint("Part 1: Introduction");

        StoryMode storyMode = new StoryMode();
    
        List<Object> partList = new ArrayList<>();
        partList.add(storyOne);
        storyMode.setPart("0");

        this.storyMode = storyMode;
    }

    /**
     * Gets the current story part the user is on.
     * 
     * @return The current story part as an Object.
     */
    public int getCurrentStoryPart() {
        return Integer.parseInt(this.storyMode.getPart());
    }

    /**
     * Moves to the next story part if available.
     * 
     * @return True if the user successfully moved to the next part, False if no more parts.
     */
    public boolean nextStoryPart() {
        try {
            int currentPart = getCurrentStoryPart();
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
