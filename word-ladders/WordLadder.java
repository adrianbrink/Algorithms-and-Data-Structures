import edu.princeton.cs.algs4.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.LinkedList;



class WordLadder {

	public static void main (String[] args)
	{

		// create graph
		
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

		String fromWord = args[0];
		String toWord = args[1];
		
		// run breadth first search
		bff(fromWord, toWord, map);
	}

	private static void bff(String fromWord, String toWord, HashMap<String, LinkedList<String>> map)
	{
		Map<String, Boolean> marked = new HashMap<String, Boolean>();
		Map<String, String> edgeTo = new HashMap<String, String>();
		
		Queue<String> queue = new Queue<String>();
		queue.enqueue(fromWord);
		
		while(!queue.isEmpty()) {

			String word = queue.dequeue();
			String lastFour = word.substring(1,5);

			// sort last 4 characters
			char[] lastFourArray = lastFour.toCharArray();
			Arrays.sort(lastFourArray);

			for(String child: map.get(new String(lastFourArray)))
			{
				if(marked.get(child) == null)
		    	{
		    		edgeTo.put(child, word);
		    		marked.put(child, true);
		    		queue.enqueue(child);
		    	}
			}		    
		}

		StdOut.println(pathTo(toWord, fromWord, marked, edgeTo));
	}

	private static Boolean hasPathTo(String word, Map marked)
	{
		return marked.get(word) == null ? false : true;
	}

	private static Iterable<String> pathTo(String word, String source, Map marked, Map<String, String> edgeTo)
	{
		if(! hasPathTo(word, marked)) return null;
		Stack<String> path = new Stack<String>();
		for (String x = word; x != source; x = edgeTo.get(x))
		{
			path.push(x);
		}
		path.push(source);
		return path;
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