/**
 * A Player has a space on the board and a number of pieces.
 * 
 * @Eric Weber
 * @3/5/16
 */
public class Player {
    /** The index of the space which the player currently is on. */
    private int index;
    /** The number of pieces the player currently has. */
    private int pieces;
    /** The number of the latest roll needed for future reference. */
    private int storedRoll;
    /**
     * Constructor.
     */
    public Player() {
        int index = 0;
        int pieces = 0;
    }
    
    /**
     * Advance the player.
     * 
     * Print the player and the number of spaces advanced.
     * @param spaces the number of spaces to advance the player.
     */
    public void advance(int spaces) {
        System.out.print(this + " has advanced " + spaces + " spaces. ");
        index += spaces;
    }
    /**
     * Add pieces to the player.
     * 
     * Print the player and the number of pieces added.
     * @param pieces the number of pieces to add.
     */
    public void addPieces(int pieces) {
        System.out.print(this + " has recieved " + pieces + " pieces. ");
        this.pieces += pieces;
    }
    /**
     * Get the index of the player.
     * @return the index of the player.
     */
    public int getIndex() {
        return index;
    }
    /**
     * Get the number of pieces belonging to the player.
     * @return the number of pieces belonging to the player.
     */
    public int getPieces() {
        return pieces;
    }
    /**
     * Get the roll stored by this player for future reference.
     * 
     * @return the roll stored by this player for future reference.
     */
    public int getRoll() {
        return storedRoll;
    }
    /**
     * Set the roll stored by this player for future reference.
     * 
     * @param the roll to be stored by this player for future reference.
     */
    public void setRoll(int roll) {
        storedRoll = roll;
    }
}