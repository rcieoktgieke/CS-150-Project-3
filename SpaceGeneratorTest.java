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
 * @3/16/16
 */
public class SpaceGeneratorTest
{
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
            SpaceGenerator spaceGen1 = new SpaceGenerator(new Scanner(new File("config.txt")));
            while (!(spaceGen1.randomSpace(1, 1, 1) instanceof BlankSpace)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1) instanceof FairTPot)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1) instanceof RandTPot)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1) instanceof Hold)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1) instanceof PHold)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1) instanceof HoldQ)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1) instanceof JStack)) {
            }
        }
        catch (Exception e) {
        }
    }
    
    @Test
    public void testRandomSpaceInvalidFile()
    {
        try {
            SpaceGenerator spaceGen1 = new SpaceGenerator(new Scanner(new File("invalid.notatxt")));
            while (!(spaceGen1.randomSpace(1, 1, 1) instanceof BlankSpace)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1) instanceof FairTPot)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1) instanceof RandTPot)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1) instanceof Hold)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1) instanceof PHold)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1) instanceof HoldQ)) {
            }
            while (!(spaceGen1.randomSpace(1, 1, 1) instanceof JStack)) {
            }
        }
        catch (Exception e) {
        }
    }
}
