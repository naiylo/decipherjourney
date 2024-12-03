package com.example.decipherjourney.Model;

import java.util.List;

/**
 * Story class represents a generalized structure for any story in the application.
 * It contains shared attributes like checkpoint, dialogs, and story parts.
 * 
 * @author Oskar Schiedewitz
 */
public abstract class Story {

    /**
     * The current checkpoint within the story.
     */
    private String checkpoint;

    /**
     * The list of dialogs included in the story.
     */
    private List<List<String>> dialogs;

    /**
     * The list of story parts representing the story's progression.
     */
    private List<String> storyParts;

    // Getter and Setter methods

    /**
     * Gets the current checkpoint within the story.
     * 
     * @return The checkpoint of the story.
     */
    public String getCheckpoint() {
        return checkpoint;
    }

    /**
     * Sets the current checkpoint within the story.
     * 
     * @param checkpoint The new checkpoint to set for the story.
     */
    public void setCheckpoint(String checkpoint) {
        this.checkpoint = checkpoint;
    }

    /**
     * Gets the list of dialogs included in the story.
     * 
     * @return The dialogs of the story.
     */
    public List<List<String>> getDialogs() {
        return dialogs;
    }

    /**
     * Sets the list of dialogs included in the story.
     * 
     * @param dialogs The new dialogs to set for the story.
     */
    public void setDialogs(List<List<String>> dialogs) {
        this.dialogs = dialogs;
    }

    /**
     * Gets the list of story parts representing the story's progression.
     * 
     * @return The story parts of the story.
     */
    public List<String> getStoryParts() {
        return storyParts;
    }

    /**
     * Sets the list of story parts representing the story's progression.
     * 
     * @param storyParts The new story parts to set for the story.
     */
    public void setStoryParts(List<String> storyParts) {
        this.storyParts = storyParts;
    }
}
