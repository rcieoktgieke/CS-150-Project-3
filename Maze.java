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
    public Maze() {
        pieces = new HashMap<Token, Integer>();
    }
    public void enterMaze(Token t, MazeNode entrance) {
        locations.put(t, entrance);
    }
    public boolean canMove(Token t, int roll) {
        return false;
    }
    public void takeTurn(Token t, int roll, int boardEnd) {
    }
}