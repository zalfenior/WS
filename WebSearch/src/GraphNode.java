import java.util.concurrent.ConcurrentLinkedQueue;

public class GraphNode{
  public int id;
  public String name;
  public ConcurrentLinkedQueue<Integer> linkTo = new ConcurrentLinkedQueue<Integer>();

  
  public GraphNode(int init) {
    id = init;
  }
  public int getID() { return id; }
  public void setID(int res) { id = res; }

  public String getName() { return name; }
  public void setName(String neo) { name = neo; }
  
  public ConcurrentLinkedQueue<Integer> getQueue() { return linkTo; }
  public void setQueue( ConcurrentLinkedQueue<Integer> queue ) { linkTo = queue; }
    
}