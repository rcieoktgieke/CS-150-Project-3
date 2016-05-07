/**
 * A MazeEdge stores a weight and a destination of type MazeNode.
 * 
 * @Eric Weber
 * @5/7/16
 */
public class MazeEdge {
    private Integer weight;
    private MazeNode dest;
    /**
     * Constructor.
     */
    public MazeEdge(MazeNode dest, Integer weight) {
        this.weight = weight;
        this.dest = dest;
    }
    public Integer getWeight() {
        return weight;
    }
    public MazeNode getDest() {
        return dest;
    }
}