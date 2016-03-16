import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class DieTest.
 *
 * @Eric Weber
 * @3/4/16
 */
public class DieTest 
{
    private Die d;
    private int roll;
    /**
     * Default constructor for test class DieTest
     */
    public DieTest()
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
    public void testRoll()
    {
        roll = d.roll();
        assertTrue(0 < roll && roll <= 10);
        roll = d.roll();
        assertTrue(0 < roll && roll <= 10);
        roll = d.roll();
        assertTrue(0 < roll && roll <= 10);
        roll = d.roll();
        assertTrue(0 < roll && roll <= 10);
        roll = d.roll();
        assertTrue(0 < roll && roll <= 10);
        roll = d.roll();
        assertTrue(0 < roll && roll <= 10);
        roll = d.roll();
        assertTrue(0 < roll && roll <= 10);
    }

    @Test
    public void testPrevRoll()
    {
        roll = d.roll();
        assertEquals(roll, d.prevRoll());
        roll = d.roll();
        assertEquals(roll, d.prevRoll());
        roll = d.roll();
        assertEquals(roll, d.prevRoll());
        roll = d.roll();
        assertEquals(roll, d.prevRoll());
        roll = d.roll();
        assertEquals(roll, d.prevRoll());
        roll = d.roll();
        assertEquals(roll, d.prevRoll());
        roll = d.roll();
        assertEquals(roll, d.prevRoll());
    }
}



