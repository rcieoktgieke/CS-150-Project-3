import java.util.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class FinishFirstPlayerTest.
 *
 * @Eric Weber
 * @5/7/16
 */
public class FinishFirstPlayerTest
{
    private Scanner scan;
    private Die die;
    private Board board;
    private FinishFirstPlayer finishFi1;
    private LinkedList<Player> players;
    /**
     * Default constructor for test class FinishFirstPlayerTest
     */
    public FinishFirstPlayerTest()
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
        finishFi1 = new FinishFirstPlayer(1, 5);
        players = new LinkedList<Player>();
        players.add(new FinishFirstPlayer(1, 5));
        players.add(new FinishFirstPlayer(2, 5));
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
        FinishFirstPlayer p = new FinishFirstPlayer(1, 20);
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
            FinishFirstPlayer p = new FinishFirstPlayer(1, 20);
            p.whichToken(rand.nextInt(4), board, 10, 5, players, 100, 1.0).advance(495);
            for (int i = 0; i < 100; i ++) {
                p.whichToken(rand.nextInt(4), board, 10, 5, players, 100, 1.0).addPieces(rand.nextInt(20));
            }
            assertTrue(p.getPoints(495, 100, 1.0) == (100.00 + p.getPieces()*1.0));
        }
    }
    
    @Test
    public void testWhichTokenLosing()
    {
        players.get(0).whichToken(4, board, 10, 5, players, 100, 1.0).addPieces(100);
        
        Token t1 = finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0);
        t1.advance(4);
        assertTrue(t1 == finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0));
        t1.advance(4);
        assertFalse(t1 == finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0));
        
        Token t2 = finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0);
        t2.advance(4);
        assertTrue(t2 == finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0));
        t2.advance(4);
        assertFalse(t2 == finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0) && t1 == finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0));
        
        Token t3 = finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0);
        t3.advance(4);
        assertTrue(t3 == finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0));
        t3.advance(4);
        assertFalse(t3 == finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0) && t2 == finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0) && t1 == finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0));
    }
    
    @Test
    public void testWhichTokenWinning()
    {
        finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0).addPieces(100);
        
        Token t1 = finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0);
        t1.advance(4);
        assertTrue(t1 == finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0));
        t1.advance(4);
        assertTrue(t1 == finishFi1.whichToken(2, board, 10, 5, players, 100, 1.0));
        
        Token t3 = finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0);
        t3.advance(4);
        assertTrue(t3 == finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0));
        t3.advance(4);
        assertFalse(t3 == finishFi1.whichToken(4, board, 10, 5, players, 100, 1.0));
        assertTrue(t1 == finishFi1.whichToken(2, board, 10, 5, players, 100, 1.0));
    }
}