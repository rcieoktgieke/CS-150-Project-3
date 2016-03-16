/**
 * A blank space lets a player roll on their next turn and move forward.
 *  
 * @Eric Weber
 * @3/3/16
 */
public class BlankSpace implements Space  {
    
    /** The ability of the player last passed to takeTurn to move. */
    private boolean canMove = true;
    /**
     * Constructor.
     */
    public BlankSpace() {
    }
    public void land(Player p, Die d) {
    }
    /**
     * @return if moving the player would go beyond the bounds of the board.
     */
    public boolean canMove() {
        return canMove;
    }
    
    public String getStatus() {
        return "Blank";
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