/**
 * When a player lands on a random treasure pot, the player rolls the die and receives a number of pieces equal to that roll. Each pot starts with a number of pieces specified by the configuration, and those pieces are decreased by the number given to each player. When the number of pieces reaches zero, no more pieces are given and the space is treated as blank.
 * 
 * @Eric Weber
 * @3/4/16
 */
public class RandTPot implements Space  {
    
    /** The number of pieces left in the pot */
    private int pieces;
    /** The ability of the player last passed to takeTurn to move. */
    private boolean canMove = true;
    /**
     * Constructor.
     * 
     * @param pieces number of pieces initially in the pot.
     */
    public RandTPot(int pieces) {
        this.pieces = pieces;
    }
    
    /**
     * Print the player and the status of the pot. If pieces are left in the pot, give pieces to the player.
     */
    public void land(Player p, Die d) {
        System.out.print(p + " has landed on " + getStatus() + ". ");
        if (pieces > 0) {
            p.addPieces(piecesToGive(d));
        }
    }
    /**
     * @return if moving the player would go beyond the bounds of the board.
     */
    public boolean canMove() {
        return canMove;
    }
    
    /**
     * Roll the die. If the value rolled is greater than the number of pieces left, return the number of pieces left. Otherwise, return the number rolled.
     * 
     * @return the max of a die roll and number of pieces.
     */
    private int piecesToGive(Die d) {
        int piecesToGive = d.roll();
        if (piecesToGive > pieces) {
            pieces = 0;
            return piecesToGive;
        }
        else {
            pieces -= piecesToGive;
            return piecesToGive;
        }
    }
    
    public String getStatus() {
        return "R.Pot|" + pieces;
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