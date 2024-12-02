package com.example.decipherjourney.Model;

import java.util.List;

/**
 * StoryMode class represents the story progress of a user in the application.
 * 
 * @author Oskar Schiedewitz
 */
public class StoryMode {

    /**
     * The list of story parts that the user has progressed through or interacted with.
     */
    private List<Object> storyParts;

    /**
     * The current part of the story for the user.
     */
    private String part;

    // Getter and Setter methods

    /**
     * Gets the list of story parts the user has interacted with.
     * 
     * @return The story parts of the user.
     */
    public List<Object> getStoryParts() {
        return storyParts;
    }

    /**
     * Sets the list of story parts the user has interacted with.
     * 
     * @param storyParts The new list of story parts to set for the user.
     */
    public void setStoryParts(List<Object> storyParts) {
        this.storyParts = storyParts;
    }

    /**
     * Adds a story part to the list of story parts.
     * 
     * @param storyPart The new story part to add to the story.
     */
    public void addStoryParts(Object storyPart) {
        this.storyParts.add(storyPart);
    }

    /**
     * Gets the current part of the user's story progress.
     * 
     * @return The part of the user.
     */
    public String getPart() {
        return part;
    }

    /**
     * Sets the current part of the user's story progress.
     * 
     * @param part The new part to set for the user.
     */
    public void setPart(String part) {
        this.part = part;
    }


}
