import edu.princeton.cs.algs4.*; 
import java.util.Hashtable;

public class SpanningUSA {
	
	private static final int cities = 128;

	public static void main(String[] args) {
		String[] lines = new In(args[0]).readAll().split("\\n");
		Hashtable cities = new Hashtable();
		//Source: http://algs4.cs.princeton.edu/43mst/EdgeWeightedGraph.java.html
		//EdgeWeightedgraph g = new EdgeWeightedgraph(cities);

		/*
		 * 	Collect all cities in an array
		 */
		for (int i = 0; i < cities; i++) {
			lines = in.readLine();
			//TO DO: Remove " " surrounding the names of some cities
			cities.put(read, new Integer(i));
		}

		/*
		 * 	Collect all edges between cities
		 */
		while(in.hasNextLine()) { 
				
			//Total:	 		"San Diego"--"Saint Joseph" [1652]
			//first split:  [0] "San Diego"  	[1]	"Saint Joseph" [1652]
			//second split: [0]	"Saint Joseph"  [1]	[1652]

			String read = in.readLine();
			String[] first = read.split("--");
			String from = first[0];

			String[] second = first.split("\\[");
			String to = second[0];

			int weg = Ingteger.parseInt(second[1].substring(0, second[1].length()-1));
			StdOut.println("from - to - weg: "+from +to +weg);
		}
	}
}