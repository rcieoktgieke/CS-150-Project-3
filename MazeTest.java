import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
/**
 * The test class MazeTest.
 *
 * @Eric Weber
 * @4/28/16
 */
public class MazeTest
{
    /**
     * Default constructor for test class MazeTest
     */
    public MazeTest()
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
    public void testMaze()
    {
        HashMap<Integer, MazeNode> nodes = new HashMap<Integer, MazeNode>();
        ArrayList<MazeNode> entrances = new ArrayList<MazeNode>();
        ArrayList<MazeNode> exits = new ArrayList<MazeNode>();
        int j = 100;
        for (int i = 0; i < 100; i ++) {
            int key1 = i;
            j --;
            int key2 = (j % 50) - 1;
            exits.remove(nodes.get(key1));
            entrances.remove(nodes.get(key2));
            if (!nodes.containsKey(key1)) {
                nodes.put(key1, new MazeNode(key1));
                entrances.add(nodes.get(key1));
            }
            if (nodes.containsKey(key2)) {
                nodes.get(key1).addEdge(nodes.get(key2), (key2 - key1 + 100) % 10);
            }
            else {
                nodes.put(key2, new MazeNode(key2));
                nodes.get(key1).addEdge(nodes.get(key2), (key2 - key1 + 100) % 10);
                exits.add(nodes.get(key2));
            }
        }
        
        Iterator<Integer> keyIter = nodes.keySet().iterator();
        String output = "";
        while (keyIter.hasNext()) {
            MazeNode cNode = nodes.get(keyIter.next());
            output += cNode.toString();
        }
        System.out.println(output);
        assertEquals(output, "0 -8- 48\n1 -6- 47\n2 -4- 46\n3 -2- 45\n4 -0- 44\n5 -8- 43\n6 -6- 42\n7 -4- 41\n8 -2- 40\n9 -0- 39\n10 -8- 38\n11 -6- 37\n12 -4- 36\n13 -2- 35\n14 -0- 34\n15 -8- 33\n16 -6- 32\n17 -4- 31\n18 -2- 30\n19 -0- 29\n20 -8- 28\n21 -6- 27\n22 -4- 26\n23 -2- 25\n24 -0- 24\n25 -8- 23\n26 -6- 22\n27 -4- 21\n28 -2- 20\n29 -0- 19\n30 -8- 18\n31 -6- 17\n32 -4- 16\n33 -2- 15\n34 -0- 14\n35 -8- 13\n36 -6- 12\n37 -4- 11\n38 -2- 10\n39 -0- 9\n40 -8- 8\n41 -6- 7\n42 -4- 6\n43 -2- 5\n44 -0- 4\n45 -8- 3\n46 -6- 2\n47 -4- 1\n48 -2- 0\n49 -0- -1\n50 -8- 48\n51 -6- 47\n52 -4- 46\n53 -2- 45\n54 -0- 44\n55 -8- 43\n56 -6- 42\n57 -4- 41\n58 -2- 40\n59 -0- 39\n60 -8- 38\n61 -6- 37\n62 -4- 36\n63 -2- 35\n64 -0- 34\n65 -8- 33\n66 -6- 32\n67 -4- 31\n68 -2- 30\n69 -0- 29\n70 -8- 28\n71 -6- 27\n72 -4- 26\n73 -2- 25\n74 -0- 24\n75 -8- 23\n76 -6- 22\n77 -4- 21\n78 -2- 20\n79 -0- 19\n80 -8- 18\n81 -6- 17\n82 -4- 16\n83 -2- 15\n84 -0- 14\n85 -8- 13\n86 -6- 12\n87 -4- 11\n88 -2- 10\n89 -0- 9\n90 -8- 8\n91 -6- 7\n92 -4- 6\n93 -2- 5\n94 -0- 4\n95 -8- 3\n96 -6- 2\n97 -4- 1\n98 -2- 0\n99 -0- -1\n");
        
        Iterator<MazeNode> entrancesIter = entrances.iterator();
        output = "=======Entrances========\n";
        while (entrancesIter.hasNext()) {
            MazeNode cNode = entrancesIter.next();
            output += cNode.getKey() + "\n";
        }
        System.out.println(output);
        assertEquals(output, "=======Entrances========\n49\n50\n51\n52\n53\n54\n55\n56\n57\n58\n59\n60\n61\n62\n63\n64\n65\n66\n67\n68\n69\n70\n71\n72\n73\n74\n75\n76\n77\n78\n79\n80\n81\n82\n83\n84\n85\n86\n87\n88\n89\n90\n91\n92\n93\n94\n95\n96\n97\n98\n99\n");
        
        Iterator<MazeNode> exitsIter = exits.iterator();
        output = "=======Exits========\n";
        while (exitsIter.hasNext()) {
            MazeNode cNode = exitsIter.next();
            output += cNode.getKey() + "\n";
        }
        System.out.println(output);
        assertEquals(output, "=======Exits========\n-1\n");
    }
}