import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class FairTPotTest.
 *
 * @Eric Weber
 * @2/21/16
 */
public class FairTPotTest {
    
    public FairTPotTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCanGive() {
        FairTPot fairTPot1 = new FairTPot(15, 5);
        assertTrue(fairTPot1.canGive());
        assertEquals(15, fairTPot1.givePieces());
        assertTrue(fairTPot1.canGive());
        assertEquals(15, fairTPot1.givePieces());
        assertTrue(fairTPot1.canGive());
        assertEquals(15, fairTPot1.givePieces());
        assertTrue(fairTPot1.canGive());
        assertEquals(15, fairTPot1.givePieces());
        assertTrue(fairTPot1.canGive());
        assertEquals(15, fairTPot1.givePieces());
        assertFalse(fairTPot1.canGive());
    }
}

