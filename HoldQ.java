import java.util.*;
/**
 * When a tokens lands on a hold queue, they are added to the end of a queue of tokens that landed there previously. They may only leave the space when no tokens are in the queue ahead of them, and when they roll the number they rolled to land on the hold queue. When a token is on a hold queue and are not in the front of the queue, they end their turn without rolling. When they are on a hold queue and are in the front of the queue, they roll, and if their roll is other than the one they entered with, the token’s turn ends without moving. When the token rolls the required number, they advance the number of spaces rolled times a number specified for that hold space when the board is created, which will be between two numbers set in configuration.
 * 
 * @Eric Weber
 * @4/2/16
 */
public class HoldQ implements Space {
    
    /** The number by which to multiply rolls leaving the hold.*/
    int multiplier;
    /** The ability of the token last passed to takeTurn to move. */
    boolean canMove;
    /** The queue of tokens currently in the hold */
    LinkedList<Token> tokens;
    /**
     * Constructor.
     * 
     * @param multiplier number by which to multiply rolls leaving the hold queue.
     */
    public HoldQ(int multiplier) {
        this.multiplier = multiplier;
        tokens = new LinkedList<Token>();
    }
    /**
     * Print the token and the status of the hold queue. Set the roll stored by the token. Add the token to queue of tokens currently in the hold queue.
     */
    public void land(Token t, Die d) {
        t.setRoll(d.prevRoll());
        tokens.addLast(t);
        System.out.print(t + " has landed on " + getStatus() + ". ");
    }
    /**
     * @return if the token is first in the queue, if the roll is equal to the token's entry roll, and if the roll times the multiplier is within the bounds of the board.
     */
    public boolean canMove(Token t, int roll, int boardEnd) {
        if (tokens.peek() == t) {
            /** Hold behavior. */
            if (roll == t.getRoll()) {
                if ((roll * multiplier) + t.getIndex() <= boardEnd && (roll * multiplier) + t.getIndex() >= 0) {            
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
        else {
            return false;
        }
    }
    
    public String getStatus() {
        String rollsStr = "";
        Iterator<Token> tokenIter = tokens.iterator();
        while (tokenIter.hasNext()) {    
            rollsStr += ", " + tokenIter.next().getRoll();
        }
        if (rollsStr.length() > 0) {
            return "HoldQ:" + multiplier + "|" + rollsStr.substring(2);
        }
        else {
            return "HoldQ:" + multiplier;
        }
    }
    
    /**
     * Take token's turn.
     * 
     * Print the token and the roll. If the token can move, advance the token.
     */
    public boolean takeTurn(Token t, int roll, int boardEnd) {
        System.out.print(t);
        if (tokens.peek() == t) {
            System.out.print(" is at the front of the queue and rolled " + roll + ". ");
        }
        else {
            System.out.print(" is not at the front of the queue. ");
        }
        
        System.out.print(t + " has rolled " + roll + ". ");
        if (canMove(t, roll, boardEnd)) {
            tokens.poll();
            t.advance(roll * multiplier);
            if (t.getIndex() == boardEnd) {
                return true;
            }
            return false;
        }
        else {
            System.out.print("No change. ");
            return false;
        }
    }
}