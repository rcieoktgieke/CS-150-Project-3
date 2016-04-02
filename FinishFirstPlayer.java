import java.util.*;
/**
 * A FinishFirstPlayer advances its furthest token (when it may advance) until it comes within a single die roll of the finish, at which point it moves its other tokens in a similar fashion until it rolls a number required to end the game and would have the highest score if it ended the game.
 * 
 * @Eric Weber
 * @4/2/16
 */
public class FinishFirstPlayer extends Player {
    
    /**
     * Constructor
     * @param numTokens number of tokens this player will have.
     */
    public FinishFirstPlayer(int numTokens) {
        super(numTokens);
    }
    /**
     * Choose which token to advance based on the roll given and the strategy used by the player.
     * 
     * @param roll the die roll which will move the token returned.
     * @return token the token which will be moved.
     */
    public Token whichToken(int roll) {
        Token tokenToMove = tokens.get(0);
        int tokenIndex = 0;
        Iterator<Token> tokenIter = tokens.iterator();
        while (tokenIter.hasNext()) {
            Token cToken = tokenIter.next();
        }
        return tokenToMove;
    }
}