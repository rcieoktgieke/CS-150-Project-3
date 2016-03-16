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
    public void testGaussianSpace()
    {
        try {
            SpaceGenerator spaceGen1 = new SpaceGenerator(new Scanner(new File("config.txt")));
            while (!(spaceGen1.gaussianSpace() instanceof BlankSpace)) {
            }
            while (!(spaceGen1.gaussianSpace() instanceof FairTPot)) {
            }
            while (!(spaceGen1.gaussianSpace() instanceof RandTPot)) {
            }
            while (!(spaceGen1.gaussianSpace() instanceof Hold)) {
            }
            while (!(spaceGen1.gaussianSpace() instanceof PHold)) {
            }
            while (!(spaceGen1.gaussianSpace() instanceof HoldQ)) {
            }
        }
        catch (Exception e) {
        }
    }
    
    @Test
    public void testGaussianSpaceInvalidFile()
    {
        try {
            SpaceGenerator spaceGen1 = new SpaceGenerator(new Scanner(new File("invalid.notatxt")));
            while (!(spaceGen1.gaussianSpace() instanceof BlankSpace)) {
            }
            while (!(spaceGen1.gaussianSpace() instanceof FairTPot)) {
            }
            while (!(spaceGen1.gaussianSpace() instanceof RandTPot)) {
            }
            while (!(spaceGen1.gaussianSpace() instanceof Hold)) {
            }
            while (!(spaceGen1.gaussianSpace() instanceof PHold)) {
            }
            while (!(spaceGen1.gaussianSpace() instanceof HoldQ)) {
            }
        }
        catch (Exception e) {
        }
    }
}
