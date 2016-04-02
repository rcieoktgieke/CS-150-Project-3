/**
 * A FinishFirstPlayer advances its furthest token (when it may advance) until it comes within a single die roll of the finish, at which point it moves its other tokens in a similar fashion until it rolls a number required to end the game and would have the highest score if it ended the game.
 * 
 * @Eric Weber
 * @4/1/16
 */
public class FinishFirstPlayer extends Player {
    
    /**
     * Constructor
     * @param numTokens number of tokens this player will have.
     */
    public FinishFirstPlayer(int numTokens) {
        super(numTokens);
    }
    
}