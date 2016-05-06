import java.util.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MazeEntranceTest.
 *
 * @Eric Weber
 * @5/4/16
 */
public class MazeEntranceTest
{
    /**
     * Default constructor for test class MazeEntranceTest
     */
    public MazeEntranceTest()
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
    public void testMazeEntrance()
    {
        /*try {
            Scanner graphScan = new Scanner(new File("new-maze-25-50.txt"));
            
            HashMap<Integer, MazeNode> nodes = new HashMap<Integer, MazeNode>();
            ArrayList<MazeNode> entrances = new ArrayList<MazeNode>();
            ArrayList<MazeNode> exits = new ArrayList<MazeNode>();
            while (graphScan.hasNextLine()) {
                Scanner lineScanner = new Scanner(graphScan.nextLine());
                Integer key1 = lineScanner.nextInt();
                lineScanner.next();
                Integer key2 = lineScanner.nextInt();
                Integer weight = lineScanner.nextInt();
                
                exits.remove(nodes.get(key1));
                entrances.remove(nodes.get(key2));
                if (!nodes.containsKey(key1)) {
                    nodes.put(key1, new MazeNode(key1));
                    entrances.add(nodes.get(key1));
                }
                if (nodes.containsKey(key2)) {
                    nodes.get(key1).addEdge(nodes.get(key2), weight);
                }
                else {
                    nodes.put(key2, new MazeNode(key2));
                    nodes.get(key1).addEdge(nodes.get(key2), weight);
                    exits.add(nodes.get(key2));
                }
            }
            
            MazeEntrance entranceSpace = new MazeEntrance(null, entrances.get(0));
            ArrayList<MazeExit> exitSpaces = new ArrayList<MazeExit>();
            int index = 0;
            for (MazeNode exit : exits) {
                //exitSpaces.add(new MazeExit(null, 
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }*/
    }
}