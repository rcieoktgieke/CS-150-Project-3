import java.util.*;
/**
 * Players have a number of tokens, and given a roll, choose which to move.
 * 
 * @Eric Weber
 * @4/6/16
 */
public abstract class Player {
    /** The list of tokens belonging to this player */
    protected ArrayList<Token> tokens = new ArrayList<Token>();
    /** This player's number */
    protected int number;
    /**
     * Constructor
     * @param number this player's number.
     * @param numTokens number of tokens this player will have.
     */
    public Player(int number, int numTokens) {
        this.number = number;
        for (int i = 0; i < numTokens; i ++) {
            tokens.add(new Token(number, i));
        }
    }
    /**
     * Choose which token to advance based on the roll given and the strategy used by the player.
     * 
     * @param roll the die roll which will move the token returned.
     * @param board the board used for the game.
     * @param boardEnd the index of the final space on the board.
     * @param dRange upper number for the range of the die.
     * @return token the token which will be moved.
     */
    public abstract Token whichToken(int roll, Board board, int boardEnd, int dRange);
    /**
     * Find the number of pieces belonging to the player (the sum of the number of pieces beloning to each of the tokens belonging to the player).
     * @return the number of pieces belonging to the player.
     */
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
     * Find the number of points the player has based on the status of its tokens and the multipliers for winning and pieces.
     * @return how many points the player has.
     */
    public double getPoints(int boardEnd, int w, double p) {
        double points = 0;
        Iterator<Token> tokenIter = tokens.iterator();
        while (tokenIter.hasNext()) {
            Token cToken = tokenIter.next();
            if (cToken.getIndex() == boardEnd) {
                points += w;
            }
        }
        points += this.getPieces() * p;
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
    public String toString() {
        return "Player"+number;
    }
}