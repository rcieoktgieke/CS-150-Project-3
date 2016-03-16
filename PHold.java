import java.util.*;
/**
 * When a player lands on a priority hold, they are assigned the priority equal to the number rolled to land on it. They may only leave the space when no players with a lower number priority are on the priority hold with them. When a player is on a priority hold and does not have the lowest number priority, they end their turn without rolling. When they are on a priority hold and do have the lowest number priority, they roll, and advance the number of spaces rolled times a number specified for that priority hold when the board is created, which will be between two numbers set in configuration.
 * 
 * @Eric Weber
 * @3/5/16
 */
public class PHold implements Space, Comparator<Player> {
    
    /** The number by which to multiply rolls leaving the hold.*/
    private int multiplier;
    /** The ability of the player last passed to takeTurn to move. */
    private boolean canMove;
    /** The queue of players currently in the hold */
    private PriorityQueue<Player> players;
    /**
     * Constructor.
     * 
     * @param multiplier number by which to multiply rolls leaving the hold.
     */
    public PHold(int multiplier) {
        this.multiplier = multiplier;
        players = new PriorityQueue<Player>(1, this);
    }
    /**
     * Print the player and the status of the hold. Set the roll stored by the player. Add the player to queue of players currently in the hold.
     */
    public void land(Player p, Die d) {
        p.setRoll(d.prevRoll());
        players.add(p);
        System.out.print(p + " has landed on " + getStatus());
    }
    /**
     * @return if the player is at the front of the queue and if moving the player would go beyond the bounds of the board.
     */
    public boolean canMove() {
        return canMove;
    }
    
    public String getStatus() {
        return "P.Hold:" + multiplier + "|" + players.size();
    }
    
    /**
     * Take player's turn.
     * 
     * Print the player. If the player is first in the queue, roll the die. Print the roll. If the roll is within the bounds of the board, advance the player.
     */
    public boolean takeTurn(Player p, Die d, int boardEnd) {
        boolean atFront = true;
        int maxRoll = players.peek().getRoll();
        Iterator<Player> playerIter = players.iterator();
        while (atFront && playerIter.hasNext()) {
            Player cPlayer = playerIter.next();
            if (cPlayer == p && cPlayer.getRoll() <= maxRoll) {
                /** Normal space behavior.*/
                int roll = d.roll();
                System.out.print(p + " is at the front of the queue and has rolled " + roll + ". ");
                if ((roll * multiplier) + p.getIndex() < boardEnd && (roll * multiplier) + p.getIndex() >= 0) {
                    playerIter.remove();
                    canMove = true;
                    p.advance(roll * multiplier);
                    return false;
                }
                else if ((roll * multiplier) + p.getIndex() == boardEnd) {
                    playerIter.remove();
                    p.advance(roll * multiplier);
                    return true;
                }
                else {
                    canMove = false;
                    return false;
                }
            }
            else if (cPlayer.getRoll() > maxRoll) {
                atFront = false;
                System.out.print(p + " is not at the front of the queue. ");
                canMove = false;
                return false;
            }
        }
        return false;
    }
    
    /**
     * Compare to Player objects for their priority in the queue.
     * 
     * Player 1 is greater than player 2 if player 1's entry roll is greater or equal to player 2's. Player 1 is less than player 2 if player 1's entry roll is less than player 2's.
     * @param p1 player 1.
     * @param p2 player 2.
     * @return 1 if player 1 is greater; -1 if player 1 is less.
     */
    public int compare(Player p1, Player p2) {
        if (p1.getRoll() >= p2.getRoll()) {
            return 1;
        }
        else {
            return -1;
        }
    }
}