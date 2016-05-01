import java.util.*;
import java.lang.Math.*;
import java.io.File;
/**
 * The board stores spaces and provides access to them to main.
 * 
 * @Eric Weber
 * @4/30/16
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
     * @param graphScanners the Scanner objects for the graph config files.
     * @param d the die used for the game.
     */
    public Board(Scanner configScan, ArrayList<Scanner> graphScans, Die d) {
        spaceGen = new SpaceGenerator(configScan, this);
        x = spaceGen.getX();
        y = spaceGen.getY();
        z = spaceGen.getZ();
        numberOfSpaces = x*y*z;
        board = new ArrayList<Space>(numberOfSpaces);
        board.add(new BlankSpace());
        /**Iterate through the board, creating a new space of a type defined by a random number at each index.*/
        for (int i = 1; i < (numberOfSpaces - x); i ++) {
            board.add(spaceGen.randomSpace(x, y, z, d));
        }
        for (int i = 0; i < x; i ++) {
            board.add(new BlankSpace());
        }
        ArrayList<Integer> mazeIndices = new ArrayList<Integer>();
        for (Scanner graphScan : graphScans) {
            insertGraph(graphScan, mazeIndices);
        }
    }
    private void insertGraph(Scanner graphScan, ArrayList<Integer> mazeIndices) {
        Maze maze = new Maze();
        Random rand = new Random();
        
        HashMap<Integer, MazeNode> nodes = new HashMap<Integer, MazeNode>();
        ArrayList<MazeNode> entrances = new ArrayList<MazeNode>();
        ArrayList<MazeNode> exits = new ArrayList<MazeNode>();
        while (graphScan.hasNextLine()) {
            Scanner lineScanner = new Scanner(graphScan.nextLine());
            Integer key1 = lineScanner.nextInt();
            lineScanner.next();
            Integer key2 = lineScanner.nextInt();
            Integer weight = lineScanner.nextInt();
            
            exits.remove(nodes.get(key1));
            entrances.remove(nodes.get(key2));
            if (!nodes.containsKey(key1)) {
                nodes.put(key1, new MazeNode(key1));
                entrances.add(nodes.get(key1));
            }
            if (nodes.containsKey(key2)) {
                nodes.get(key1).addEdge(nodes.get(key2), weight);
            }
            else {
                nodes.put(key2, new MazeNode(key2));
                nodes.get(key1).addEdge(nodes.get(key2), weight);
                exits.add(nodes.get(key2));
            }
        }
        
        for (MazeNode entrance : entrances) {
            boolean inserted = false;
            while (!inserted) {
                int index = rand.nextInt(numberOfSpaces - x - 1) + 1;
                if (!mazeIndices.contains(index)) {
                    mazeIndices.add(index);
                    board.set(index, new MazeEntrance(maze, entrance));
                    inserted = true;
                }
            }
        }
        for (MazeNode exit : exits) {
            boolean inserted = false;
            while (!inserted) {
                int index = rand.nextInt(numberOfSpaces - x - 1) + 1;
                if (!mazeIndices.contains(index)) {
                    mazeIndices.add(index);
                    board.set(index, new MazeExit(maze));
                    inserted = true;
                }
            }
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