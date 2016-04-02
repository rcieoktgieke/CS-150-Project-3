/**
 * When a token lands on a fair treasure pot, it gives that token a number of pieces specified by the game’s configuration. However, each pot can only give pieces to a certain number of tokens (also specified in configuration). If a token lands on the pot after the maximum number of tokens is reached, the pot is treated as a blank space.
 * 
 * @Eric Weber
 * @4/2/16
 */
public class FairTPot implements Space  {
    
    /** The number of pieces to be given to each token*/
    private int amount;
    /** The number of times the pot may give pieces */
    private int maxTimes;
    /** The number of times the pot has given pieces */
    private int times = 0;
    /** The ability of the token last passed to takeTurn to move. */
    private boolean canMove =  true;
    /**
     * Constructor.
     * 
     * @param amount number of pieces to be given to each token.
     * @param maxTimes number of times the pot may give pieces
     */
    public FairTPot(int amount, int maxTimes) {
        this.amount = amount;
        this.maxTimes = maxTimes;
    }
    
    /**
     * Print the token and the status of the pot. If the pot has been landed on fewer times than its max, give pieces to the token.
     */
    public void land(Token t, Die d) {
        System.out.print(t + " has landed on " + getStatus() + ". ");
        if (times < maxTimes) {
            t.addPieces(amount);
            times ++;
        }
    }
    /**
     * @return if moving the token would go beyond the bounds of the board.
     */
    public boolean canMove() {
        return canMove;
    }
    
    public String getStatus() {
        return "F.Pot|" + times;
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