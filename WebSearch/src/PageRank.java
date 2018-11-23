import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class PageRank {
	ConcurrentHashMap<Integer, GraphNode> graph;
	
	double dampening;
	double [][] pageFlow;
	
	//create the pageFlow matrix
	//every column (N^T) is the division of the page rank of a node
	void setMatrix(){
		//create nxn matrix
		pageFlow = new double[graph.size()][graph.size()];
		
		//set the value of every page rank division on outgoing links
		for(Entry<Integer, GraphNode> e : graph.entrySet()) {
			GraphNode n = e.getValue();
			n.setpageRank( 1d / (double)graph.size());
			for(Edge edge : n.linkTo) {
				pageFlow[edge.getTarget()][n.getID()] = 1d / (double)n.linkTo.size();
			}
		}
	}
	
	void dampenMatrix() {
		for(int i = 0; i < pageFlow.length; i++) {
			for(int j = 0; j < pageFlow[i].length; j++) {
				double pr = pageFlow[i][j];
				double scaleFactor = (1 - dampening) / pageFlow.length;
				double damp = (pr * dampening) + scaleFactor;
				pageFlow[i][j] = damp;
			}
		}
	}
	
	void printMatrix() {
		for(int i = 0; i < pageFlow.length; i++) {
			for(int j = 0; j < pageFlow[i].length; j++) {
				System.out.printf("%6.5f ", pageFlow[i][j]);
			}
			System.out.println();
		}
	}
	
	void testMap() {
		pageFlow = new double[4][4];
		
		pageFlow[0][0] = 0;
		pageFlow[1][0] = .5;
		pageFlow[2][0] = 0;
		pageFlow[3][0] = .5;
		pageFlow[0][1] = 0;
		pageFlow[1][1] = 0;
		pageFlow[2][1] = .5;
		pageFlow[3][1] = .5;
		pageFlow[0][2] = 1;
		pageFlow[1][2] = 0;
		pageFlow[2][2] = 0;
		pageFlow[3][2] = 0;
		pageFlow[0][3] = 0;
		pageFlow[1][3] = 0;
		pageFlow[2][3] = 1;
		pageFlow[3][3] = 0;
		
		dampening = 0.8;
		
		dampenMatrix();
		printMatrix();
	}
	//initial page rank is 1/ N where N is the number of graph nodes
	//dampening factor (decimal value = double) is SN + (1-S)/N where N is the PageRank flow matrix
	//Once pagerank flow matrix is done, matrix multiply initial page rank (r0) with matrix
	//this creates r1, r1 * prfm = r2 so on and so forth.
}
