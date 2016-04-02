import java.util.Random;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class TokenTest.
 *
 * @Eric Weber
 * @4/1/16
 */
public class TokenTest
{
    /**
     * Default constructor for test class TokenTest
     */
    public TokenTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testAdvance()
    {
        Random rand = new Random();
        for (int i = 0; i < 100; i ++) {
            Token token1 = new Token();
            int index = 0;
            for (int j = 0; j < 100; j ++) {
                int number = rand.nextInt(20);
                token1.advance(number);
                index += number;
                assertEquals(index, token1.getIndex());
            }
        }
        //Wow look at that 1+1 = 2! Who'd have thought?
    }
    
    @Test
    public void testAddPieces()
    {
        Random rand = new Random();
        for (int i = 0; i < 100; i ++) {
            Token token1 = new Token();
            int pieces = 0;
            for (int j = 0; j < 100; j ++) {
                int number = rand.nextInt(20);
                token1.advance(number);
                pieces += number;
                assertEquals(pieces, token1.getIndex());
            }
        }
        //Wow look at that 1+1 = 2! Who'd have thought?
    }
}