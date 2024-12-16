package HW2;

import java.awt.Font;

/**
 * Driver class for the keyboard-based virtual dulcimer.
 * Now includes treble1 and treble2 strings, in addition to bass strings.
 * 
 * The program displays the key mappings for bass, treble1, and treble2 strings on the screen,
 * and plays the corresponding dulcimer sounds when keys are pressed.
 * 
 * @author Kirtan Patel
 * @version 09/24/2024 
 */
public class DulcimerDriver {

    /**
     * Main method to start the dulcimer application.
     * It sets up the dulcimer strings and the key-to-note mappings, 
     * and listens for key presses to simulate the hammering of dulcimer strings.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Key mappings for each row of the dulcimer strings
        String bassKeys =    "a   s   d   f   g   h   j   k   l   ;   '";       
        String treble1Keys = "1   2   3   4   5   6   7   8   9   0   -   =";
        String treble2Keys = "q   w   e   r   t   y   u   i   o   p   [   ]";

        // Corresponding notes for the bass, treble1, and treble2 strings
        String bassNotes = "G-  A   B   C   D   E   F   G   A+ A#+  C+";
        String treble1Notes = "D++ C++ B++ A++ G+  F#+ E+  D+  C#+ B+  A+  G#";
        String treble2Notes = "G+  F#+ E+  D+  C+  B+  A+  G   F#  E   D   C#";

        // Set the font for displaying key mappings and notes
        StdDraw.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        // Display the dulcimer key mappings on the window
        StdDraw.textLeft(0.00, 1.00, "DULCIMER KEY MAPPINGS");

        // Display bass keys and notes
        StdDraw.textLeft(0.00, 0.90, "        keys:  " + bassKeys);
        StdDraw.textLeft(0.00, 0.87, "BASS          --- --- --- --- --- --- --- --- --- --- ---");
        StdDraw.textLeft(0.00, 0.84, "       notes:  " + bassNotes);

        // Display treble1 keys and notes
        StdDraw.textLeft(0.00, 0.75, "        keys:  " + treble1Keys);
        StdDraw.textLeft(0.00, 0.72, "TREBLE1       --- --- --- --- --- --- --- --- --- --- --- ---");
        StdDraw.textLeft(0.00, 0.69, "       notes:  " + treble1Notes);

        // Display treble2 keys and notes
        StdDraw.textLeft(0.00, 0.60, "        keys:  " + treble2Keys);
        StdDraw.textLeft(0.00, 0.57, "TREBLE2       --- --- --- --- --- --- --- --- --- --- --- ---");
        StdDraw.textLeft(0.00, 0.54, "       notes:  " + treble2Notes);

        // Create a Dulcimer object with bass, treble1, and treble2 strings
        Dulcimer dulc = new Dulcimer(bassNotes, treble1Notes, treble2Notes);

        // Combine all key mappings (bass, treble1, and treble2) into a single string without spaces
        String allKeys = bassKeys.replace(" ", "") + treble1Keys.replace(" ", "") + treble2Keys.replace(" ", "");

        // Main event loop to listen for key presses and play the corresponding string
        while (true) { 
            // Check if a key has been typed
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();  // Get the next typed key
                int typed = allKeys.indexOf(key);   // Find the index of the typed key in allKeys

                // Determine which string (bass, treble1, or treble2) corresponds to the key
                if (typed >= 0 && typed < bassKeys.replace(" ", "").length()) {  // Bass keys
                    dulc.hammer(typed, "bass");  // Hammer the bass string
                } else if (typed >= bassKeys.replace(" ", "").length() &&
                           typed < bassKeys.replace(" ", "").length() + treble1Keys.replace(" ", "").length()) {  // Treble1 keys
                    dulc.hammer(typed - bassKeys.replace(" ", "").length(), "treble1");  // Hammer the treble1 string
                } else {  // Treble2 keys
                    dulc.hammer(typed - bassKeys.replace(" ", "").length() - treble1Keys.replace(" ", "").length(), "treble2");  // Hammer the treble2 string
                }
            }
            dulc.play();  // Play the dulcimer sound based on struck strings
        }
    }    
}