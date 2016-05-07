import java.util.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class JustInFrontPlayerTest.
 *
 * @Eric Weber
 * @5/7/16
 */
public class JustInFrontPlayerTest
{
    private Scanner scan;
    private Die die;
    private Board board;
    private JustInFrontPlayer inFront1;
    private LinkedList<Player> players;
    /**
     * Default constructor for test class JustInFrontPlayerTest
     */
    public JustInFrontPlayerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() throws FileNotFoundException
    {
        scan = new Scanner(new File("playerTestConfig.txt"));
        die = new Die(5);
        ArrayList<Scanner> scanners = new ArrayList<Scanner>();
        board = new Board(scan, scanners, die);
        inFront1 = new JustInFrontPlayer(1, 5);
        players = new LinkedList<Player>();
        players.add(new JustInFrontPlayer(1, 5));
        players.add(new JustInFrontPlayer(2, 5));
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
    public void testGetPieces()
    {
        Random rand = new Random();
        int pieces = 0;
        JustInFrontPlayer p = new JustInFrontPlayer(1, 20);
        for (int i = 0; i < 500; i ++) {
            int piecesToAdd = rand.nextInt(20);
            p.whichToken(rand.nextInt(15), board, 10, 5, players, 100, 1.0).addPieces(piecesToAdd);
            pieces += piecesToAdd;
            assertEquals(pieces, p.getPieces());
        }
    }
    
    @Test
    public void testGetPoints()
    {
        for (int j = 0; j < 100; j ++) {
            Random rand = new Random();
            JustInFrontPlayer p = new JustInFrontPlayer(1, 20);
            p.whichToken(rand.nextInt(4), board, 10, 5, players, 100, 1.0).advance(495);
            for (int i = 0; i < 100; i ++) {
                p.whichToken(rand.nextInt(4), board, 10, 5, players, 100, 1.0).addPieces(rand.nextInt(20));
            }
            assertTrue(p.getPoints(495, 100, 1.0) == (100.00 + p.getPieces()*1.0));
        }
    }
}
