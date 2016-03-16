import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class HoldTest.
 *
 * @Eric Weber
 * @3/16/16
 */
public class HoldTest
{
    private Hold hold;
    private Token t;
    private Die d;
    private int boardEnd;
    /**
     * Default constructor for test class HoldTest
     */
    public HoldTest()
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
        hold = new Hold(3);
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
    public void testCanMove()
    {
        for (int i = 0; i < 100; i ++) {
            t = new Token();
            int tokenIndex = t.getIndex();
            int roll = d.prevRoll();
            hold.land(t, d);
            while (d.prevRoll() != roll) {
                assertFalse(hold.canMove());
                hold.takeTurn(t, d, boardEnd);
            }
            if (tokenIndex + (d.prevRoll() * 3) > 0 && tokenIndex + (d.prevRoll() * 3) < boardEnd) {
                assertTrue(hold.canMove());
            }
            else if (tokenIndex + (d.prevRoll() * 3) == boardEnd) {
                assertTrue(hold.canMove());
            }
            else {
                assertFalse(hold.canMove());
            }
        }
    }
    
    @Test
    public void testGetStatus()
    {
        String rolls = "";
        for (int i = 0; i < 300; i ++) {
            hold.land(t, d);
            rolls += "" + d.prevRoll();
            assertEquals("Hold:3|" + rolls, hold.getStatus());
            rolls += ", ";
        }
    }
    @Test
    public void testTakeTurn()
    {
        for (int i = 0; i < 100; i ++) {
            t = new Token();
            int tokenIndex = t.getIndex();
            int roll = d.prevRoll();
            boolean takeTurnOutput = false;
            hold.land(t, d);
            while (d.prevRoll() != roll) {
                assertFalse(hold.canMove());
                takeTurnOutput = hold.takeTurn(t, d, boardEnd);
            }
            if (tokenIndex + (d.prevRoll() * 3) > 0 && tokenIndex + (d.prevRoll() * 3) < boardEnd) {
                assertEquals(false, takeTurnOutput);
                assertEquals(t.getIndex(), (tokenIndex + d.prevRoll()));
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