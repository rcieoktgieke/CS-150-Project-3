import java.util.*;
/**
 * A Maze stores a graph of nodes through which tokens move.
 * 
 * @Eric Weber
 * @4/28/16
 */
public class Maze {
    
    private HashMap<Token, Integer> pieces;
    private HashMap<Token, MazeNode> locations;
    //private ArrayList<MazeNode> entrances;
    public Maze() {//ArrayList<MazeNode> entrances) {
        //this.entrances = entrances;
        pieces = new HashMap<Token, Integer>();
    }
    /*public ArrayList<MazeNode> getEntrances() {
        return entrances;
    }
    public ArrayList<MazeExit> getExits() {
        return null;
    }*/
}