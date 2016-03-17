import java.util.*;
/**
 * When a token lands on a priority hold, they are assigned the priority equal to the number rolled to land on it. They may only leave the space when no tokens with a lower number priority are on the priority hold with them. When a token is on a priority hold and does not have the lowest number priority, they end their turn without rolling. When they are on a priority hold and do have the lowest number priority, they roll, and advance the number of spaces rolled times a number specified for that priority hold when the board is created, which will be between two numbers set in configuration.
 * 
 * @Eric Weber
 * @3/17/16
 */
public class PHold implements Space, Comparator<Integer> {
    
    /** The number by which to multiply rolls leaving the hold.*/
    private int multiplier;
    /** The ability of the token last passed to takeTurn to move. */
    private boolean canMove;
    /** The queue of tokens currently in the hold */
    private TreeMap<Integer, ArrayList<Token>> tokens;
    /**
     * Constructor.
     * 
     * @param multiplier number by which to multiply rolls leaving the hold.
     */
    public PHold(int multiplier) {
        this.multiplier = multiplier;
        tokens = new TreeMap<Integer, ArrayList<Token>>(this);
    }
    /**
     * Print the token and the status of the hold. Set the roll stored by the token. Add the token to queue of tokens currently in the hold.
     */
    public void land(Token t, Die d) {
        t.setRoll(d.prevRoll());
        if (tokens.containsKey(d.prevRoll())) {
            tokens.get(d.prevRoll()).add(t);
        }
        else {
            ArrayList<Token> entry = new ArrayList<Token>(1);
            entry.add(t);
            tokens.put(d.prevRoll(), entry);
        }
        System.out.print(t + " has landed on " + getStatus());
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
    public boolean takeTurn(Token t, Die d, int boardEnd) {
        if (tokens.firstEntry().getValue().contains(t)) {
            /** Normal space behavior.*/
            int roll = d.roll();
            System.out.print(t + " is at the front of the queue and has rolled " + roll + ". ");
            if ((roll * multiplier) + t.getIndex() < boardEnd && (roll * multiplier) + t.getIndex() >= 0) {
                tokens.get(roll).remove(t); // is this right?
                canMove = true;
                t.advance(roll * multiplier);
                return false;
            }
            else if ((roll * multiplier) + t.getIndex() == boardEnd) {
                tokens.get(roll).remove(t); // and this
                t.advance(roll * multiplier);
                return true;
            }
            else {
                canMove = false;
                return false;
            }
        }
        else {
            System.out.print(t + " is not at the front of the queue. ");
            canMove = false;
            return false;
        }
        
        /*
        boolean atFront = true;
        int maxRoll = tokens.peek().getRoll();
        Iterator<Token> tokenIter = tokens.iterator();
        while (atFront && tokenIter.hasNext()) {
            Token cToken = tokenIter.next();
            if (cToken == t && cToken.getRoll() <= maxRoll) {
                /** Normal space behavior.*
                int roll = d.roll();
                System.out.print(t + " is at the front of the queue and has rolled " + roll + ". ");
                if ((roll * multiplier) + t.getIndex() < boardEnd && (roll * multiplier) + t.getIndex() >= 0) {
                    tokenIter.remove();
                    canMove = true;
                    t.advance(roll * multiplier);
                    return false;
                }
                else if ((roll * multiplier) + t.getIndex() == boardEnd) {
                    tokenIter.remove();
                    t.advance(roll * multiplier);
                    return true;
                }
                else {
                    canMove = false;
                    return false;
                }
            }
            else if (cToken.getRoll() > maxRoll) {
                atFront = false;
                System.out.print(t + " is not at the front of the queue. ");
                canMove = false;
                return false;
            }
        }
        return false;*/
    }
    
    /**
     * Compare to Integer objects (rolls) for their priority in the queue.
     * 
     * Token 1 is greater than token 2 if token 1's entry roll is greater than token 2's. Tokens are equal if their entry roll is equal. Token 1 is less than token 2 if token 1's entry roll is less than token 2's.
     * @param p1 token 1.
     * @param p2 token 2.
     * @return 1 if token 1 is greater, 0 if tokens are equal, -1 if token 1 is less.
     */
    public int compare(Integer roll1, Integer roll2) {
        if (roll1 > roll2) {
            return 1;
        }
        else if (roll1 == roll2) {
            return 0;
        }
        else {
            return -1;
        }
    }
}