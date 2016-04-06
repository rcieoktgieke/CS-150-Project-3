import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class FairTPotTest.
 *
 * @Eric Weber
 * @4/6/16
 */
public class FairTPotTest
{
    private FairTPot fPot;
    private Token t;
    private Die d;
    private int boardEnd;
    /**
     * Default constructor for test class FairTPotTest
     */
    public FairTPotTest()
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
        fPot = new FairTPot(10, 300);
        t = new Token(1, 1);
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
    public void testCanMove()
    {
        for (int i = 0; i < 100; i ++) {
            t = new Token(1, i);
            int tokenIndex = t.getIndex();
            int roll = d.roll();
            if (tokenIndex + roll > 0 && tokenIndex + roll <= boardEnd) {
                assertTrue(fPot.canMove(t, roll, boardEnd));
            }
            else {
                assertFalse(fPot.canMove(t, roll, boardEnd));
            }
        }
    }
    @Test
    public void testCanMoveEnd()
    {
        t = new Token(1, 1);
        assertTrue(fPot.canMove(t, boardEnd, boardEnd));
    }
    @Test
    public void testCanMoveTooFar()
    {
        t = new Token(1, 1);
        assertFalse(fPot.canMove(t, boardEnd + 1, boardEnd));
        assertFalse(fPot.canMove(t, 0, boardEnd));
    }
    
    @Test
    public void testGetStatus()
    {
        for (int times = 0; times < 300; times ++) {
            assertEquals("F.Pot|" + times, fPot.getStatus());
            fPot.land(t, d);
        }
        assertEquals("F.Pot|300", fPot.getStatus());
        fPot.land(t, d);
        assertEquals("F.Pot|300", fPot.getStatus());
    }
    @Test
    public void testLand()
    {
        int pieces = 0;
        while (pieces < 3000) {
            fPot.land(t, d);
            pieces += 10;
            assertEquals(pieces, t.getPieces());
        }
        fPot.land(t, d);
        assertEquals(pieces, t.getPieces());
    }
    @Test
    public void testTakeTurn()
    {
        for (int i = 0; i < 100; i ++) {
            t = new Token(1, i);
            int tokenIndex = t.getIndex();
            boolean takeTurnOutput = fPot.takeTurn(t, d.roll(), boardEnd);
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