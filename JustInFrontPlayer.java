import java.util.*;
/**
 * A JustInFrontPlayer checks if any player has a token in front of its frontmost token. If one does, it acts like a finish first player. Otherwise, it acts like a JustPointsPlayer.
 * 
 * @Eric Weber
 * @5/7/16
 */
public class JustInFrontPlayer extends Player{
    
    public JustInFrontPlayer(int number, int numTokens) {
        super(number, numTokens);
    }
    public Token whichToken(int roll, Board board, int boardEnd, int dRange, LinkedList<Player> players, int w, double p) {
        int thisIndex = getHighestIndex();
        boolean inFront = true;
        boolean winning = true;
        double points = this.getPoints(boardEnd, w, p);
        Iterator<Player> playerIter = players.iterator();
        while (playerIter.hasNext()) {
            Player cPlayer = playerIter.next();
            if (cPlayer.getPoints(boardEnd, w, p) > points) {
                winning = false;
            }
            if (cPlayer.getHighestIndex() > thisIndex) {
                inFront = false;
            }
        }
        
        Token tokenToMove = tokens.get(0);
        Iterator<Token> tokenIter = tokens.iterator();
        while (tokenIter.hasNext()) {
            Token cToken = tokenIter.next();
            if (cToken.getIndex() != boardEnd) {
                tokenToMove = cToken;
            }
        }
        
        if (inFront) {
            boolean foundPieces = false;
            tokenIter = tokens.iterator();
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
        }
        else {
            int tokenIndex = 0;
            tokenIter = tokens.iterator();
            while (tokenIter.hasNext()) {
                Token cToken = tokenIter.next();
                if (board.get(cToken.getIndex()).canMove(cToken, roll, boardEnd)) {
                    if (winning || cToken.getIndex() + dRange < boardEnd) {
                        if (cToken.getIndex() >= tokenIndex) {
                            tokenToMove = cToken;
                            tokenIndex = cToken.getIndex();
                        }
                    }
                }
            }
        }
        return tokenToMove;
    }
    public String toString() {
        return "JustInFrontPlayer"+number;
    }
}