import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class HoldQTest.
 *
 * @Eric Weber
 * @4/6/16
 */
public class HoldQTest
{
    private HoldQ holdQ;
    private Token t;
    private Token t1;
    private Token t2;
    private Token t3;
    private Die d;
    private int boardEnd;
    /**
     * Default constructor for test class HoldQTest
     */
    public HoldQTest()
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
        holdQ = new HoldQ(2, d);
        t1 = new Token(1, 1);
        t2 = new Token(1, 2);
        t3 = new Token(1, 3);
        boardEnd = 10;
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
            int roll1 = d.roll();
            holdQ.land(t1, d);
            int roll2 = d.roll();
            holdQ.land(t2, d);
            int roll3 = d.roll();
            holdQ.land(t3, d);
            assertFalse(holdQ.canMove(t2, d.roll(), boardEnd));
            assertFalse(holdQ.canMove(t3, d.roll(), boardEnd));
        }
        
        holdQ = new HoldQ(2, d);
        for (int i = 0; i < 100; i ++) {
            holdQ = new HoldQ(2, d);
            Token t = new Token(1, i);
            int tokenIndex = t.getIndex();
            int roll = d.prevRoll();
            holdQ.land(t, d);
            while (d.prevRoll() != roll) {
                assertFalse(holdQ.canMove(t, d.prevRoll(), boardEnd));
                d.roll();
            }
            assertTrue(holdQ.canMove(t, d.prevRoll(), boardEnd));
        }
    }
    @Test
    public void testAdvanced() {
        boolean reachedEnd = false;
        boolean passedBounds = false;
        for (int i = 0; (i < 100) || !reachedEnd || !passedBounds; i ++) {
            holdQ = new HoldQ(2, d);
            t = new Token(1, i);
            int tokenIndex = t.getIndex();
            int roll = d.prevRoll();
            holdQ.land(t, d);
            holdQ.takeTurn(t, d.prevRoll(), boardEnd);
            if (tokenIndex + (d.prevRoll() * 2) >= 0 && tokenIndex + (d.prevRoll() * 2) < boardEnd) {
                assertTrue(holdQ.advanced());
            }
            else if (tokenIndex + (d.prevRoll() * 2) == boardEnd) {
                assertTrue(holdQ.advanced());
                reachedEnd = true;
            }
            else {
                assertFalse(holdQ.advanced());
                passedBounds = true;
            }
        }
    }
    
    @Test
    public void testGetStatus()
    {
        String rolls = "";
        for (int i = 0; i < 300; i ++) {
            holdQ.land(t1, d);
            rolls += "" + d.prevRoll();
            assertEquals("HoldQ:2|" + rolls, holdQ.getStatus());
            rolls += ", ";
        }
    }
    
    @Test
    public void testTakeTurn()
    {
        boardEnd = 20;
        for (int i = 0; i < 300; i ++) {
            t1 = new Token(i, 1);
            t2 = new Token(i, 2);
            t3 = new Token(i, 3);
            int roll1 = d.roll();
            holdQ.land(t1, d);
            int roll2 = d.roll();
            holdQ.land(t2, d);
            int roll3 = d.roll();
            holdQ.land(t3, d);
            holdQ.takeTurn(t2, d.roll(), boardEnd);
            holdQ.takeTurn(t3, d.roll(), boardEnd);
            assertEquals(t2.getIndex(), 0);
            assertEquals(t3.getIndex(), 0);
            
            holdQ.takeTurn(t1, roll1, boardEnd);
            if (holdQ.advanced()) {
                assertEquals(t1.getIndex(), d.prevRoll()*2);
            }
            
            holdQ.takeTurn(t2, roll2, boardEnd);
            if (holdQ.advanced()) {
                assertEquals(t2.getIndex(), d.prevRoll()*2);
            }
            
            holdQ.takeTurn(t3, roll3, boardEnd);
            if (holdQ.advanced()) {
                assertEquals(t3.getIndex(), d.prevRoll()*2);
            }
        }
    }
}