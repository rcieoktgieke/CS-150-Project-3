/**
 * A Player has a space on the board and a number of pieces.
 * 
 * @Eric Weber
 * @2/25/16
 */
public class Player {
    private int index;
    private int pieces;
    public Player() {
        int index = 0;
        int pieces = 0;
    }
    public boolean advance(int spaces) {
        index += spaces;
        return true;
    }
    public void addPieces(int pieces) {
        this.pieces += pieces;
    }
    public int getIndex() {
        return index;
    }
}