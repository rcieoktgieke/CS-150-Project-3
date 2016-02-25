/**
 * The Die has an upper limit for its range and a roll function that generates a number between one and that limit.
 * 
 * @Eric Weber
 * @2/21/16
 */
import java.util.Random;
public class Die {
    
    private int range;
    public Die(int range) {
        this.range = range;
    }
    public int roll() {
        Random r = new Random();
        return r.nextInt(range) + 1;
    }
}