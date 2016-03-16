import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class RandTPotTest.
 *
 * @Eric Weber
 * @3/4/16
 */
public class RandTPotTest
{
    private RandTPot rPot;
    private Player p;
    private Die d;
    private int boardEnd;
    /**
     * Default constructor for test class RandTPotTest
     */
    public RandTPotTest()
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
        rPot = new RandTPot(30);
        p = new Player();
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
        assertEquals(true, rPot.canMove());
    }
    @Test
    public void testCanMove()
    {
        for (int i = 0; i < 100; i ++) {
            p = new Player();
            int playerIndex = p.getIndex();
            rPot.takeTurn(p, d, boardEnd);
            if (playerIndex + d.prevRoll() > 0 && playerIndex + d.prevRoll() < boardEnd) {
                assertTrue(rPot.canMove());
            }
            else if (playerIndex + d.prevRoll() == boardEnd) {
                assertTrue(rPot.canMove());
            }
            else {
                assertFalse(rPot.canMove());
            }
        }
    }
    
    @Test
    public void testGetStatus()
    {
        int pieces = 30;
        assertEquals("R.Pot|" + pieces, rPot.getStatus());
        while (pieces > 0) {
            rPot.land(p, d);
            pieces -= d.prevRoll();
            if (pieces > 0) {
                assertEquals("R.Pot|" + pieces, rPot.getStatus());
            }
            else {
                assertEquals("R.Pot|0", rPot.getStatus());
            }
        }
    }
    
    @Test
    public void testLand()
    {
        rPot = new RandTPot(3000);
        int pieces = 0;
        while (pieces < 3000) {
            rPot.land(p, d);
            pieces += d.prevRoll();
            assertEquals(pieces, p.getPieces());
        }
        rPot.land(p, d);
        assertEquals(pieces, p.getPieces());
    }
    @Test
    public void testTakeTurn()
    {
        for (int i = 0; i < 100; i ++) {
            p = new Player();
            int playerIndex = p.getIndex();
            boolean takeTurnOutput = rPot.takeTurn(p, d, boardEnd);
            if (playerIndex + d.prevRoll() > 0 && playerIndex + d.prevRoll() < boardEnd) {
                assertEquals(false, takeTurnOutput);
                assertEquals(p.getIndex(), (playerIndex + d.prevRoll()));
            }
            else if (playerIndex + d.prevRoll() == boardEnd) {
                assertEquals(true, takeTurnOutput);
                assertEquals(p.getIndex(), boardEnd);
            }
            else {
                assertEquals(p.getIndex(), playerIndex);
            }
        }
    }
}
