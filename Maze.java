import java.util.*;
/**
 * A Maze stores a graph of nodes through which tokens move.
 * 
 * @Eric Weber
 * @4/30/16
 */
public class Maze {
    
    private HashMap<Token, Integer> pieces;
    private HashMap<Token, MazeNode> locations;
    public Maze() {
        pieces = new HashMap<Token, Integer>();
        locations = new HashMap<Token, MazeNode>();
    }
    public void enterMaze(Token t, MazeNode entrance) {
        pieces.put(t, 0);
        locations.put(t, entrance);
    }
    public boolean canMove(Token t, int roll) {
        for (MazeEdge edge : locations.get(t).getEdges()) {
            if (edge.getWeight() == roll) {
                return true;
            }
            else {
                return false;
            }
        }
        return true;
    }
    public void takeTurn(Token t, int roll, int boardEnd) {
        for (MazeEdge edge : locations.get(t).getEdges()) {
            if (edge.getWeight() == roll) {
                pieces.replace(t, pieces.get(t) + roll);
                if (edge.getDest().getExit() == null) {
                    locations.replace(t, edge.getDest());
                }
                else {
                    System.out.print("Exiting maze. ");
                    t.addPieces(pieces.get(t));
                    pieces.remove(t);
                    edge.getDest().getExit().land(t, null);
                    locations.remove(t);
                }
                break;
            }
        }
    }
}