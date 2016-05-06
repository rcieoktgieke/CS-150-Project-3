import java.util.*;
/**
 * 
 * 
 * @Eric Weber
 * @4/30/16
 */
public class MazeNode {
    private LinkedList<MazeEdge> edges;
    private MazeExit exit = null;
    private Integer key;
    public MazeNode(Integer key) {
        edges = new LinkedList<MazeEdge>();
        this.key = key;
    }
    public MazeNode(Integer key, MazeExit exit) {
        edges = new LinkedList<MazeEdge>();
        this.key = key;
        this.exit = exit;
    }
    public Integer getKey() {
        return key;
    }
    public void addEdge(MazeNode node, Integer weight) {
        edges.add(new MazeEdge(node, weight));
    }
    public LinkedList<MazeEdge> getEdges() {
        return edges;
    }
    public MazeExit getExit() {
        return exit;
    }
    public void setExit(MazeExit exit) {
        this.exit = exit;
    }
    public String toString() {
        String output = "";
        Iterator<MazeEdge> edgeIter = edges.iterator();
        while (edgeIter.hasNext()) {
            MazeEdge edge = edgeIter.next();
            output += key + " -" + edge.getWeight() + "- " + edge.getDest().getKey() + "\n";
        }
        return output;
    }
}