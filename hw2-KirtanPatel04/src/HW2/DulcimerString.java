package HW2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a single string on the dulcimer, capable of vibrating and producing sound.
 * The string is modeled as a queue of displacements, simulating the string's vibration using the Karplus-Strong algorithm.
 * 
 * @author Kirtan Patel 
 * @version 09/21/2024
 */
public class DulcimerString {
    private String note;
    private Queue<Double> displacements;
    private static final double DECAY = 0.996;
    private static final int SAMPLE_RATE = 44100;

    /**
     * Constructs a DulcimerString with a specific note.
     * The queue size is determined by the note's frequency.
     * 
     * @param note the musical note this string represents
     */
    public DulcimerString(String note) {
        this.note = note;
        int N = calculateQueueSize(note);
        displacements = new LinkedList<>();
        
        // Initialize the queue with N zeros
        for (int i = 0; i < N; i++) {
            displacements.add(0.0);
        }
    }

    /**
     * Returns the musical note of this string.
     * 
     * @return the note as a string
     */
    public String getNote() {
        return this.note;
    }

    /**
     * Calculates the offset of this string's note from middle C.
     * Takes into account the octave notation (+ or -) in the note.
     * 
     * @return the offset from middle C
     */
    public int getOffsetFromMiddleC() {
        String[] chromaticScale = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
        
        // Remove octave notation (+ or -)
        String noteWithoutOctave = note.replace("+", "").replace("-", "");
        
        // Find the index of the note in the chromatic scale
        int index = -1;
        for (int i = 0; i < chromaticScale.length; i++) {
            if (chromaticScale[i].equals(noteWithoutOctave)) {
                index = i;
                break;
            }
        }
        
        // C is the 3rd index, so subtract 3
        int offset = index - 3;
        
        // Adjust for octave notation
        if (note.contains("+")) {
            offset += 12;
        } else if (note.contains("-")) {
            offset -= 12;
        }
        
        return offset;
    }

    /**
     * Simulates striking the string, filling the displacement queue with random values.
     * This simulates the initial excitation of the string when it is hammered.
     */
    public void strike() {
        int N = displacements.size();
        displacements.clear();
        
        // Replace the queue with random values between -0.5 and 0.5
        for (int i = 0; i < N; i++) {
            double randomValue = Math.random() - 0.5;  // Range [-0.5, 0.5)
            displacements.add(randomValue);
        }
    }

    /**
     * Returns the current sample from the displacement queue and updates it with a new value.
     * The new value is calculated using the Karplus-Strong algorithm's decay factor.
     * 
     * @return the current sample (the first value in the queue before updating)
     */
    public double sample() {
        // Get the first two values
        double first = displacements.poll(); // Remove and return the first value
        double second = displacements.peek(); // Peek at the second value
        
        // Calculate the new value to be added
        double newValue = DECAY * 0.5 * (first + second);
        
        // Add the new value to the end of the queue
        displacements.add(newValue);
        
        // Return the first value (which was removed)
        return first;
    }

    /**
     * Calculates the size of the displacement queue based on the frequency of the note.
     * The size is determined by the sample rate and the note's frequency.
     * 
     * @param note the musical note
     * @return the size of the displacement queue
     */
    private int calculateQueueSize(String note) {
        int offset = getOffsetFromMiddleC();
        double frequency = 440 * Math.pow(2, offset / 12.0);
        return (int) Math.round(SAMPLE_RATE / frequency);
    }

    /**
     * Returns a string representation of the DulcimerString object.
     * Useful for debugging purposes.
     * 
     * @return a string representing the note and the current state of the displacement queue
     */
    @Override
    public String toString() {
        return "Note: " + note + ", Queue: " + displacements.toString();
    }
}