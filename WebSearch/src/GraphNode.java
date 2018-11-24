import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GraphNode{
  private int id;
  private String name = "N/A";
  private int degree = 0;
  private double [] pageRank;
  private String printer = "";
  public ConcurrentLinkedQueue<Edge> linkTo = new ConcurrentLinkedQueue<Edge>();

  
  public GraphNode(int init, int iter) {
    id = init;
    pageRank = new double[iter + 1];
  }
  
  public int getID() { return id; }
  public void setID(int res) { id = res; }

  public int getDegree() { return degree; }
  public void setDegree(int ent) { degree = ent; }
  
  public String getName() { return name; }
  public void setName(String neo) { name = neo; }
  
  public String getPrint() { return printer; }
  public void setPrint(String neo) { printer = neo; }
  public void addPrint(String x) { printer += x + "\n"; } //convert float value to string before call or in method
  
  public double getpageRank(int index) { return pageRank[index]; }
  public void setpageRank(int index, double neo) { pageRank[index] = neo; }
  
  public ConcurrentLinkedQueue<Edge> getQueue() { return linkTo; }
  public void setQueue( ConcurrentLinkedQueue<Edge> queue ) { linkTo = queue; }
  
  public void PrintNode() {
	  System.out.printf("<\n%d\n", id);
	  Iterator<Edge> iter = this.getQueue().iterator();
	  while(iter.hasNext()) {
		  System.out.printf("\n%d -> %d", id, iter.next().getTarget());
	  }
	  System.out.println(">");
  }
    
}
