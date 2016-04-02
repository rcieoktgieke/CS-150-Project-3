import java.util.*;
/**
 * When a token lands on a hold, they may not move again until they roll the number they rolled to land on the hold. Any other roll ends the token’s turn without moving their token. When the token rolls the required number, they advance the number of spaces rolled times a number specified for that hold space when the board is created, which will be between two numbers set in configuration.
 *  
 * @Eric Weber
 * @4/2/16
 */
public class Hold implements Space  {
    /** The number by which to multiply rolls leaving the hold. */
    private int multiplier;
    /** The ability of the token last passed to takeTurn to move. */
    private boolean canMove;
    /** The list of tokens currently in the hold */
    private LinkedList<Token> tokens;
    /**
     * Constructor.
     * 
     * @param multiplier number by which to multiply rolls leaving the hold.
     */
    public Hold(int multiplier) {
        this.multiplier = multiplier;
        tokens = new LinkedList<Token>();
    }
    /**
     * Print the token and the status of the hold. Set the roll stored by the token. Add the token to list of tokens currently in the hold.
     */
    public void land(Token t, Die d) {
        tokens.add(t);
        t.setRoll(d.prevRoll());
        System.out.print(t + " has landed on " + getStatus() + ". ");
    }
    /**
     * @return if the token has been released from the hold and if moving the token would go beyond the bounds of the board.
     */
    public boolean canMove() {
        return canMove;
    }
    
    public String getStatus() {
        String rollsStr = "";
        Iterator<Token> tokenIter = tokens.iterator();
        while (tokenIter.hasNext()) {    
            rollsStr += ", " + tokenIter.next().getRoll();
        }
        if (rollsStr.length() > 0) {
            return "Hold:" + multiplier + "|" + rollsStr.substring(2);
        }
        else {
            return "Hold:" + multiplier;
        }
    }
    
    /**
     * Take token's turn.
     * 
     * Print the token and the roll. If the roll is equal to the token's entry roll, and if the roll times the multiplier is within the bounds of the board, advance the token.
     */
    public boolean takeTurn(Token t, int roll, int boardEnd) {
        System.out.print(t + " has rolled " + roll + ". ");
        if (roll == t.getRoll()) {
            if ((roll * multiplier) + t.getIndex() < boardEnd && (roll * multiplier) + t.getIndex() >= 0) {
                t.advance(roll * multiplier);
                canMove = true;
                return false;
            }
            else if ((roll * multiplier) + t.getIndex() == boardEnd) {
                t.advance(roll * multiplier);
                return true;
            }
            else {
                canMove = false;
                System.out.print("Can't escape: movement out of bounds. ");
                return false;
            }
        }
        else {
            System.out.print("No change. ");
            canMove = false;
            return false;
        }
    }
}