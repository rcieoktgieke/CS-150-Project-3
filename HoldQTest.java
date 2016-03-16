import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class HoldQTest.
 *
 * @Eric Weber
 * @3/5/16
 */
public class HoldQTest
{
    private HoldQ holdQ;
    private Player p1;
    private Player p2;
    private Player p3;
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
        p1 = new Player();
        p2 = new Player();
        p3 = new Player();
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
            holdQ.land(p1, d);
            int roll2 = d.roll();
            holdQ.land(p2, d);
            int roll3 = d.roll();
            holdQ.land(p3, d);
            holdQ.takeTurn(p2, d, boardEnd);
            assertFalse(holdQ.canMove());
            holdQ.takeTurn(p3, d, boardEnd);
            assertFalse(holdQ.canMove());
        }
        
        holdQ = new HoldQ(2);
        for (int i = 0; i < 100; i ++) {
            Player p = new Player();
            int playerIndex = p.getIndex();
            int roll = d.prevRoll();
            holdQ.land(p, d);
            holdQ.takeTurn(p, d, boardEnd);
            while (d.prevRoll() != roll) {
                assertFalse(holdQ.canMove());
                holdQ.takeTurn(p, d, boardEnd);
            }
            if (playerIndex + (d.prevRoll() * 2) >= 0 && playerIndex + (d.prevRoll() * 2) < boardEnd) {
                assertTrue(holdQ.canMove());
            }
            else if (playerIndex + (d.prevRoll() * 2) == boardEnd) {
                assertTrue(holdQ.canMove());
            }
            else {
                assertFalse(holdQ.canMove());
            }
        }
    }
    
    @Test
    public void testGetStatus()
    {
        String rolls = "";
        for (int i = 0; i < 300; i ++) {
            holdQ.land(p1, d);
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
            p1 = new Player();
            p2 = new Player();
            p3 = new Player();
            int roll1 = d.roll();
            holdQ.land(p1, d);
            int roll2 = d.roll();
            holdQ.land(p2, d);
            int roll3 = d.roll();
            holdQ.land(p3, d);
            holdQ.takeTurn(p2, d, boardEnd);
            holdQ.takeTurn(p3, d, boardEnd);
            assertEquals(p2.getIndex(), 0);
            assertEquals(p3.getIndex(), 0);
            
            holdQ.takeTurn(p1, d, boardEnd);
            while (d.prevRoll() != roll1) {
                holdQ.takeTurn(p1, d, boardEnd);
            }
            assertEquals(p1.getIndex(), roll1*2);
            
            holdQ.takeTurn(p2, d, boardEnd);
            while (d.prevRoll() != roll2) {
                holdQ.takeTurn(p2, d, boardEnd);
            }
            assertEquals(p2.getIndex(), roll2*2);
            
            holdQ.takeTurn(p3, d, boardEnd);
            while (d.prevRoll() != roll3) {
                holdQ.takeTurn(p3, d, boardEnd);
            }
            assertEquals(p3.getIndex(), roll3*2);
        }
    }
}


