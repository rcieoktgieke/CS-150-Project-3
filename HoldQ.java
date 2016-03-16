import java.util.*;
/**
 * When a player lands on a hold queue, they are added to the end of a queue of players that landed there previously. They may only leave the space when no players are in the queue ahead of them, and when they roll the number they rolled to land on the hold queue. When a player is on a hold queue and are not in the front of the queue, they end their turn without rolling. When they are on a hold queue and are in the front of the queue, they roll, and if their roll is other than the one they entered with, the player’s turn ends without moving their token. When the player rolls the required number, they advance the number of spaces rolled times a number specified for that hold space when the board is created, which will be between two numbers set in configuration.
 * 
 * @Eric Weber
 * @3/5/16
 */
public class HoldQ implements Space {
    
    /** The number by which to multiply rolls leaving the hold.*/
    int multiplier;
    /** The ability of the player last passed to takeTurn to move. */
    boolean canMove;
    /** The queue of players currently in the hold */
    LinkedList<Player> players;
    /**
     * Constructor.
     * 
     * @param multiplier number by which to multiply rolls leaving the hold queue.
     */
    public HoldQ(int multiplier) {
        this.multiplier = multiplier;
        players = new LinkedList<Player>();
    }
    /**
     * Print the player and the status of the hold queue. Set the roll stored by the player. Add the player to queue of players currently in the hold queue.
     */
    public void land(Player p, Die d) {
        p.setRoll(d.prevRoll());
        players.addLast(p);
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
            return "HoldQ:" + multiplier + "|" + rollsStr.substring(2);
        }
        else {
            return "HoldQ:" + multiplier;
        }
    }
    
    /**
     * Take player's turn.
     * 
     * Print the player. If the player is first in the queue, roll the die. Print the roll. If the roll is equal to the player's entry roll, and if the roll times the multiplier is within the bounds of the board, advance the player.
     */
    public boolean takeTurn(Player p, Die d, int boardEnd) {
        System.out.print(p);
        if (players.peek() == p) {
            /** Hold behavior. */
            int roll = d.roll();
            System.out.print(" is at the front of the queue and rolled " + roll + ". ");
            if (roll == p.getRoll()) {
                if ((roll * multiplier) + p.getIndex() < boardEnd && (roll * multiplier) + p.getIndex() >= 0) {
                    p.advance(roll * multiplier);
                    players.poll();
                    canMove = true;
                    return false;
                }
                else if ((roll * multiplier) + p.getIndex() == boardEnd) {
                    p.advance(roll * multiplier);
                    players.poll();
                    canMove = true;
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
        else {
            System.out.print(" is not at the front of the queue. ");
            canMove = false;
            return false;
        }
    }
}