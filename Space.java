/**
 * Spaces are parts of the board at specific indecies.
 * 
 * @Eric Weber
 * @3/3/16
 */
public interface Space {
    
    /**
     * Land player on space.
     * 
     * Executes appropriate operations for when a player lands on the space.
     * @param p player that is landing on this space.
     * @param d die that is used for the game.
     */
    public void land(Player p, Die d);
    /**
     * Check if the player last passed to takeTurn can move.
     * 
     * @return if player can move.
     */
    public boolean canMove();
    /**
     * Return the type and vital information of the space.
     * 
     * @return the type and vital information of the space.
     */
    public String getStatus();
    /**
     * Take the turn of the player p.
     * 
     * Executes appropriate operations for the player to take its turn.
     * @param p player that is taking its turn.
     * @param d die that is used for the game.
     * @param boardEnd the index of the final space on the board.
     * 
     * @return if player has reached the final space by exact count.
     */
    public boolean takeTurn(Player p, Die d, int boardEnd);
}