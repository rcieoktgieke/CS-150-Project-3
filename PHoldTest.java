import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class PHoldTest.
 *
 * @Eric Weber
 * @4/2/16
 */
public class PHoldTest
{
    private PHold pHold;
    private Token t1;
    private Token t2;
    private Token t3;
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
        t1 = new Token();
        t2 = new Token();
        t3 = new Token();
        d = new Die(10);
        boardEnd = 30;
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
        t1.setRoll(10);
        t2.setRoll(5);
        assertEquals(1, pHold.compare(t1.getRoll(), t2.getRoll()));
        t1.setRoll(10);
        t2.setRoll(10);
        assertEquals(0, pHold.compare(t1.getRoll(), t2.getRoll()));
        t1.setRoll(5);
        t2.setRoll(10);
        assertEquals(-1, pHold.compare(t1.getRoll(), t2.getRoll()));
    }

    @Test
    public void testTakeTurn()
    {
        for (int i = 0; i < 500; i ++) {
            Token t = new Token();
            int tokenIndex = t.getIndex();
            pHold.land(t, d);
            boolean takeTurnOutput = pHold.takeTurn(t, d.roll(), boardEnd);
            if (tokenIndex + (d.prevRoll() * 3) > 0 && tokenIndex + (d.prevRoll() * 3) < boardEnd) {
                assertEquals(false, takeTurnOutput);
                assertEquals(t.getIndex(), (tokenIndex + (d.prevRoll() * 3)));
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
