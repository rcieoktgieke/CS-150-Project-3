import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class HoldQTest.
 *
 * @Eric Weber
 * @4/2/16
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
        holdQ = new HoldQ(2);
        t1 = new Token();
        t2 = new Token();
        t3 = new Token();
        d = new Die(10);
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
        
        holdQ = new HoldQ(2);
        for (int i = 0; i < 100; i ++) {
            Token t = new Token();
            int tokenIndex = t.getIndex();
            int roll = d.prevRoll();
            holdQ.land(t, d);
            while (d.prevRoll() != roll) {
                assertFalse(holdQ.canMove(t, d.prevRoll(), boardEnd));
                d.roll();
            }
            if (tokenIndex + (d.prevRoll() * 2) >= 0 && tokenIndex + (d.prevRoll() * 2) < boardEnd) {
                assertTrue(holdQ.canMove(t, d.prevRoll(), boardEnd));
            }
            else if (tokenIndex + (d.prevRoll() * 2) == boardEnd) {
                assertTrue(holdQ.canMove(t, d.prevRoll(), boardEnd));
            }
            else {
                assertFalse(holdQ.canMove(t, d.prevRoll(), boardEnd));
            }
            holdQ.takeTurn(t, d.prevRoll(), boardEnd);
        }
    }
    @Test
    public void testCanMoveStart()
    {
        t = new Token();
        holdQ.land(t, d);
        t.setRoll(0);
        assertTrue(holdQ.canMove(t, 0, boardEnd));
    }
    @Test
    public void testCanMoveEnd()
    {
        t = new Token();
        holdQ.land(t, d);
        t.setRoll(boardEnd / 2);
        assertTrue(holdQ.canMove(t, (boardEnd / 2), boardEnd));
    }
    @Test
    public void testCanMoveTooFar()
    {
        t = new Token();
        holdQ.land(t, d);
        t.setRoll((boardEnd / 2) + 1);        
        assertFalse(holdQ.canMove(t, ((boardEnd / 2) + 1), boardEnd));
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
            t1 = new Token();
            t2 = new Token();
            t3 = new Token();
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
            
            holdQ.takeTurn(t1, d.roll(), boardEnd);
            while (d.prevRoll() != roll1) {
                holdQ.takeTurn(t1, d.roll(), boardEnd);
            }
            assertEquals(t1.getIndex(), roll1*2);
            
            holdQ.takeTurn(t2, d.roll(), boardEnd);
            while (d.prevRoll() != roll2) {
                holdQ.takeTurn(t2, d.roll(), boardEnd);
            }
            assertEquals(t2.getIndex(), roll2*2);
            
            holdQ.takeTurn(t3, d.roll(), boardEnd);
            while (d.prevRoll() != roll3) {
                holdQ.takeTurn(t3, d.roll(), boardEnd);
            }
            assertEquals(t3.getIndex(), roll3*2);
        }
    }
}