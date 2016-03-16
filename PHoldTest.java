import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class PHoldTest.
 *
 * @Eric Weber
 * @3/5/16
 */
public class PHoldTest
{
    private PHold pHold;
    private Player p1;
    private Player p2;
    private Player p3;
    private Die d;
    private int boardEnd;
    /**
     * Default constructor for test class PHoldTest
     */
    public PHoldTest()
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
        pHold = new PHold(3);
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
    public void testCompare()
    {
        p1.setRoll(10);
        p2.setRoll(5);
        assertEquals(1, pHold.compare(p1, p2));
        p1.setRoll(10);
        p2.setRoll(10);
        assertEquals(1, pHold.compare(p1, p2));
        p1.setRoll(5);
        p2.setRoll(10);
        assertEquals(-1, pHold.compare(p1, p2));
    }

    @Test
    public void testTakeTurn()
    {
        for (int i = 0; i < 100; i ++) {
            Player p = new Player();
            int playerIndex = p.getIndex();
            pHold.land(p, d);
            boolean takeTurnOutput = pHold.takeTurn(p, d, boardEnd);
            if (playerIndex + (d.prevRoll() * 3) > 0 && playerIndex + (d.prevRoll() * 3) < boardEnd) {
                assertEquals(false, takeTurnOutput);
                assertEquals(p.getIndex(), (playerIndex + (d.prevRoll() * 3)));
            }
            else if (playerIndex + (d.prevRoll() * 3) == boardEnd) {
                assertEquals(true, takeTurnOutput);
                assertEquals(p.getIndex(), boardEnd);
            }
            else {
                assertEquals(p.getIndex(), playerIndex);
            }
        }
    }
}
