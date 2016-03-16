import java.util.*;
/**
 * When a token lands on a priority hold, they are assigned the priority equal to the number rolled to land on it. They may only leave the space when no tokens with a lower number priority are on the priority hold with them. When a token is on a priority hold and does not have the lowest number priority, they end their turn without rolling. When they are on a priority hold and do have the lowest number priority, they roll, and advance the number of spaces rolled times a number specified for that priority hold when the board is created, which will be between two numbers set in configuration.
 * 
 * @Eric Weber
 * @3/16/16
 */
public class PHold implements Space, Comparator<Token> {
    
    /** The number by which to multiply rolls leaving the hold.*/
    private int multiplier;
    /** The ability of the token last passed to takeTurn to move. */
    private boolean canMove;
    /** The queue of tokens currently in the hold */
    private PriorityQueue<Token> tokens;
    /**
     * Constructor.
     * 
     * @param multiplier number by which to multiply rolls leaving the hold.
     */
    public PHold(int multiplier) {
        this.multiplier = multiplier;
        tokens = new PriorityQueue<Token>(1, this);
    }
    /**
     * Print the token and the status of the hold. Set the roll stored by the token. Add the token to queue of tokens currently in the hold.
     */
    public void land(Token p, Die d) {
        p.setRoll(d.prevRoll());
        tokens.add(p);
        System.out.print(p + " has landed on " + getStatus());
    }
    /**
     * @return if the token is at the front of the queue and if moving the token would go beyond the bounds of the board.
     */
    public boolean canMove() {
        return canMove;
    }
    
    public String getStatus() {
        return "P.Hold:" + multiplier + "|" + tokens.size();
    }
    
    /**
     * Take token's turn.
     * 
     * Print the token. If the token is first in the queue, roll the die. Print the roll. If the roll is within the bounds of the board, advance the token.
     */
    public boolean takeTurn(Token p, Die d, int boardEnd) {
        boolean atFront = true;
        int maxRoll = tokens.peek().getRoll();
        Iterator<Token> tokenIter = tokens.iterator();
        while (atFront && tokenIter.hasNext()) {
            Token cToken = tokenIter.next();
            if (cToken == p && cToken.getRoll() <= maxRoll) {
                /** Normal space behavior.*/
                int roll = d.roll();
                System.out.print(p + " is at the front of the queue and has rolled " + roll + ". ");
                if ((roll * multiplier) + p.getIndex() < boardEnd && (roll * multiplier) + p.getIndex() >= 0) {
                    tokenIter.remove();
                    canMove = true;
                    p.advance(roll * multiplier);
                    return false;
                }
                else if ((roll * multiplier) + p.getIndex() == boardEnd) {
                    tokenIter.remove();
                    p.advance(roll * multiplier);
                    return true;
                }
                else {
                    canMove = false;
                    return false;
                }
            }
            else if (cToken.getRoll() > maxRoll) {
                atFront = false;
                System.out.print(p + " is not at the front of the queue. ");
                canMove = false;
                return false;
            }
        }
        return false;
    }
    
    /**
     * Compare to Token objects for their priority in the queue.
     * 
     * Token 1 is greater than token 2 if token 1's entry roll is greater or equal to token 2's. Token 1 is less than token 2 if token 1's entry roll is less than token 2's.
     * @param p1 token 1.
     * @param p2 token 2.
     * @return 1 if token 1 is greater; -1 if token 1 is less.
     */
    public int compare(Token p1, Token p2) {
        if (p1.getRoll() >= p2.getRoll()) {
            return 1;
        }
        else {
            return -1;
        }
    }
}