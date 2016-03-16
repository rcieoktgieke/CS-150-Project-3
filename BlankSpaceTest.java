import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class BlankSpaceTest.
 *
 * @Eric Weber
 * @3/4/16
 */
public class BlankSpaceTest
{
    private BlankSpace bSpace;
    private Player p;
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
        assertEquals(true, bSpace.canMove());
    }
    @Test
    public void testCanMove()
    {
        for (int i = 0; i < 100; i ++) {
            p = new Player();
            int playerIndex = p.getIndex();
            bSpace.takeTurn(p, d, boardEnd);
            if (playerIndex + d.prevRoll() > 0 && playerIndex + d.prevRoll() < boardEnd) {
                assertTrue(bSpace.canMove());
            }
            else if (playerIndex + d.prevRoll() == boardEnd) {
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
        bSpace.land(p, d);
    }

    @Test
    public void testTakeTurn()
    {
        for (int i = 0; i < 100; i ++) {
            p = new Player();
            int playerIndex = p.getIndex();
            boolean takeTurnOutput = bSpace.takeTurn(p, d, boardEnd);
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
