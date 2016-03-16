import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class FairTPotTest.
 *
 * @Eric Weber
 * @3/4/16
 */
public class FairTPotTest
{
    private FairTPot fPot;
    private Player p;
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
        assertEquals(true, fPot.canMove());
    }
    @Test
    public void testCanMove()
    {
        for (int i = 0; i < 100; i ++) {
            p = new Player();
            int playerIndex = p.getIndex();
            fPot.takeTurn(p, d, boardEnd);
            if (playerIndex + d.prevRoll() > 0 && playerIndex + d.prevRoll() < boardEnd) {
                assertTrue(fPot.canMove());
            }
            else if (playerIndex + d.prevRoll() == boardEnd) {
                assertTrue(fPot.canMove());
            }
            else {
                assertFalse(fPot.canMove());
            }
        }
    }
    
    @Test
    public void testGetStatus()
    {
        for (int times = 0; times < 300; times ++) {
            assertEquals("F.Pot|" + times, fPot.getStatus());
            fPot.land(p, d);
        }
        assertEquals("F.Pot|300", fPot.getStatus());
        fPot.land(p, d);
        assertEquals("F.Pot|300", fPot.getStatus());
    }
    @Test
    public void testLand()
    {
        int pieces = 0;
        while (pieces < 3000) {
            fPot.land(p, d);
            pieces += 10;
            assertEquals(pieces, p.getPieces());
        }
        fPot.land(p, d);
        assertEquals(pieces, p.getPieces());
    }
    @Test
    public void testTakeTurn()
    {
        for (int i = 0; i < 100; i ++) {
            p = new Player();
            int playerIndex = p.getIndex();
            boolean takeTurnOutput = fPot.takeTurn(p, d, boardEnd);
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