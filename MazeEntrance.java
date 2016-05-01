/**
 * 
 *  
 * @Eric Weber
 * @4/30/16
 */
public class MazeEntrance implements Space {
    
    private Maze maze;
    private MazeNode node;
    /**
     * Constructor.
     */
    public MazeEntrance(Maze maze, MazeNode node) {
        this.maze = maze;
        this.node = node;
    }
    public void land(Token t, Die d) {
        System.out.print(t + " has landed on " + getStatus() + ". ");
        maze.enterMaze(t, node);
    }
    /**
     * @return if moving the token would go beyond the bounds of the board.
     */
    public boolean canMove(Token t, int roll, int boardEnd) {
        return maze.canMove(t, roll);
    }
    /**
     * @return true
     */
    public boolean advanced() {
       return true;
    }
    
    public String getStatus() {
        return "MazeEntrance";
    }
    
    /**
     * Take token's turn.
     * 
     * Print the token and the roll. If the roll is within the bounds of the board, advance the token.
     */
    public boolean takeTurn(Token t, int roll, int boardEnd) {
        maze.takeTurn(t, roll, boardEnd);
        return false;
    }
}