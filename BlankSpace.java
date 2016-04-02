/**
 * A blank space lets a token roll on their next turn and move forward.
 *  
 * @Eric Weber
 * @4/2/16
 */
public class BlankSpace implements Space  {
    
    /** The ability of the token last passed to takeTurn to move. */
    private boolean canMove = true;
    /**
     * Constructor.
     */
    public BlankSpace() {
    }
    public void land(Token t, Die d) {
    }
    /**
     * @return if moving the token would go beyond the bounds of the board.
     */
    public boolean canMove() {
        return canMove;
    }
    
    public String getStatus() {
        return "Blank";
    }
    
    /**
     * Take token's turn.
     * 
     * Print the token and the roll. If the roll is within the bounds of the board, advance the token.
     */
    public boolean takeTurn(Token t, int roll, int boardEnd) {
        System.out.print(t + " has rolled " + roll + ". ");
        if (roll + t.getIndex() < boardEnd) {
            canMove = true;
            t.advance(roll);
            return false;
        }
        else if (roll + t.getIndex() == boardEnd) {
            t.advance(roll);
            return true;
        }
        else {
            canMove = false;
            return false;
        }
    }
}