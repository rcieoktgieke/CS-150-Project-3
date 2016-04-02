import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class BlankSpaceTest.
 *
 * @Eric Weber
 * @3/16/16
 */
public class BlankSpaceTest
{
    private BlankSpace bSpace;
    private Token t;
    private Die d;
    private int boardEnd;
    /**
     * Default constructor for test class BlankSpaceTest
     */
    public BlankSpaceTest()
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
        bSpace = new BlankSpace();
        t = new Token();
        d = new Die(10);
        boardEnd = 15;
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
    public void testDefaultCanMove()
    {
        assertEquals(true, bSpace.canMove());
    }
    @Test
    public void testCanMove()
    {
        for (int i = 0; i < 100; i ++) {
            t = new Token();
            int tokenIndex = t.getIndex();
            bSpace.takeTurn(t, d.roll(), boardEnd);
            if (tokenIndex + d.prevRoll() > 0 && tokenIndex + d.prevRoll() < boardEnd) {
                assertTrue(bSpace.canMove());
            }
            else if (tokenIndex + d.prevRoll() == boardEnd) {
                assertTrue(bSpace.canMove());
            }
            else {
                assertFalse(bSpace.canMove());
            }
        }
    }
    
    @Test
    public void testGetStatus()
    {
        assertEquals("Blank", bSpace.getStatus());
    }

    @Test
    public void testLand()
    {
        bSpace.land(t, d);
    }

    @Test
    public void testTakeTurn()
    {
        for (int i = 0; i < 100; i ++) {
            t = new Token();
            int tokenIndex = t.getIndex();
            boolean takeTurnOutput = bSpace.takeTurn(t, d.roll(), boardEnd);
            if (tokenIndex + d.prevRoll() > 0 && tokenIndex + d.prevRoll() < boardEnd) {
                assertEquals(false, takeTurnOutput);
                assertEquals(t.getIndex(), (tokenIndex + d.prevRoll()));
            }
            else if (tokenIndex + d.prevRoll() == boardEnd) {
                assertEquals(true, takeTurnOutput);
                assertEquals(t.getIndex(), boardEnd);
            }
            else {
                assertEquals(t.getIndex(), tokenIndex);
            }
        }
    }
}
