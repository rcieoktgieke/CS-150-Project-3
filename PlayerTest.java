import java.util.Random;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PlayerTest.
 *
 * @Eric Weber
 * @4/1/16
 */
public class PlayerTest
{
    /**
     * Default constructor for test class PlayerTest
     */
    public PlayerTest()
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
    public void testGetPieces()
    {
        Random rand = new Random();
        int pieces = 0;
        Player p = new Player(20);
        for (int i = 0; i < 500; i ++) {
            int piecesToAdd = rand.nextInt(20);
            p.whichToken(rand.nextInt(15)).addPieces(piecesToAdd);
            pieces += piecesToAdd;
            assertEquals(pieces, p.getPieces());
        }
    }
    
    @Test
    public void testGetPoints()
    {
        for (int j = 0; j < 100; j ++) {
            Random rand = new Random();
            Player p = new Player(20);
            p.whichToken(rand.nextInt(15)).advance(30);
            for (int i = 0; i < 100; i ++) {
                p.whichToken(rand.nextInt(15)).addPieces(rand.nextInt(20));
            }
            assertTrue(p.getPoints(30, 100, 1.4) == (100.00 + p.getPieces()*1.4));
        }
    }
}