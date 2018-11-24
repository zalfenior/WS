import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class WebSearch {
	
	private static File toMap;
	private static int iter = 0;
	private static float scale = 0;
	private static int directed = 0;
	public static ConcurrentHashMap<Integer, GraphNode> node = new ConcurrentHashMap<Integer, GraphNode>();
	public static String[] lineRead;
	public static double weight = 0;
	public static int tempInt, deg = 0, source, target;
	public static String label = "";
	
	public static void main(String[] args) {
		
		String line;
		
		if(args.length != 3) {
			System.out.println("ERROR: method to run: /java WebSearch <GML File name> <Int n: number of iterations> <Float z: Scaledown> ");
			System.exit(0);
		}
		
		toMap = new File(args[0]);
		iter = Integer.parseInt(args[1]);
		scale = Float.valueOf(args[2]);
		
		PageRank r = new PageRank();
		r.testMap();
		
		try (BufferedReader read = new BufferedReader(new FileReader(toMap))) {
		    while ((line = read.readLine()) != null) {
		    	if(line.contains("node")) {
		    		while ((line = read.readLine()) != null) {
	    				line = line.replaceAll("\\s+"," ");
		    			lineRead = line.split(" ");
		    			if(line.contains("id")) {
		    				tempInt = Integer.parseInt(lineRead[2]);
		    			} else if (line.contains("label")) {
		    				label = lineRead[2];
		    			} else if (line.contains("degree")) {
		    				deg = Integer.parseInt(lineRead[2]);
		    			} else if (line.contains("]")){
		    				GraphNode a = new GraphNode(tempInt, iter);
		    				a.setDegree(deg);
		    				a.setName(label);
			    			//System.out.printf("Id of test node is %d\nDegree is %d\nName is %s\n", a.getID(), a.getDegree(), a.getName());
			    			node.put(tempInt, a);
			    			break;
		    			} 
		    		}
		    	} else if (line.contains("edge")) {
		    		while ((line = read.readLine()) != null) {
		    			line = line.replaceAll("\\s+"," ");
		    			lineRead = line.split(" ");
		    			if(line.contains("source")) {
		    				source = Integer.parseInt(lineRead[2]);
		    			} else if(line.contains("target")) {
		    				target = Integer.parseInt(lineRead[2]);
		    			} else if(line.contains("weight")){
		    				weight = Double.parseDouble(lineRead[2]);
		    			} else if(line.contains("]")) {
		    				Edge tempEdge = new Edge(target);
		    				tempEdge.setWeight(weight);
		    				node.get(source).getQueue().add(tempEdge);
		    			}
		    		}
		    	} else if (line.contains("directed")) {
		    		line = line.replaceAll("\\s+"," ");
	    			lineRead = line.split(" ");
	    			directed = Integer.parseInt(lineRead[2]);
		    	} else {
		    		continue;
		    	}
		    }
		} catch (IOException e) {
			System.out.println("That happened, wasn't supposed to though");
			e.printStackTrace();
		}
		
	}
}
