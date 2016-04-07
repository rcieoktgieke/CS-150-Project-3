import java.util.*;
/**
 * JustPointsPlayers choose to move tokens (which are free to move) only if they will land (or have a chance to land) on a treasure pot.
 * 
 * @Eric Weber
 * @4/6/16
 */
public class JustPointsPlayer extends Player {
    
    public JustPointsPlayer(int number, int numTokens) {
        super(number, numTokens);
    }
    public Token whichToken(int roll, Board board, int boardEnd, int dRange, LinkedList<Player> players, int w, double p) {
        Token tokenToMove = tokens.get(0);
        
        boolean foundPieces = false;
        Iterator<Token> tokenIter = tokens.iterator();
        while (tokenIter.hasNext()) {
            Token cToken = tokenIter.next();
            if (board.get(cToken.getIndex()).canMove(cToken, roll, boardEnd)) {
                if (board.get(cToken.getIndex()) instanceof BlankSpace || board.get(cToken.getIndex()) instanceof RandTPot || board.get(cToken.getIndex()) instanceof FairTPot) {
                    if (board.get(cToken.getIndex() + roll) instanceof RandTPot) {
                        RandTPot pot = (RandTPot) board.get(cToken.getIndex() + roll);
                        if (pot.hasPieces()) {
                            foundPieces = true;
                            tokenToMove = cToken;
                        }
                    }
                    else if (board.get(cToken.getIndex() + roll) instanceof FairTPot) {
                        FairTPot pot = (FairTPot) board.get(cToken.getIndex() + roll);
                        if (pot.hasPieces()) {
                            foundPieces = true;
                            tokenToMove = cToken;
                        }
                    }
                }
                else if (!foundPieces) {
                    tokenToMove = cToken;
                }
            }
        }
        return tokenToMove;
    }
}