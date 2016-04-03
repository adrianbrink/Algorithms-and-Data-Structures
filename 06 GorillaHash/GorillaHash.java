import edu.princeton.cs.algs4.*;
import java.util.Comparator;
import java.util.Arrays;

public class GorillaHash {

	public static void main(String[] args) {

        //Initial value stated in the pdf k = 20 & d = 10000
        int k = 20;
        int d = 10000;

        //Create a string of array for each line in the file.   
        String[] lines = new In(args[0]).readAll().split("\\n");

        int numberOfSpecies = 0;
        for (int i = 0 ; i < lines.length ; i++) if (lines[i].contains(">")) numberOfSpecies++;
        
        String[] species = new String[numberOfSpecies];
        String[] dnaLine = new String[numberOfSpecies];
        Arrays.fill(dnaLine, "");
        
        // Fill the ArrayStrings with data (uses numberOfSpecies to keep track)
        numberOfSpecies = -1;
        for(int i = 0 ; i < lines.length ; i++) {
            if (lines[i].contains(">")) {
                numberOfSpecies++;
                species[numberOfSpecies] = lines[i].replace(">", "");
            } else  dnaLine[numberOfSpecies] = dnaLine[numberOfSpecies] + lines[i];     
        }

        //Output - test
        for(int i = 0 ; i <= numberOfSpecies; i++) {
            StdOut.println(i + ": " + species[i]);
            StdOut.println(i + ": " + dnaLine[i]);
        }
	}
}