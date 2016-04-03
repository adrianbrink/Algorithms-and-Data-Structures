import edu.princeton.cs.algs4.*;
import java.util.Comparator;
import java.util.Arrays;

public class GorillaHash {

	public static void main(String[] args) {

        
        //The gram length - Initial value k = 20 
        int k = 20;

        //The profil length - Initial value d = 10000
        int d = 10000;

        //Create a string of array for each line in the file.   
        String[] lines = new In(args[0]).readAll().split("\\n");

        int numberOfSpecies = 0;
        for (int i = 0 ; i < lines.length ; i++) if (lines[i].contains(">")) numberOfSpecies++;
        
        String[] species = new String[numberOfSpecies];
        String[] dnaLine = new String[numberOfSpecies];
            //Because DNA from the input is appended
            Arrays.fill(dnaLine, "");
        
        // Fill the ArrayStrings with data (uses numberOfSpecies to keep track)
        numberOfSpecies = -1;
        for(int i = 0 ; i < lines.length ; i++) {
            if (lines[i].contains(">")) {
                numberOfSpecies++;
                species[numberOfSpecies] = lines[i].replace(">", "");
            } else  dnaLine[numberOfSpecies] = dnaLine[numberOfSpecies] + lines[i];
        }
        
        //Create profiles in an two dimentional array.... Fuck this shit!!! (Do the Pong Dance)
        int [][] profil = new int[numberOfSpecies][d];
        
        for(int i = 0 ; i < numberOfSpecies ; i++) profil[i] = getProfil(dnaLine[i], k, d);
        
        //Output - test
        for(int i = 0 ; i <= numberOfSpecies; i++) {
            StdOut.println(i + ": " + species[i]);
            StdOut.println(i + ": " + dnaLine[i]);
        }
    }
    
    //Calculate the similarities between the profiles based on hashing
    private static double similar(int[] p, int[] q) {
        //Helpers: http://introcs.cs.princeton.edu/java/34nbody/Vector.java.html
        
        double similar, dot = 0.0;
        
        //Collect the inner product of this Vector p and q
        //dot = p dot q = p1 * q1 + p2 * q2 .... and so on
        for (int i = 0; i < p.length; i++) dot = dot + (p[i] * q[i]);
   
        //Collect the lenght of this Vector p and q (Euclidean dist.)
        //pytagoras: a^2 + b^ = c^2
        double pLength = dist(p);
        double qLength = dist(q);
        double totalLength = pLength * qLength;
        
        similar = dot / totalLength;
        
        return similar;
    }
    
    private static double dist(int[] a) {
        double aLength = 0;
        
        for (int i = 0 ; i < a.length ; i++) aLength = aLength + Math.pow(a[i], 2);
        return Math.sqrt(aLength);
    }
    
    private static int[] getProfil(String s, int gramLength, int profilLength) {
        int[] profil = new int[profilLength];
        return profil;
    } 
}