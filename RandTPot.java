/**
 * When a token lands on a random treasure pot, the token rolls the die and receives a number of pieces equal to that roll. Each pot starts with a number of pieces specified by the configuration, and those pieces are decreased by the number given to each token. When the number of pieces reaches zero, no more pieces are given and the space is treated as blank.
 * 
 * @Eric Weber
 * @4/2/16
 */
public class RandTPot implements Space  {
    
    /** The number of pieces left in the pot */
    private int pieces;
    /** The ability of the token last passed to takeTurn to move. */
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
     * Print the token and the status of the pot. If pieces are left in the pot, give pieces to the token.
     */
    public void land(Token t, Die d) {
        System.out.print(t + " has landed on " + getStatus() + ". ");
        if (pieces > 0) {
            t.addPieces(piecesToGive(d));
        }
    }
    /**
     * @return if moving the token would go beyond the bounds of the board.
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