/**
 * When a player lands on a fair treasure pot, it gives that player a number of pieces specified by the game’s configuration. However, each pot can only give pieces to a certain number of players (also specified in configuration). If a player lands on the pot after the maximum number of players is reached, the pot is treated as a blank space. .
 * 
 * @Eric Weber
 * @3/3/16
 */
public class FairTPot implements Space  {
    
    /** The number of pieces to be given to each player*/
    private int amount;
    /** The number of times the pot may give pieces */
    private int maxTimes;
    /** The number of times the pot has given pieces */
    private int times = 0;
    /** The ability of the player last passed to takeTurn to move. */
    private boolean canMove =  true;
    /**
     * Constructor.
     * 
     * @param amount number of pieces to be given to each player.
     * @param maxTimes number of times the pot may give pieces
     */
    public FairTPot(int amount, int maxTimes) {
        this.amount = amount;
        this.maxTimes = maxTimes;
    }
    
    /**
     * Print the player and the status of the pot. If the pot has been landed on fewer times than its max, give pieces to the player.
     */
    public void land(Player p, Die d) {
        System.out.print(p + " has landed on " + getStatus() + ". ");
        if (times < maxTimes) {
            p.addPieces(amount);
            times ++;
        }
    }
    /**
     * @return if moving the player would go beyond the bounds of the board.
     */
    public boolean canMove() {
        return canMove;
    }
    
    public String getStatus() {
        return "F.Pot|" + times;
    }
    
    /**
     * Take player's turn.
     * 
     * Roll the die. Print the player and the roll. If the roll is within the bounds of the board, advance the player.
     */
    public boolean takeTurn(Player p, Die d, int boardEnd) {
        int roll = d.roll();
        System.out.print(p + " has rolled " + roll + ". ");
        if (roll + p.getIndex() < boardEnd) {
            canMove = true;
            p.advance(roll);
            return false;
        }
        else if (roll + p.getIndex() == boardEnd) {
            p.advance(roll);
            return true;
        }
        else {
            canMove = false;
            return false;
        }
    }
}