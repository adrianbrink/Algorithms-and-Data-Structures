import edu.princeton.cs.algs4.*;
import java.util.Comparator;
import java.util.Arrays;

public class GorillaHash {

    /*
     *  Calculate the similarities between two input arrays
     *  Source: http://introcs.cs.princeton.edu/java/34nbody/Vector.java.html
     */
    private static double similar(int[] p, int[] q) {
        
        
        double similar, dot = 0.0;
        
        //Collect the inner product of this Vector p and q
        //dot = p dot q = p1 * q1 + p2 * q2 .... and so on
        for (int i = 0; i < p.length; i++) dot = dot + (p[i] * q[i]);
   
        //Collect the lenght of this Vector p and q (Euclidean dist.)
        //pytagoras: a^2 + b^ = c^2
        similar = dot / (dist(p) * dist(q));
        
        return similar;
    }
    
    /*
     *  Returns the length of a vector
     *  Source: http://introcs.cs.princeton.edu/java/34nbody/Vector.java.html
     */
    private static double dist(int[] a) {
        double aLength = 0;
        
        for (int i = 0 ; i < a.length ; i++) aLength = aLength + Math.pow(a[i], 2);
        return Math.sqrt(aLength);
    }

    /*
     *  Returns the hashed profile of the gram-sequence
     */ 
    private static int[] profile(String s, int gramLength, int profileLength) {
        //Setup profile size, set by profilLength
        int[] profile = new int[profileLength];
        
        //Setup gram size, set by gramLength related to s(dnaline)
        String[] gram = grams(gramLength, s);

        //Hash the remainder of profileLength of gram to the profile
        //Source: https://en.wikipedia.org/wiki/Java_hashCode%28%29 ".hashCode()"
        for (int i = 0; i < gram.length ; i++) {
            int hash = Math.abs(gram[i].hashCode()) % profileLength;
            profile[hash] = profile[hash] +1;
        }
        return profile;
    } 

    /*
     *  Returns all the different gram-sequenses (k-value) constructed from the dnaString (s-value) in a string of arrays
     *  Description: dnaString.length = 20 && k = 20 then gram will only contain 1 result;
     */
    private static String[] grams(int gramLength, String s) {
        //Check for duplicates (improvement of code use : Set)
        //Set<String> set = new HashSet<>();
        
        String[] gram = new String[s.length()-gramLength];
        for (int i = 0; i < gram.length; i++) {
            int end = i + gramLength;
            gram[i] = s.substring(i, end);
        }
        return gram;
    }


	public static void main(String[] args) {
        //The gram length - Initial value k = 20 
        int k = 20;

        //The profil length used in a reminder calc. in Method: "profile" - Initial value d = 10000
        int d = 10000;

        //Create a string of array for each line in the file.  
        String[] lines = new In(args[0]).readAll().split("\\n");

        int numberOfSpecies = 0;
        for (int i = 0 ; i < lines.length ; i++) if (lines[i].contains(">")) numberOfSpecies++;
        
        String[] species = new String[numberOfSpecies];
        String[] dnaLine = new String[numberOfSpecies];
            //Because of DNA from the input is appended
            Arrays.fill(dnaLine, "");
        
        // Fill the ArrayStrings with data (uses numberOfSpecies to keep track)
        numberOfSpecies = 0;
        for(int i = 0 ; i < lines.length ; i++) {
            if (lines[i].contains(">")) {
                species[numberOfSpecies] = lines[i].replace(">", "").trim().replaceAll("\\d+.*", "");
                if (i != 0) numberOfSpecies++; //skip first iteration
            } else  dnaLine[numberOfSpecies] = dnaLine[numberOfSpecies] + lines[i];
        }
        
        //Create profiles in an two dimentional array.... *!"& this (Do the Pong Dance)
        int [][] profile = new int[numberOfSpecies][d];
        for(int i = 0 ; i < numberOfSpecies ; i++) profile[i] = profile(dnaLine[i], k, d);

        // Print top-bar with species (names)
        System.out.printf("%15s", "");
        for (int i = 0; i < species.length; i++) {
            System.out.printf("%15s", species[i]);
        }
        
        StdOut.println("");
        
        // Print names first in the row (i) and profiles compared (j)
        for (int i = 0; i < profile.length; i++) {
            System.out.printf("%15s", species[i]);
            for (int j = 0; j < profile.length; j++) {
                double similar = similar(profile[i], profile[j]);
                System.out.printf("%15f", similar);
            }
            System.out.println();
        }
    }
    
    /*
     UNIT-testing:
     Similar method
     dist method
     */
    
    /*
     Time Conmplexity:
     1. Read lines in file (loop)
     2. Read #species in file (loop)
     3. Create profile (loop)
     3.1 Compute grams (loop)
     3.2 Hash values (loop)
     4. PrintArray species & dnaLine
     5. PrintTwoDimentionalArray Headline
     6. PrintTwoDimentionalArray Contens
     
     Conclusion: Worst Case is O(n^2)
     */
}