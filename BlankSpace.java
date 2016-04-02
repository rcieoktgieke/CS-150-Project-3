/**
 * A blank space lets a token roll on their next turn and move forward.
 *  
 * @Eric Weber
 * @4/2/16
 */
public class BlankSpace implements Space {
    
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
    public boolean canMove(Token t, int roll, int boardEnd) {
        if (roll + t.getIndex() <= boardEnd && roll + t.getIndex() > 0) {            
            return true;
        }
        else {
            return false;
        }
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