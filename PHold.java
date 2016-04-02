import java.util.*;
/**
 * When a token lands on a priority hold, they are assigned the priority equal to the number rolled to land on it. They may only leave the space when no tokens with a lower number priority are on the priority hold with them. When a token is on a priority hold and does not have the lowest number priority, they end their turn without rolling. When they are on a priority hold and do have the lowest number priority, they roll, and advance the number of spaces rolled times a number specified for that priority hold when the board is created, which will be between two numbers set in configuration.
 * 
 * @Eric Weber
 * @4/2/16
 */
public class PHold implements Space, Comparator<Integer> {
    
    /** The number by which to multiply rolls leaving the hold.*/
    private int multiplier;
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
     * @return if the token is first in the queue and if the roll times the multiplier is within the bounds of the board.
     */
    public boolean canMove(Token t, int roll, int boardEnd) {
        if (tokens.firstEntry().getValue().contains(t)) {
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
    
    public String getStatus() {
        return "P.Hold:" + multiplier + "|" + tokens.size();
    }
    
    /**
     * Take token's turn.
     * 
     * Print the roll and the token. If it can move, advance the token.
     */
    public boolean takeTurn(Token t, int roll, int boardEnd) {
        if (tokens.firstEntry().getValue().contains(t)) {
            System.out.print(t + " is at the front of the queue and has rolled " + roll + ". ");
        }
        else {
            System.out.print(t + " is not at the front of the queue. ");
        }
        if (canMove(t, roll, boardEnd)) {
            tokens.get(t.getRoll()).remove(t);
            if (tokens.get(t.getRoll()).isEmpty()) {
                tokens.remove(tokens.firstEntry().getKey());
            }
            int prevIndex = t.getIndex();
            t.advance(roll * multiplier);
            if ((roll * multiplier) + prevIndex == boardEnd) {
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
    
    /**
     * Compare two Integer objects (rolls) for their priority in the queue.
     * 
     * Return 1 if roll 1 is higher than roll 2, 0 if they are equal, and -1 if roll 1 is less than roll 2.
     * @param roll1 roll 1.
     * @param roll2 roll 2.
     * @return 1 if roll 1 is higher than roll 2, 0 if they are equal, and -1 if roll 1 is less than roll 2.
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