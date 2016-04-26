import edu.princeton.cs.algs4.*; 
import java.util.Hashtable;
import java.util.Comparator;
import java.util.Random;

public class SpanningUSA {
	
	private static final int cities = 128;

	public static void main(String[] args) {
		String[] lines = new In(args[0]).readAll().split("\\n");
		Hashtable map = new Hashtable();
		EdgeWeightedGraph g = new EdgeWeightedGraph(cities); //Source: http://algs4.cs.princeton.edu/43mst/EdgeWeightedGraph.java.html

		/*
		 * 	Collect all cities in an array
		 */
		for (int i = 0; i < cities; i++) {
			lines[i] = lines[i].replaceAll("\"", "").trim();
			map.put(lines[i], new Integer(i));
		}
		
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
			
			Edge edge = new Edge((Integer) map.get(from), (Integer) map.get(to), weg);
				
			g.addEdge(edge);
			
		}

		prims(g, cities);
		
	}

	private static void prims(EdgeWeightedGraph g, int cities){


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

		Queue<Edge> mst = new Queue<Edge>();

		// pick random vertice as a starting point for the tree
		Random ran = new Random();
		int newestVertice = ran.nextInt(cities);
		
		int treeSize = 1;
		Boolean[] marked = new Boolean[cities];
		marked[newestVertice] = true;

		while(treeSize <= cities)
		{	
			for (Edge edge: g.adj(newestVertice))
			{
				int other = edge.other(newestVertice);
				
				// only insert edge if adjecent vertix is outside tree
				if(marked[other] == null) pq.insert(edge);
			}

			Edge edge = (Edge) pq.delMin();

			// get the vertex outside the tree
			int either = edge.either();
			int other = edge.other(either);
			newestVertice = marked[either] == null ? either : other;

			marked[newestVertice] = true;
			mst.enqueue(edge);
			treeSize ++;
		}

	
		StdOut.println(mst.toString());
	}
}