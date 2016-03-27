import java.util.*;
/**
 * Players have a number of tokens, and given a roll, choose which to move.
 * 
 * @Eric Weber
 * @3/26/16
 */
public class Player {
    
    private ArrayList<Token> tokens = new ArrayList<Token>();
    public Player(int numTokens) {
        for (int i = 0; i < numTokens; i ++) {
            tokens.add(new Token());
        }
    }
    public Token whichToken(int roll) {
        return tokens.get(0);
    }
    public int getPieces() {
        int pieces = 0;
        Iterator<Token> tokenIter = tokens.iterator();
        while (tokenIter.hasNext()) {
            Token cToken = tokenIter.next();
            pieces += cToken.getPieces();
        }
        return pieces;
    }
    /**
     * Print each token, their index, and their pieces.
     */
    public void printTokens() {
        Token cToken;
        Iterator<Token> tokenIter = tokens.iterator();
        while (tokenIter.hasNext()) {
            cToken = tokenIter.next();
            System.out.println("    " + cToken + ": " + cToken.getIndex() + " Pieces: " + cToken.getPieces());
        }
    }
}