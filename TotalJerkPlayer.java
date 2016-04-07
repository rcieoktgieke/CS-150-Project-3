import java.util.*;
/**
 * A TotalJerkPlayer reserves 5 of its tokens to stay put on any Space that could prevent other player's tokens from moving (i.e. PHold and HoldQ).
 * 
 * @Eric Weber
 * @4/7/16
 */
public class TotalJerkPlayer extends Player {
    
    public TotalJerkPlayer(int number, int numTokens) {
        super(number, numTokens);
    }
    public Token whichToken(int roll, Board board, int boardEnd, int dRange, LinkedList<Player> players, int w, double p) {
        return new Token(1, 1);
    }
}