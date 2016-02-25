/**
 * When a player lands on a fair treasure pot, it gives that player a number of pieces specified by the game’s configuration. However, each pot can only give pieces to a certain number of players (also specified in configuration). If a player lands on the pot after the maximum number of players is reached, the pot is treated as a blank space. .
 * 
 * @Eric Weber
 * @2/21/16
 */
public class FairTPot extends Space {
    
    private int amount;
    private int maxTimes;
    private int times = 0;
    public FairTPot(int amount, int maxTimes) {
        this.amount = amount;
        this.maxTimes = maxTimes;
    }
    public boolean canGive() {
        if (times < maxTimes) {
            return true;
        }
        else {
            return false;
        }
    }
    public int givePieces() {
        times ++;
        return amount;
    }
}