import java.util.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class FinishFirstPlayerTest.
 *
 * @Eric Weber
 * @4/6/16
 */
public class FinishFirstPlayerTest
{
    /**
     * Default constructor for test class FinishFirstPlayerTest
     */
    public FinishFirstPlayerTest()
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
    public void testWhichToken()
    {
        try {
            Scanner scan = new Scanner(new File("playerTestConfig.txt"));
            Die die = new Die(5);
            Board board = new Board(scan, die);
            FinishFirstPlayer finishFi1 = new FinishFirstPlayer(5);
            Token t1 = finishFi1.whichToken(4, board, 10, 5);
            t1.advance(4);
            assertTrue(t1 == finishFi1.whichToken(4, board, 10, 5));
            t1.advance(4);
            assertFalse(t1 == finishFi1.whichToken(4, board, 10, 5));
            
            Token t2 = finishFi1.whichToken(4, board, 10, 5);
            t2.advance(4);
            assertTrue(t2 == finishFi1.whichToken(4, board, 10, 5));
            t2.advance(4);
            assertFalse(t2 == finishFi1.whichToken(4, board, 10, 5) && t1 == finishFi1.whichToken(4, board, 10, 5));
            
            Token t3 = finishFi1.whichToken(4, board, 10, 5);
            t3.advance(4);
            assertTrue(t3 == finishFi1.whichToken(4, board, 10, 5));
            t3.advance(4);
            assertFalse(t3 == finishFi1.whichToken(4, board, 10, 5) && t2 == finishFi1.whichToken(4, board, 10, 5) && t1 == finishFi1.whichToken(4, board, 10, 5));
        }
        catch (Exception e) {
            assertTrue(false);
        }
    }
}