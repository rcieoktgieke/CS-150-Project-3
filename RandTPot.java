/**
 * When a player lands on a random treasure pot, the player rolls the die and receives a number of pieces equal to that roll. Each pot starts with a number of pieces specified by the configuration, and those pieces are decreased by the number given to each player. When the number of pieces reaches zero, no more pieces are given and the space is treated as blank.
 * 
 * @Eric Weber
 * @2/25/16
 */
public class RandTPot implements Space {
    
    private int pieces;
    public RandTPot(int pieces) {
        this.pieces = pieces;
    }
    public boolean canGive() {
        if (pieces > 0) {
            return true;
        }
        else {
            return false;
        }
    }
    public int givePieces() {
        return pieces; //TODO not this number
    }
    public void takeTurn(Player p, Die d) {
        p.advance(d.roll());
    }
}