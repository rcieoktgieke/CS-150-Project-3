import java.util.Random;
/**
 * The Die has an upper limit for its range and a roll function that generates a number between one and that limit.
 * 
 * @Eric Weber
 * @4/7/16
 */
public class Die {
    
    /** The upper number for the range of the die. */
    private int range;
    /** The value last rolled by the die. */
    private int prevRoll;
    /** The seed (if any) for the random number generator. */
    private int seed;
    /** Whether the seed is set. */
    private boolean hasSeed = false;
    /**
     * Constructor.
     * 
     * @param range upper number for the range of the die.
     */
    public Die(int range) {
        this.range = range;
    }
    /**
     * Constructor.
     * 
     * @param range upper number for the range of the die.
     * @param seed the seed for the random number generator.
     */
    public Die(int range, int seed) {
        this.seed = seed;
        hasSeed = true;
        this.range = range;
    }
    
    /**
     * Roll the die.
     * 
     * Store roll for later use.
     * @return the value rolled, between 1 and the upper limit for the range, inclusive.
     */
    public int roll() {
        Random r;
        if (hasSeed) {
            r = new Random();
        }
        else {
            r = new Random();
        }
        prevRoll = r.nextInt(range) + 1;
        return prevRoll;
    }
    
    /**
     * Get the value last rolled.
     * 
     * @return the value last rolled.
     */
    public int prevRoll() {
        return prevRoll;
    }
}