import java.util.*;
/**
 * A TotalJerkPlayer reserves half of its tokens to stay put on any Space that could prevent other player's tokens from moving (i.e. PHold and HoldQ), unless none of its other tokens can move. If at least one of its moving tokens can move, it moves the token with the highest index.
 * 
 * @Eric Weber
 * @4/7/16
 */
public class TotalJerkPlayer extends Player {

    /** The number of tokens this player has. */
    private int numTokens;
    public TotalJerkPlayer(int number, int numTokens) {
        super(number, numTokens);
        this.numTokens = numTokens;
    }

    public Token whichToken(int roll, Board board, int boardEnd, int dRange, LinkedList<Player> players, int w, double p) {
        
        Token tokenToMove = null;
        /** First tokens - hope to move one. */
        Iterator<Token> tokenIter = tokens.iterator();
        int i = 0;
        int tokenIndex = 0;
        while (tokenIter.hasNext() && i < numTokens / 2) {
            i ++;
            Token cToken = tokenIter.next();
            if (board.get(cToken.getIndex()).canMove(cToken, roll, boardEnd)) {
                if (cToken.getIndex() >= tokenIndex) {
                    tokenToMove = cToken;
                    tokenIndex = cToken.getIndex();
                }
            }
        }
        while (tokenIter.hasNext()) {
            Token cToken = tokenIter.next();
            if ((!(board.get(cToken.getIndex()) instanceof PHold) && !(board.get(cToken.getIndex()) instanceof HoldQ)) || tokenToMove == null){
                if (board.get(cToken.getIndex()).canMove(cToken, roll, boardEnd)) {
                    tokenToMove = cToken;
                }
            }
        }
        if (tokenToMove == null) {
            tokenToMove = tokens.get(0);
        }
        return tokenToMove;
    }
}