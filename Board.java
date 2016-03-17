import java.util.*;
import java.lang.Math.*;
import java.io.File;
/**
 * The board stores spaces and provides access to them to main.
 * 
 * @Eric Weber
 * @3/16/16
 */
public class Board {
    
    /** The list of spaces representing the board */
    private ArrayList<Space> board;
    /** The value of the x dimension of the board. */
    private int x;
    /** The value of the y dimension of the board. */
    private int y;
    /** The value of the z dimension of the board. */
    private int z;
    /** The number of spaces on the board */
    private int numberOfSpaces;
    /**
     * Constructor.
     * 
     * Initialize the board and set each space based on a Gaussian random number.
     * @param fileName the name of the config file for the various frequencies and initial details of the spaces.
     * @param x the value of the x dimension of the board.
     * @param y the value of the y dimension of the board.
     * @param z the value of the z dimension of the board.
     */
    public Board(int x, int y, int z, Scanner scan) {
        this.x = x;
        this.y = y;
        this.z = z;
        numberOfSpaces = x*y*z;
        board = new ArrayList<Space>(numberOfSpaces);
        board.add(new BlankSpace());
        SpaceGenerator spaceGen = new SpaceGenerator(scan);
        /**Iterate through the board, creating a new space of a type defined by a Gaussian random number at each index.*/
        for (int i = 1; i < (numberOfSpaces); i ++) {
            board.add(spaceGen.randomSpace());
        }
    }
    /**
     * Get the number of spaces on the board.
     * 
     * @return the number of spaces on the board.
     */
    public int numberOfSpaces() {
        return x*y*z;
    }
    /**
     * Get the space at the given index.
     * 
     * @param index the index of the desired space.
     * @return the space at the given index.
    */
    public Space get(int index) {
        return board.get(index);
    }
    /**
     * Print the status of each space on the board.
     */
    public void printBoard() {
        for (int zIndex = 0; zIndex < z; zIndex ++) {
            for (int yIndex = 0; yIndex < y; yIndex ++) {
                for (int xIndex = 0; xIndex < x; xIndex ++) {
                    System.out.printf("%-25s", zIndex*(x*y)+yIndex*x+xIndex + ": " + board.get(zIndex*(x*y)+yIndex*x+xIndex).getStatus());
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}