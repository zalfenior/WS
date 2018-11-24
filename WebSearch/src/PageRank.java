import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class PageRank {
	ConcurrentHashMap<Integer, GraphNode> graph;
	
	double dampening;
	int iter;
	double [][] pageFlow;
	
	public PageRank(ConcurrentHashMap<Integer, GraphNode> chm) {
		graph = chm;
	}
	
	//create the pageFlow matrix
	//every column (N^T) is the division of the page rank of a node
	public void setMatrix(){
		//create nxn matrix
		pageFlow = new double[graph.size()][graph.size()];
		
		//set the value of every page rank division on outgoing links
		for(Entry<Integer, GraphNode> e : graph.entrySet()) {
			GraphNode n = e.getValue();
			n.setpageRank(0, 1d / (double)graph.size());
			for(Edge edge : n.linkTo) {
				pageFlow[edge.getTarget()][n.getID()] = 1d / (double)n.linkTo.size();
			}
		}
	}
	
	public void setIter(int i) { iter = i; }
	public void setDamp(double d) { dampening = d; }
	
	public void dampenMatrix() {
		for(int i = 0; i < pageFlow.length; i++) {
			for(int j = 0; j < pageFlow[i].length; j++) {
				double pr = pageFlow[i][j];
				double scaleFactor = (1 - dampening) / pageFlow.length;
				double damp = (pr * dampening) + scaleFactor;
				pageFlow[i][j] = damp;
			}
		}
	}
	
	public void printMatrix() {
		for(int i = 0; i < pageFlow.length; i++) {
			for(int j = 0; j < pageFlow[i].length; j++) {
				System.out.printf("%6.5f ", pageFlow[i][j]);
			}
			System.out.println();
		}
	}
	
	public void calcPR() {
		double nPR;
		GraphNode receiver;
		GraphNode sender;
		
		for(int i = 1; i <= iter; i++) { //number of page rank iterations
			for(int j = 0; j < graph.size(); j++) { //outer array index, the node we are on
				receiver = graph.get(j);
				nPR = 0; //new PageRank, starts at 0
				
				for(int k = 0; k < graph.size(); k++) { //incoming links to node
					sender = graph.get(k); //get the node pointing to j
					nPR += sender.getpageRank(i - 1) * pageFlow[j][k]; //set new PR to old pr * pageflow
				}
				
				//set the new pagerank into the node and the node back into the graph
				receiver.setpageRank(i, nPR);
				graph.put(receiver.getID(), receiver);
			}
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
}
