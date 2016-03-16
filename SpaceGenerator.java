import java.util.*;
/**
 * SpaceGenerator uses a Gaussian random number to choose one of the six types of spaces.
 * 
 * @Eric Weber
 * @3/16/16
 */
public class SpaceGenerator {
    private Random rand;
    /** The frequency with which fair treasure pots should appear on the board.*/
    private double fairPotFreq = 1.28;
    /** The number of pieces each fair pot should give to players.*/
    private int fairPotPieces;
    /** The number of times each fair pot can give pieces to players.*/
    private int fairPotTimes;
    /** The frequency with which random treasure pots should appear on the board.*/
    private double randomPotFreq = .437;
    /** The initial number of pieces each random treasure pot should have.*/
    private int randomPotPieces;
    /** The frequency with which holds should appear on the board.*/
    private double holdFreq = .318; 
    /** The minimum factor by which players' rolls should be multiplied upon exiting a hold.*/
    private int minHoldFactor;
    /** The maximum factor by which players' rolls should be multiplied upon exiting a hold.*/
    private int maxHoldFactor;
    /** The frequency with which priority holds should appear on the board.*/
    private double pHoldFreq = .273;
    /** The minimum factor by which players' rolls should be multiplied upon exiting a priority hold.*/
    private int minPHoldFactor;
    /** The maximum factor by which players' rolls should be multiplied upon exiting a priority hold.*/
    private int maxPHoldFactor;
    /** The frequency with which hold queues should appear on the board.*/
    private double holdQFreq = .252;
    /** The minimum factor by which players' rolls should be multiplied upon exiting a hold queue.*/
    private int minHoldQFactor;
    /** The maximum factor by which players' rolls should be multiplied upon exiting a hold queue.*/
    private int maxHoldQFactor;
    /**
     * Constructor.
     * 
     * Initialize random without seed.
     * @param scan the Scanner object for the board's config file.
     */
    public SpaceGenerator(Scanner scan) {
        rand = new Random();
        readConfig(scan);
    }
    /**
     * Generates a space (of type BlankSpace, RandTPot, FairTPot, Hold, PHold, or HoldQ) based on a Gaussian random number.
     * 
     * @return a space of type BlankSpace, RandTPot, FairTPot, Hold, PHold, or HoldQ.
     */
    public Space gaussianSpace() {
        double spaceRand = rand.nextGaussian();
        if (spaceRand < -1*fairPotFreq)  {
            return new FairTPot(fairPotPieces, fairPotTimes);
        }
        else if (spaceRand < -1*fairPotFreq + randomPotFreq) {
            return new RandTPot(randomPotPieces);
        }
        else if (spaceRand < -1*fairPotFreq + randomPotFreq + holdFreq) {
            if ((maxHoldFactor - minHoldFactor) > 0) {
                return new Hold(rand.nextInt(maxHoldFactor - minHoldFactor) + minHoldFactor);
            }
            else {
                return new Hold(minHoldFactor);
            }
        }
        else if (spaceRand < -1*fairPotFreq + randomPotFreq + holdFreq + pHoldFreq) {
            if ((maxPHoldFactor - minPHoldFactor) > 0) {
                return new PHold(rand.nextInt(maxPHoldFactor - minPHoldFactor) + minPHoldFactor);
            }
            else {
                return new PHold(minPHoldFactor);
            }
        }
        else if (spaceRand < -1*fairPotFreq + randomPotFreq + holdFreq + pHoldFreq + holdQFreq) {
            if ((maxHoldQFactor - minHoldQFactor) > 0) {
                return new HoldQ(rand.nextInt(maxHoldQFactor - minHoldQFactor) + minHoldQFactor);
            }
            else {
                return new HoldQ(minHoldQFactor);
            }
        }
        else {
            return new BlankSpace();
        }
    }
    private void readConfig(Scanner scan) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Scanner lineScanner = new Scanner(line);
            if (line.substring(0, 5).equals("HoldQ")) {
                lineScanner.next();
                lineScanner.nextInt();//holdQFreq = lineScanner.nextInt();
                minHoldQFactor = lineScanner.nextInt();
                maxHoldQFactor = lineScanner.nextInt();
            }
            else if (line.substring(0, 4).equals("Hold")) {
                lineScanner.next();
                lineScanner.nextInt();//holdFreq = lineScanner.nextInt();
                minHoldFactor = lineScanner.nextInt();
                maxHoldFactor = lineScanner.nextInt();
            }
            else if (line.substring(0, 6).equals("JStack")) {
                /*lineScanner.next();
                lineScanner.nextInt();//holdFreq = lineScanner.nextInt();
                minHoldFactor = lineScanner.nextInt();
                maxHoldFactor = lineScanner.nextInt();*/
            }
            else if (line.substring(0, 12).equals("treasurePotA")) {
                lineScanner.next();
                lineScanner.nextInt();//fairPotFreq = lineScanner.nextInt();
                fairPotPieces = lineScanner.nextInt();
                fairPotTimes = lineScanner.nextInt();
            }
            else if (line.substring(0, 12).equals("treasurePotB")) {
                lineScanner.next();
                lineScanner.nextInt();//randomPotFreq = lineScanner.nextInt();
                randomPotPieces = lineScanner.nextInt();
            }
            else if (line.substring(0, 12).equals("PriorityHold")) {
                lineScanner.next();
                lineScanner.nextInt();//pHoldFreq = lineScanner.nextInt();
                minPHoldFactor = lineScanner.nextInt();
                maxPHoldFactor = lineScanner.nextInt();
            }
        }
    }
}