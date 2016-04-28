
/**
 * 
 * 
 * @Eric Weber
 * @4/28/16
 */
public class MazeEdge {
    private Integer weight;
    private MazeNode dest;
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