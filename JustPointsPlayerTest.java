import java.util.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class JustPointsPlayerTest.
 *
 * @Eric Weber
 * @4/6/16
 */
public class JustPointsPlayerTest
{
    private Scanner scan;
    private Die die;
    private Board board;
    private JustPointsPlayer justPoints1;
    private LinkedList<Player> players;
    /**
     * Default constructor for test class JustPointsPlayerTest
     */
    public JustPointsPlayerTest()
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
        board = new Board(scan, die);
        justPoints1 = new JustPointsPlayer(1, 5);
        players = new LinkedList<Player>();
        players.add(new JustPointsPlayer(1, 5));
        players.add(new JustPointsPlayer(2, 5));
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
        JustPointsPlayer p = new JustPointsPlayer(1, 20);
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
            JustPointsPlayer p = new JustPointsPlayer(1, 20);
            p.whichToken(rand.nextInt(4), board, 10, 5, players, 100, 1.0).advance(495);
            for (int i = 0; i < 100; i ++) {
                p.whichToken(rand.nextInt(4), board, 10, 5, players, 100, 1.0).addPieces(rand.nextInt(20));
            }
            assertTrue(p.getPoints(495, 100, 1.0) == (100.00 + p.getPieces()*1.0));
        }
    }
}