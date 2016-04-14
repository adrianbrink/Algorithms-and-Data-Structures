import edu.princeton.cs.algs4.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.LinkedList;

class WordLadder {
	public static void main (String[] args) {
		//Read the file stucture and Construct the map (words-#.txt)
		String[] a = StdIn.readAllStrings();
		HashMap<String, LinkedList<String>> map = createGraph(a);

		//Terminal input:
		//java WordLadder "words-#-in.txt" < "words-#.txt
		String[] v = new In(args[0]).readAll().split("\\s"); //String fromWord = args[0]; && String toWord = args[1]; 
				
		// (v. length() / 2 ) is the total # runs
		for (int i = 0 ; i < v.length ; i += 2) {
			String fromWord = v[i];
			String toWord = v[i+1];

			// if fromWord is equal to toWord, then we are done: 
			if (fromWord.equals(toWord)) StdOut.println("0"); 					//Gives only the path count
			//if (fromWord.equals(toWord)) StdOut.println("0 : " +fromWord); 	//Gives the path results aswell
			else bfs(fromWord, toWord, map);
		}	
	}

	private static HashMap<String, LinkedList<String>> createGraph(String[] a){
		HashMap<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();
		for(String str: a){
			String[] permutations = permutations(4,str);
			for(String permutation: permutations){
				LinkedList<String> entry = map.get(permutation);
			    if (entry == null) {
			        entry = new LinkedList<String> ();
			        map.put(permutation, entry);
			    }
			    entry.add(str);
			}
		}
		return map;
	}

	private static void bfs(String fromWord, String toWord, HashMap<String, LinkedList<String>> map) {
		Map<String, Boolean> marked = new HashMap<String, Boolean>();
		Map<String, String> edgeTo = new HashMap<String, String>();
		Queue<String> queue = new Queue<String>();
		Stack<String> path = new Stack<String>();
		queue.enqueue(fromWord);
		
		while(!queue.isEmpty()) {

			String word = queue.dequeue();
			String lastFour = word.substring(1,5);

			// sort last 4 characters
			char[] lastFourArray = lastFour.toCharArray();
			Arrays.sort(lastFourArray);

			for(String child: map.get(new String(lastFourArray))){
				if(marked.get(child) == null) {
		    		edgeTo.put(child, word);
		    		marked.put(child, true);
		    		queue.enqueue(child);
		    	}
			}		    
		}
		path = pathTo(toWord, fromWord, marked, edgeTo);
		if (path == null) StdOut.println("-1");						//Gives only the path count
	 	else StdOut.println(path.size() - 1);						//Gives only the path count
		//if (path == null) StdOut.println("-1: " +path);			//Gives the path results aswell
	 	//else StdOut.println((path.size() - 1) +" : " +path);		//Gives the path results aswell
	}

	private static Boolean hasPathTo(String word, Map marked) {
		return marked.get(word) == null ? false : true;
	}

	private static Stack<String> pathTo(String word, String source, Map marked, Map<String, String> edgeTo) {
		if(!hasPathTo(word, marked)) return null;
		Stack<String> path = new Stack<String>();
		
		for (String x = word; x != source; x = edgeTo.get(x)) {
			path.push(x);
		}
		path.push(source);	
		return path;
	}

	private static String[] permutations(int n, String s) {
		String[] permutations = new String[5];
		
		for(int i = 0; i <= n; i++) {
			char[] chars = new char[4];
			for(int j=0; j<4;j++) {
				chars[j] = s.charAt((i+j)%5);			
			}	
			Arrays.sort(chars);
			permutations[i] = new String(chars);
		}
		return permutations;
	}
}