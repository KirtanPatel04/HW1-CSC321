package HW2;

import java.util.ArrayList;

/**
 * Class that models a Dulcimer. The dulcimer consists of bass, treble1, and treble2 strings.
 * Each string is represented by a DulcimerString object.
 * 
 * @author Kirtan Patel
 * @version 09/21/2024 
 */
public class Dulcimer {
    // ArrayList to hold the bass strings of the dulcimer
    public ArrayList<DulcimerString> bassStrings;
    // ArrayList to hold the treble1 strings of the dulcimer
    public ArrayList<DulcimerString> treble1Strings;
    // ArrayList to hold the treble2 strings of the dulcimer
    public ArrayList<DulcimerString> treble2Strings;
    
    /**
     * Constructs a Dulcimer with the specified bass, treble1, and treble2 strings.
     * Each note is passed as a string, split, and used to create corresponding DulcimerString objects.
     * 
     * @param bassNotes a string specifying the bass notes, from bottom to top
     * @param treble1Notes a string specifying the treble1 notes
     * @param treble2Notes a string specifying the treble2 notes
     */
    public Dulcimer(String bassNotes, String treble1Notes, String treble2Notes) {
        // Initialize the ArrayList for bass strings and create DulcimerString objects for each note
        this.bassStrings = new ArrayList<>();
        for (String str : bassNotes.split("\\s+")) { // Split by whitespace to get individual notes
            this.bassStrings.add(new DulcimerString(str)); // Add each string to the bassStrings list
        }

        // Initialize the ArrayList for treble1 strings and create DulcimerString objects for each note
        this.treble1Strings = new ArrayList<>();
        for (String str : treble1Notes.split("\\s+")) { // Split by whitespace to get individual notes
            this.treble1Strings.add(new DulcimerString(str)); // Add each string to the treble1Strings list
        }

        // Initialize the ArrayList for treble2 strings and create DulcimerString objects for each note
        this.treble2Strings = new ArrayList<>();
        for (String str : treble2Notes.split("\\s+")) { // Split by whitespace to get individual notes
            this.treble2Strings.add(new DulcimerString(str)); // Add each string to the treble2Strings list
        }
    }
    
    /**
     * Strikes the specified string and sets it to vibrating based on the string type.
     * 
     * @param stringNum the string number (starting from 0 at the bottom, for each section: bass, treble1, and treble2)
     * @param stringType the type of string ("bass", "treble1", or "treble2")
     */
    public void hammer(int stringNum, String stringType) {
        // Handle striking a bass string
        if (stringType.equals("bass") && stringNum >= 0 && stringNum < this.bassStrings.size()) {
            this.bassStrings.get(stringNum).strike(); // Strike the specified bass string
        }
        // Handle striking a treble1 string
        else if (stringType.equals("treble1") && stringNum >= 0 && stringNum < this.treble1Strings.size()) {
            this.treble1Strings.get(stringNum).strike(); // Strike the specified treble1 string
        }
        // Handle striking a treble2 string
        else if (stringType.equals("treble2") && stringNum >= 0 && stringNum < this.treble2Strings.size()) {
            this.treble2Strings.get(stringNum).strike(); // Strike the specified treble2 string
        }
    }

    /**
     * Plays the sounds corresponding to all of the struck strings.
     * This combines the frequencies from bass, treble1, and treble2 strings and plays them through the speaker.
     */
    public void play() {
        double combinedFrequencies = 0.0;  // Variable to hold the sum of all string samples (frequencies)

        // Sum the samples from all bass strings
        for (int i = 0; i < this.bassStrings.size(); i++) {
            combinedFrequencies += this.bassStrings.get(i).sample(); // Add each bass string sample to combinedFrequencies
        }

        // Sum the samples from all treble1 strings
        for (int i = 0; i < this.treble1Strings.size(); i++) {
            combinedFrequencies += this.treble1Strings.get(i).sample(); // Add each treble1 string sample to combinedFrequencies
        }

        // Sum the samples from all treble2 strings
        for (int i = 0; i < this.treble2Strings.size(); i++) {
            combinedFrequencies += this.treble2Strings.get(i).sample(); // Add each treble2 string sample to combinedFrequencies
        }

        // Play the combined frequencies through the audio system
        StdAudio.play(combinedFrequencies);
    }
}
