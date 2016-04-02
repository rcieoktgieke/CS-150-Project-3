import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.File;
/**
 * The test class SpaceGeneratorTest.
 *
 * @Eric Weber
 * @4/2/16
 */
public class SpaceGeneratorTest
{
    private Die d;
    /**
     * Default constructor for test class SpaceGeneratorTest
     */
    public SpaceGeneratorTest()
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
    public void testRandomSpace()
    {
        try {
            SpaceGenerator spaceGen1 = new SpaceGenerator(new Scanner(new File("config.txt")), null);
            while (!(spaceGen1.randomSpace(1, 1, 1, d) instanceof BlankSpace)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1, d) instanceof FairTPot)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1, d) instanceof RandTPot)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1, d) instanceof Hold)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1, d) instanceof PHold)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1, d) instanceof HoldQ)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1, d) instanceof JStack)) {
            }
        }
        catch (Exception e) {
        }
    }
    
    @Test
    public void testNoStackSpace()
    {
        try {
            SpaceGenerator spaceGen1 = new SpaceGenerator(new Scanner(new File("config.txt")), null);
            Space space = spaceGen1.noStackSpace(1, 1, 1, d);
            while (!(space instanceof BlankSpace)) {
                assertFalse(space instanceof JStack);
                space = spaceGen1.noStackSpace(1, 1, 1, d);
            }
            while (!(space instanceof FairTPot)) {
                assertFalse(space instanceof JStack);
                space = spaceGen1.noStackSpace(1, 1, 1, d);
            }
            while (!(space instanceof RandTPot)) {
                assertFalse(space instanceof JStack);
                space = spaceGen1.noStackSpace(1, 1, 1, d);
            }
            while (!(space instanceof Hold)) {
                assertFalse(space instanceof JStack);
                space = spaceGen1.noStackSpace(1, 1, 1, d);
            }
            while (!(space instanceof PHold)) {
                assertFalse(space instanceof JStack);
                space = spaceGen1.noStackSpace(1, 1, 1, d);
            }
            while (!(space instanceof HoldQ)) {
                assertFalse(space instanceof JStack);
                space = spaceGen1.noStackSpace(1, 1, 1, d);
            }
        }
        catch (Exception e) {
        }
    }
    
    @Test
    public void testRandomSpaceInvalidFile()
    {
        try {
            SpaceGenerator spaceGen1 = new SpaceGenerator(new Scanner(new File("invalid.notatxt")), null);
            while (!(spaceGen1.randomSpace(1, 1, 1, d) instanceof BlankSpace)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1, d) instanceof FairTPot)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1, d) instanceof RandTPot)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1, d) instanceof Hold)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1, d) instanceof PHold)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1, d) instanceof HoldQ)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1, d) instanceof JStack)) {
            }
        }
        catch (Exception e) {
        }
    }
}
