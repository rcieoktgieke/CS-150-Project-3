import java.util.*;
/**
 * When a player lands on a hold, they may not move again until they roll the number they rolled to land on the hold. Any other roll ends the player’s turn without moving their token. When the player rolls the required number, they advance the number of spaces rolled times a number specified for that hold space when the board is created, which will be between two numbers set in configuration.
 *  
 * @Eric Weber
 * @3/5/16
 */
public class Hold implements Space  {
    /** The number by which to multiply rolls leaving the hold. */
    private int multiplier;
    /** The ability of the player last passed to takeTurn to move. */
    private boolean canMove;
    /** The list of players currently in the hold */
    private LinkedList<Player> players;
    /**
     * Constructor.
     * 
     * @param multiplier number by which to multiply rolls leaving the hold.
     */
    public Hold(int multiplier) {
        this.multiplier = multiplier;
        players = new LinkedList<Player>();
    }
    /**
     * Print the player and the status of the hold. Set the roll stored by the player. Add the player to list of players currently in the hold.
     */
    public void land(Player p, Die d) {
        players.add(p);
        p.setRoll(d.prevRoll());
        System.out.print(p + " has landed on " + getStatus() + ". ");
    }
    /**
     * @return if the player has been released from the hold and if moving the player would go beyond the bounds of the board.
     */
    public boolean canMove() {
        return canMove;
    }
    
    public String getStatus() {
        String rollsStr = "";
        Iterator<Player> playerIter = players.iterator();
        while (playerIter.hasNext()) {    
            rollsStr += ", " + playerIter.next().getRoll();
        }
        if (rollsStr.length() > 0) {
            return "Hold:" + multiplier + "|" + rollsStr.substring(2);
        }
        else {
            return "Hold:" + multiplier;
        }
    }
    
    /**
     * Take player's turn.
     * 
     * Roll the die. Print the player and the roll. If the roll is equal to the player's entry roll, and if the roll times the multiplier is within the bounds of the board, advance the player.
     */
    public boolean takeTurn(Player p, Die d, int boardEnd) {
        int roll = d.roll();
        System.out.print(p + " has rolled " + roll + ". ");
        if (roll == p.getRoll()) {
            if ((roll * multiplier) + p.getIndex() < boardEnd && (roll * multiplier) + p.getIndex() >= 0) {
                p.advance(roll * multiplier);
                canMove = true;
                return false;
            }
            else if ((roll * multiplier) + p.getIndex() == boardEnd) {
                p.advance(roll * multiplier);
                return true;
            }
            else {
                canMove = false;
                System.out.print("Can't escape: movement out of bounds. ");
                return false;
            }
        }
        else {
            System.out.print("No change. ");
            canMove = false;
            return false;
        }
    }
}