import java.util.*;
/**
 * A Maze stores a list of locations of Tokens in a Maze, and the number of pieces each Token has acquired in the Maze. When a Token moves in a Maze, it changes the Node associated with it to the one linked to its current node by the weight equal to its node.
 * 
 * @Eric Weber
 * @5/7/16
 */
public class Maze {
    
    private HashMap<Token, Integer> pieces;
    private HashMap<Token, MazeNode> locations;
    /**
     * Constructor.
     */
    public Maze() {
        pieces = new HashMap<Token, Integer>();
        locations = new HashMap<Token, MazeNode>();
    }
    /**
     * Enter a Token into the Maze with 0 pieces and with the MazeNode at which it is starting.
     * 
     * @param t the Token to be added.
     * @param entrance the MazeNode at which the Token is starting.
     */
    public void enterMaze(Token t, MazeNode entrance) {
        pieces.put(t, 0);
        locations.put(t, entrance);
    }
    /**
     * Determine if the given Token can move using the given roll.
     * 
     * @param t the Token to move.
     * @param roll the roll with which the Token will move.
     * @return if the given Token can move using the given roll.
     */
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
    /**
     * Take the Token's turn. If the Token's current node has an edge with weight equal to the Token's roll, the Token's node is replaced by the one at the destination of that edge. If that node has no outgoing edges, the Token lands on the MazeExit associated with that MazeNode.
     * 
     * @param t the Token being moved.
     * @param roll the roll with which the Token is moving.
     * @param boardEnd the index of the final space of the board.
     */
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