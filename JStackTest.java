import java.util.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class JStackTest.
 *
 * @Eric Weber
 * @5/7/16
 */
public class JStackTest
{
    private JStack jStack;
    private Die d;
    /**
     * Default constructor for test class JStackTest
     */
    public JStackTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() throws FileNotFoundException
    {
        jStack = new JStack(1, 4, 2, 5, new Board(new Scanner(new File("jStackTestConfig.txt")), new ArrayList<Scanner>(), new Die(10)), new Die(10));
        d = new Die(10);
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
    public void testCanMove()
    {
        for (int i = 0; i < 100; i ++) {
            Token t = new Token(1, i);
            jStack.land(t, d);
            assertEquals(true, jStack.canMove(t, d.roll(), 100));
        }
    }
    
    @Test
    public void testTakeTurn()
    {
        for (int i = 0; i < 500; i ++) {
            Token t = new Token(1, i);
            t.advance(d.roll());
            int prevIndex = t.getIndex();
            boolean gameOver = jStack.takeTurn(t, d.roll(), 39);
            if (((prevIndex/(4*2) + d.prevRoll() % 5) - prevIndex/(4*2))*2*4 + prevIndex == 39) {
                assertEquals(true, gameOver);
            }
            else {
                assertEquals(false, gameOver);
            }
        }
    }
}