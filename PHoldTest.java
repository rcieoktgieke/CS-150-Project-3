import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class PHoldTest.
 *
 * @Eric Weber
 * @4/6/16
 */
public class PHoldTest
{
    private PHold pHold;
    private Token t;
    private Token t1;
    private Token t2;
    private Token t3;
    private Die d;
    private int boardEnd;
    /**
     * Default constructor for test class PHoldTest
     */
    public PHoldTest()
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
        d = new Die(10);
        pHold = new PHold(3, d);
        t1 = new Token(1, 1);
        t2 = new Token(1, 2);
        t3 = new Token(1, 3);
        boardEnd = 21;
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
            t = new Token(1, i);
            int tokenIndex = t.getIndex();
            int roll = d.prevRoll();
            pHold.land(t, d);
            while (d.prevRoll() != roll) {
                assertFalse(pHold.canMove(t, d.prevRoll(), boardEnd));
                d.roll();
            }
            assertTrue(pHold.canMove(t, d.prevRoll(), boardEnd));
        }
    }
    @Test
    public void testAdvanced()
    {
        boolean reachedEnd = false;
        boolean passedBounds = false;
        for (int i = 0; (i < 100) || !reachedEnd || !passedBounds; i ++) {
            pHold = new PHold(3, d);
            t = new Token(1, i);
            int tokenIndex = t.getIndex();
            int roll = d.prevRoll();
            pHold.land(t, d);
            pHold.takeTurn(t, d.roll(), boardEnd);
            if (tokenIndex + (d.prevRoll() * 3) >= 0 && tokenIndex + (d.prevRoll() * 3) < boardEnd) {
                assertTrue(pHold.advanced());
            }
            else if (tokenIndex + (d.prevRoll() * 3) == boardEnd) {
                assertTrue(pHold.advanced());
                reachedEnd = true;
            }
            else {
                assertFalse(pHold.advanced());
                passedBounds = true;
            }
        }
    }
    
    @Test
    public void testCompare()
    {
        t1.setRoll(10);
        t2.setRoll(5);
        assertEquals(1, pHold.compare(t1.getRoll(), t2.getRoll()));
        t1.setRoll(10);
        t2.setRoll(10);
        assertEquals(0, pHold.compare(t1.getRoll(), t2.getRoll()));
        t1.setRoll(5);
        t2.setRoll(10);
        assertEquals(-1, pHold.compare(t1.getRoll(), t2.getRoll()));
    }

    @Test
    public void testTakeTurn()
    {
        for (int i = 0; i < 500; i ++) {
            Token t = new Token(1, i);
            int tokenIndex = t.getIndex();
            pHold.land(t, d);
            boolean takeTurnOutput = pHold.takeTurn(t, d.prevRoll(), boardEnd);
            
            if (tokenIndex + (d.prevRoll() * 3) > 0 && tokenIndex + (d.prevRoll() * 3) < boardEnd) {
                assertEquals(false, takeTurnOutput);
                assertEquals(t.getIndex(), (tokenIndex + (d.prevRoll() * 3)));
            }
            else if (tokenIndex + (d.prevRoll() * 3) == boardEnd) {
                assertEquals(true, takeTurnOutput);
                assertEquals(t.getIndex(), boardEnd);
            }
            else {
                assertEquals(t.getIndex(), tokenIndex);
            }
        }
    }
}
