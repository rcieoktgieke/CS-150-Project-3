import java.util.*;
/**
 * SpaceGenerator uses a Gaussian random number to choose one of the six types of spaces.
 * 
 * @Eric Weber
 * @4/28/16
 */
public class SpaceGenerator {
    private Random rand;
    /** The frequency with which fair treasure pots should appear on the board.*/
    private double fairPotFreq;
    /** The number of pieces each fair pot should give to players.*/
    private int fairPotPieces;
    /** The number of times each fair pot can give pieces to players.*/
    private int fairPotTimes;
    /** The frequency with which random treasure pots should appear on the board.*/
    private double randomPotFreq;
    /** The initial number of pieces each random treasure pot should have.*/
    private int randomPotPieces;
    /** The frequency with which holds should appear on the board.*/
    private double holdFreq; 
    /** The minimum factor by which players' rolls should be multiplied upon exiting a hold.*/
    private int minHoldFactor;
    /** The maximum factor by which players' rolls should be multiplied upon exiting a hold.*/
    private int maxHoldFactor;
    /** The frequency with which priority holds should appear on the board.*/
    private double pHoldFreq;
    /** The minimum factor by which players' rolls should be multiplied upon exiting a priority hold.*/
    private int minPHoldFactor;
    /** The maximum factor by which players' rolls should be multiplied upon exiting a priority hold.*/
    private int maxPHoldFactor;
    /** The frequency with which hold queues should appear on the board.*/
    private double holdQFreq;
    /** The minimum factor by which players' rolls should be multiplied upon exiting a hold queue.*/
    private int minHoldQFactor;
    /** The maximum factor by which players' rolls should be multiplied upon exiting a hold queue.*/
    private int maxHoldQFactor;
    /** The frequency with which JStacks should appear on the board.*/
    private double jStackFreq;
    /** The board used for the game. */
    private Board board;
    /** The x dimension of the board. */
    private int x;
    /** The y dimension of the board. */
    private int y;
    /** The z dimension of the board. */
    private int z;
    /** The points for winning in this game. */
    private int winningPoints;
    /** The points for pieces in this game. */
    private double piecesPoints;
    /**
     * Constructor.
     * 
     * Initialize random without seed.
     * @param configScan the Scanner object for the board's config file.
     * @param board the board used for the game.
     */
    public SpaceGenerator(Scanner configScan, Board board) {
        this.board = board;
        rand = new Random();
        readConfig(configScan);
    }
    /**
     * Generates a space (of type BlankSpace, RandTPot, FairTPot, Hold, PHold, HoldQ, or JStack) based on a random number.
     * 
     * @param x the value of the x dimension of the board.
     * @param y the value of the y dimension of the board.
     * @param z the value of the z dimension of the board.
     * @param d the die used for the game.
     * @return a space of type BlankSpace, RandTPot, FairTPot, Hold, PHold, HoldQ, or JStack.
     */
    public Space randomSpace(int x, int y, int z, Die d) {
        double spaceRand = rand.nextInt(100);
        if (spaceRand < fairPotFreq)  {
            return new FairTPot(fairPotPieces, fairPotTimes);
        }
        else if (spaceRand < fairPotFreq + randomPotFreq) {
            return new RandTPot(randomPotPieces);
        }
        else if (spaceRand < fairPotFreq + randomPotFreq + holdFreq) {
            if ((maxHoldFactor - minHoldFactor) > 0) {
                return new Hold(rand.nextInt(maxHoldFactor - minHoldFactor) + minHoldFactor, d);
            }
            else {
                return new Hold(minHoldFactor, d);
            }
        }
        else if (spaceRand < fairPotFreq + randomPotFreq + holdFreq + pHoldFreq) {
            if ((maxPHoldFactor - minPHoldFactor) > 0) {
                int factor = 0;
                while (factor == -1*minPHoldFactor) {
                    factor = rand.nextInt(maxPHoldFactor - minPHoldFactor);
                }
                return new PHold(factor + minPHoldFactor, d);
            }
            else {
                return new PHold(minPHoldFactor, d);
            }
        }
        else if (spaceRand < fairPotFreq + randomPotFreq + holdFreq + pHoldFreq + holdQFreq) {
            if ((maxHoldQFactor - minHoldQFactor) > 0) {
                return new HoldQ(rand.nextInt(maxHoldQFactor - minHoldQFactor) + minHoldQFactor, d);
            }
            else {
                return new HoldQ(minHoldQFactor, d);
            }
        }
        else if (spaceRand < fairPotFreq + randomPotFreq + holdFreq + pHoldFreq + holdQFreq + jStackFreq) {
            if (rand.nextInt(2) == 1) {
                return new JStack(1, x, y, z, board, d);
            }
            else {
                return new JStack(-1, x, y, z, board, d);
            }
        }
        else {
            return new BlankSpace();
        }
    }
    
    /**
     * Generates a non-JStack space (of type BlankSpace, RandTPot, FairTPot, Hold, PHold, or HoldQ) based on a random number.
     * 
     * @param x the value of the x dimension of the board.
     * @param y the value of the y dimension of the board.
     * @param z the value of the z dimension of the board.
     * @param d the die used for the game.
     * @return a space of type BlankSpace, RandTPot, FairTPot, Hold, PHold, or HoldQ.
     */
    public Space noStackSpace(int x, int y, int z, Die d) {
        Space returnSpace = randomSpace(x, y, z, d);
        if (!(returnSpace instanceof JStack)) {
            return returnSpace;
        }
        else {
            return new BlankSpace();
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
        return winningPoints;
    }
    /**
     * Return the points for pieces in this game.
     * 
     * @return the points for pieces in this game.
     */
    public double piecesPoints() {
        return piecesPoints;
    }
    
    private void readConfig(Scanner scan) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Scanner lineScanner = new Scanner(line);
            if (line.substring(0, 5).equals("board")) {
                lineScanner.next();
                x = lineScanner.nextInt();
                y = lineScanner.nextInt();
                z = lineScanner.nextInt();
            }
            else if (line.substring(0, 6).equals("values")) {
                lineScanner.next();
                winningPoints = lineScanner.nextInt();
                piecesPoints = lineScanner.nextDouble();
            }
            else if (line.substring(0, 5).equals("HoldQ")) {
                lineScanner.next();
                holdQFreq = lineScanner.nextInt();
                minHoldQFactor = lineScanner.nextInt();
                maxHoldQFactor = lineScanner.nextInt();
            }
            else if (line.substring(0, 4).equals("Hold")) {
                lineScanner.next();
                holdFreq = lineScanner.nextInt();
                minHoldFactor = lineScanner.nextInt();
                maxHoldFactor = lineScanner.nextInt();
            }
            else if (line.substring(0, 6).equals("JStack")) {
                lineScanner.next();
                jStackFreq = lineScanner.nextInt();
            }
            else if (line.substring(0, 12).equals("treasurePotA")) {
                lineScanner.next();
                fairPotFreq = lineScanner.nextInt();
                fairPotPieces = lineScanner.nextInt();
                fairPotTimes = lineScanner.nextInt();
            }
            else if (line.substring(0, 12).equals("treasurePotB")) {
                lineScanner.next();
                randomPotFreq = lineScanner.nextInt();
                randomPotPieces = lineScanner.nextInt();
            }
            else if (line.substring(0, 12).equals("PriorityHold")) {
                lineScanner.next();
                pHoldFreq = lineScanner.nextInt();
                minPHoldFactor = lineScanner.nextInt();
                maxPHoldFactor = lineScanner.nextInt();
            }
        }
    }
}