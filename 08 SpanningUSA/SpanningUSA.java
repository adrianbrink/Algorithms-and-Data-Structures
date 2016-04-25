import edu.princeton.cs.algs4.*; 
import java.util.Hashtable;
import java.util.Comparator;

public class SpanningUSA {
	
	private static final int cities = 128;

	public static void main(String[] args) {
		String[] lines = new In(args[0]).readAll().split("\\n");
		Hashtable map = new Hashtable();
		//Source: http://algs4.cs.princeton.edu/43mst/EdgeWeightedGraph.java.html
		EdgeWeightedGraph g = new EdgeWeightedGraph(cities);

		/*
		 * 	Collect all cities in an array
		 */
		for (int i = 0; i < cities; i++) {
			lines[i] = lines[i].replaceAll("\"", "").trim();
			map.put(lines[i], new Integer(i));
			//StdOut.println(lines[i]);
		}
		

		MinPQ pq = new MinPQ(new Comparator(){
			public int compare(Object o1, Object o2) {
				Edge e1 = (Edge) o1;
				Edge e2 = (Edge) o2;
				if (e1.weight() < e2.weight()) {
					return -1;
				} else if (e1.weight() == e2.weight()) {
					return 0;
				} else {
					return 1;
				}
			}
		});

		/*
		 * 	Collect all edges between cities
		 */
		for (int i = 128; i < lines.length; i++) {
				
			//Total:	 		"San Diego"--"Saint Joseph" [1652]
			//first split:  [0] "San Diego"  	[1]	"Saint Joseph" [1652]
			//second split: [0]	"Saint Joseph"  [1]	[1652]

			lines[i] = lines[i].replaceAll("\"", "");
			
			String read = lines[i];
			String[] first = read.split("--");
			String from = first[0];

			String[] second = first[1].split("\\[");
			String to = second[0].trim();

			int weg = Integer.parseInt(second[1].substring(0, second[1].length()-1));
			//StdOut.println("from - to - weg: "+from +to +weg);
			//StdOut.println("from: "+from);
			//StdOut.println("to: "+to);

			Edge edge = new Edge((Integer) map.get(from), (Integer) map.get(to), weg);
			//StdOut.println((Integer) map.get(to));

			
			g.addEdge(edge);
			//pq.insert(edge);
		}

		// pick random vertice as a starting point for the tree
		int start = 10;

		for (Edge edge: g.adj(start))
		{
			pq.insert(edge);
		}

		Edge edge = (Edge) pq.min();
		StdOut.println(edge.weight());

		StdOut.println(edge.other(start));

		// pick min edge
		// add new vertice to tree
		// add new edges to priority queue

		

		//prims(g);

		//Source: http://algs4.cs.princeton.edu/43mst/KruskalMST.java.html
		//KruskalMST mst = new KruskalMST(g);
		//StdOut.println("Weight: " + mst.weight());
	}

	private static void prims(EdgeWeightedGraph graph){

	}
}