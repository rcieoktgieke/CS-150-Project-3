import java.util.*;
import java.lang.Math.*;
import java.io.File;
/**
 * The board stores spaces and provides access to them to main.
 * 
 * @Eric Weber
 * @4/2/16
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
    /** The number of spaces on the board. */
    private int numberOfSpaces;
    /** The space generator for the board. */
    private SpaceGenerator spaceGen;
    /**
     * Constructor.
     * 
     * Initialize the board and set each space based on a Gaussian random number.
     * @param scan the Scanner object reading the file.
     * @param d the die used for the game.
     */
    public Board(Scanner scan, Die d) {
        spaceGen = new SpaceGenerator(scan, this);
        x = spaceGen.getX();
        y = spaceGen.getY();
        z = spaceGen.getZ();
        numberOfSpaces = x*y*z;
        board = new ArrayList<Space>(numberOfSpaces);
        board.add(new BlankSpace());
        /**Iterate through the board, creating a new space of a type defined by a Gaussian random number at each index.*/
        for (int i = 1; i < (numberOfSpaces - x*y); i ++) {
            board.add(spaceGen.randomSpace(x, y, z, d));
        }
        for (int i = (numberOfSpaces - x*y); i < (numberOfSpaces - x); i ++) {
            board.add(spaceGen.noStackSpace(x, y, z, d));
        }
        for (int i = 0; i < x; i ++) {
            board.add(new BlankSpace());
        }
    }
    /**
     * Return the x dimension of the board.
     * 
     * @return the x dimension of the board.
     */
    public int getX() {
        return x;
    }
    /**
     * Return the y dimension of the board.
     * 
     * @return the y dimension of the board.
     */
    public int getY() {
        return y;
    }
    /**
     * Return the z dimension of the board.
     * 
     * @return the z dimension of the board.
     */
    public int getZ() {
        return z;
    }
    /**
     * Return the points for winning in this game.
     * 
     * @return the points for winning in this game.
     */
    public int winningPoints() {
        return spaceGen.winningPoints();
    }
    /**
     * Return the points for pieces in this game.
     * 
     * @return the points for pieces in this game.
     */
    public double piecesPoints() {
        return spaceGen.piecesPoints();
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
                    System.out.printf("%-25s", "(" + xIndex + ", " + yIndex + ", " + zIndex + "): " + board.get(zIndex*(x*y)+yIndex*x+xIndex).getStatus());
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}