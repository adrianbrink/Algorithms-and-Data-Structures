import edu.princeton.cs.algs4.*;
import java.util.HashMap;
import java.util.Arrays;
import java.util.LinkedList;

class WordLadder {

	public static void main (String[] args)
	{
		
		String[] a = StdIn.readAllStrings();

		HashMap<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();

		for(String str: a)
		{
			String[] permutations = permutations(4,str);

			for(String permutation: permutations)
			{
				LinkedList<String> entry = map.get(permutation);
		
			    if (entry == null) {
			        entry = new LinkedList<String> ();
			        map.put(permutation, entry);
			    }

			    entry.add(str);
			}
		}

		StdOut.println(map.get("ehrt"));



		
		// take input

		// create hashmap

		// for every word in file, insert in hashmap: use all sorted 4-combinations as keys and add word as value

		// do BFS	
	}

	private static String[] permutations(int n, String s)
	{
		String[] permutations = new String[5];
		
		for(int i = 0; i <= n; i++)
		{
			char[] chars = new char[4];
		
			for(int j=0; j<4;j++)
			{
				chars[j] = s.charAt((i+j)%5);			
			}	

			Arrays.sort(chars);

			permutations[i] = new String(chars);
		}

		return permutations;
	}

}