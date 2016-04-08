/**
 * A Token has a space on the board and a number of pieces.
 * 
 * @Eric Weber
 * @4/6/16
 */
public class Token {
    /** The index of the space which the token currently is on. */
    private int index;
    /** The number of pieces the token currently has. */
    private int pieces;
    /** The number of the latest roll needed for future reference. */
    private int storedRoll;
    /** This token's player's number */
    private int pNumber;
    /** This token's number */
    private int tNumber;
    /**
     * Constructor.
     */
    public Token(int pNumber, int tNumber) {
        this.pNumber = pNumber;
        this.tNumber = tNumber;
        int index = 0;
        int pieces = 0;
    }
    
    /**
     * Advance the token.
     * 
     * Print the token and the number of spaces advanced.
     * @param spaces the number of spaces to advance the token.
     */
    public void advance(int spaces) {
        System.out.print(this + " has advanced " + spaces + " spaces. ");
        index += spaces;
    }
    /**
     * Add pieces to the token.
     * 
     * Print the token and the number of pieces added.
     * @param pieces the number of pieces to add.
     */
    public void addPieces(int pieces) {
        System.out.print(this + " has recieved " + pieces + " pieces. ");
        this.pieces += pieces;
    }
    /**
     * Get the index of the token.
     * @return the index of the token.
     */
    public int getIndex() {
        return index;
    }
    /**
     * Get the number of pieces belonging to the token.
     * @return the number of pieces belonging to the token.
     */
    public int getPieces() {
        return pieces;
    }
    /**
     * Get the roll stored by this token for future reference.
     * 
     * @return the roll stored by this token for future reference.
     */
    public int getRoll() {
        return storedRoll;
    }
    /**
     * Set the roll stored by this token for future reference.
     * 
     * @param the roll to be stored by this token for future reference.
     */
    public void setRoll(int roll) {
        storedRoll = roll;
    }
    
    public String toString() {
        return "Token" + pNumber + "-" + tNumber;
    }
}