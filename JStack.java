import java.util.*;
/**
 * When a token moves off of a JStack, they move to the same x and y coordinate on the z level that corresponds to their roll modulo the z dimension of the board.
 *  
 * @Eric Weber
 * @5/7/16
 */
public class JStack implements Space {
    
    /** The ability of the token last passed to takeTurn to move. */
    private boolean canMove = true;
    /** The number by which to multiply movements out of the stack (either 0 or 1). */
    private int multiplier;
    /** The value of the x dimension of the board. */
    private int x;
    /** The value of the y dimension of the board. */
    private int y;
    /** The value of the z dimension of the board. */
    private int z;
    /** The board used for the game. */
    private Board board;
    /** The die used for the game. */
    private Die die;
    /**
     * Constructor.
     * 
     * @param x the value of the x dimension of the board.
     * @param y the value of the y dimension of the board.
     * @param z the value of the z dimension of the board.
     * @param board the board used for the game.
     */
    public JStack(int factor, int x, int y, int z, Board board, Die die) {
        this.die = die;
        this.board = board;
        multiplier = factor;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    /**
     * Print the token and the status of the JStack.
     */
    public void land(Token t, Die d) {
        System.out.print(t + " has landed on " + getStatus() + ". ");
        takeTurn(t, d.roll(), x*y*z - 1);
    }
    /**
     * Print the token and the status of the JStack.
     * @return if the token has reached the end of the board.
     */
    public boolean land(Token t, Die d, JStack initialSpace, int boardEnd) {
        System.out.print(t + " has landed on " + getStatus() + ". ");
        if (this == initialSpace) {
            int roll = d.roll();
            if (roll + t.getIndex() <= boardEnd && roll + t.getIndex() > 0) {            
                t.advance(roll);
                board.get(t.getIndex()).land(t, d);
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
        else {
            return takeTurn(t, d.roll(), x*y*z - 1, initialSpace);
        }
    }
    /**
     * @return true
     */
    public boolean canMove(Token t, int roll, int boardEnd) {
        return true;
    }
    /**
     * @return false, so that main never calls land() on a space coming from a JStack.
     */
    public boolean advanced() {
       return false;
    }
    
    public String getStatus() {
        return "JStack:" + multiplier;
    }
    
    /**
     * Take token's turn.
     * 
     * Print the token and the roll. Advance the player to their current x and y coordinate up (or down) the z level that corresponds to their roll modulo the z dimension of the board.
     */
    public boolean takeTurn(Token t, int roll, int boardEnd) {
        System.out.print(t + " has rolled " + roll + ". ");
        int zIndex = t.getIndex() / (x*y);
        t.advance(((z + ((roll*multiplier + zIndex) % z)) % z - zIndex)*x*y);
        if (t.getIndex() == boardEnd) {
            return true;
        }
        else {
            Space landingSpace = board.get(t.getIndex());
            if (landingSpace instanceof JStack) {
                return land(t, die, this, boardEnd);
            }
            else {
                land(t, die);
            }
            return false;
        }
    }
    /**
     * Take token's turn.
     * 
     * Print the token and the roll. Advance the player to their current x and y coordinate up (or down) the z level that corresponds to their roll modulo the z dimension of the board.
     * @param initalSpace the JStack that initiated this chain of landing.
     */
    public boolean takeTurn(Token t, int roll, int boardEnd, JStack initialSpace) {
        System.out.print(t + " has rolled " + roll + ". ");
        int zIndex = t.getIndex() / (x*y);
        t.advance(((z + ((roll*multiplier + zIndex) % z)) % z - zIndex)*x*y);
        if (t.getIndex() == boardEnd) {
            return true;
        }
        else {
            Space landingSpace = board.get(t.getIndex());
            if (landingSpace instanceof JStack) {
                return land(t, die, initialSpace, boardEnd);
            }
            else {
                land(t, die);
            }
            return false;
        }
    }
}