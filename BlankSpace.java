/**
 * A blank space lets a player roll on their next turn and move forward.
 *  
 * @Eric Weber
 * @version (a version number or a date)
 */
public class BlankSpace {
    
    public BlankSpace() {
    }
    public void land(Player p, Die d) {
    }
    public void takeTurn(Player p, Die d) {
        p.advance(d.roll());
    }
}