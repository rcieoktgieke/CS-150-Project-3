import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class BlankSpaceTest.
 *
 * @Eric Weber
 * @4/6/16
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
        t = new Token(1, 1);
        d = new Die(15);
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
    public void testCanMove()
    {
        for (int i = 0; i < 100; i ++) {
            t = new Token(1, i);
            int tokenIndex = t.getIndex();
            int roll = d.roll();
            if (tokenIndex + roll > 0 && tokenIndex + roll <= boardEnd) {
                assertTrue(bSpace.canMove(t, roll, boardEnd));
            }
            else {
                assertFalse(bSpace.canMove(t, roll, boardEnd));
            }
        }
    }
    @Test
    public void testCanMoveEnd()
    {
        t = new Token(1, 1);
        assertTrue(bSpace.canMove(t, boardEnd, boardEnd));
    }
    @Test
    public void testCanMoveTooFar()
    {
        t = new Token(1, 1);
        assertFalse(bSpace.canMove(t, boardEnd + 1, boardEnd));
        assertFalse(bSpace.canMove(t, 0, boardEnd));
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
            t = new Token(1, i);
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
