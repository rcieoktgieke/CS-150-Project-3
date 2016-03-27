import java.util.*;
/**
 * Players have a number of tokens, and given a roll, choose which to move.
 * 
 * @Eric Weber
 * @3/27/16
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
    public double getPoints(int boardEnd, int w, double p) {
        double points = 0;
        Iterator<Token> tokenIter = tokens.iterator();
        while (tokenIter.hasNext()) {
            Token cToken = tokenIter.next();
            if (cToken.getIndex() == boardEnd) {
                points += w;
            }
            points += cToken.getPieces() * p;
        }
        return points;
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