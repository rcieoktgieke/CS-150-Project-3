/**
 * Spaces are parts of the board at specific indecies.
 * 
 * @Eric Weber
 * @2/24/16
 */
public interface Space {
    
    public void land(Player p, Die d);
    public void takeTurn(Player p, Die d);
}