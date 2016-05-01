/**
 * A 
 *  
 * @Eric Weber
 * @4/28/16
 */
public class MazeExit implements Space {
    
    private Maze maze;
    private int index;
    /**
     * Constructor.
     */
    public MazeExit(Maze maze) {
    }
    public void land(Token t, Die d) {
    }
    /**
     * @return if moving the token would go beyond the bounds of the board.
     */
    public boolean canMove(Token t, int roll, int boardEnd) {
        if (roll + t.getIndex() <= boardEnd && roll + t.getIndex() > 0) {            
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * @return true
     */
    public boolean advanced() {
       return true;
    }
    
    public String getStatus() {
        return "MazeExit";
    }
    
    /**
     * Take token's turn.
     * 
     * Print the token and the roll. If the roll is within the bounds of the board, advance the token.
     */
    public boolean takeTurn(Token t, int roll, int boardEnd) {
        System.out.print(t + " has rolled " + roll + ". ");
        if (canMove(t, roll, boardEnd)) {
            t.advance(roll);
            if (t.getIndex() == boardEnd) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}