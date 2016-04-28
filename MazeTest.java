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
                nodes.get(key1).addEdge(nodes.get(key2), key2 - key1 + 100);
            }
            else {
                nodes.put(key2, new MazeNode(key2));
                nodes.get(key1).addEdge(nodes.get(key2), key2 - key1 + 100);
                exits.add(nodes.get(key2));
            }
        }
        
        Iterator<Integer> keyIter = nodes.keySet().iterator();
        String output = "";
        while (keyIter.hasNext()) {
            MazeNode cNode = nodes.get(keyIter.next());
            output += cNode.toString();
        }
        assertEquals(output, "0 -148- 48\n1 -146- 47\n2 -144- 46\n3 -142- 45\n4 -140- 44\n5 -138- 43\n6 -136- 42\n7 -134- 41\n8 -132- 40\n9 -130- 39\n10 -128- 38\n11 -126- 37\n12 -124- 36\n13 -122- 35\n14 -120- 34\n15 -118- 33\n16 -116- 32\n17 -114- 31\n18 -112- 30\n19 -110- 29\n20 -108- 28\n21 -106- 27\n22 -104- 26\n23 -102- 25\n24 -100- 24\n25 -98- 23\n26 -96- 22\n27 -94- 21\n28 -92- 20\n29 -90- 19\n30 -88- 18\n31 -86- 17\n32 -84- 16\n33 -82- 15\n34 -80- 14\n35 -78- 13\n36 -76- 12\n37 -74- 11\n38 -72- 10\n39 -70- 9\n40 -68- 8\n41 -66- 7\n42 -64- 6\n43 -62- 5\n44 -60- 4\n45 -58- 3\n46 -56- 2\n47 -54- 1\n48 -52- 0\n49 -50- -1\n50 -98- 48\n51 -96- 47\n52 -94- 46\n53 -92- 45\n54 -90- 44\n55 -88- 43\n56 -86- 42\n57 -84- 41\n58 -82- 40\n59 -80- 39\n60 -78- 38\n61 -76- 37\n62 -74- 36\n63 -72- 35\n64 -70- 34\n65 -68- 33\n66 -66- 32\n67 -64- 31\n68 -62- 30\n69 -60- 29\n70 -58- 28\n71 -56- 27\n72 -54- 26\n73 -52- 25\n74 -50- 24\n75 -48- 23\n76 -46- 22\n77 -44- 21\n78 -42- 20\n79 -40- 19\n80 -38- 18\n81 -36- 17\n82 -34- 16\n83 -32- 15\n84 -30- 14\n85 -28- 13\n86 -26- 12\n87 -24- 11\n88 -22- 10\n89 -20- 9\n90 -18- 8\n91 -16- 7\n92 -14- 6\n93 -12- 5\n94 -10- 4\n95 -8- 3\n96 -6- 2\n97 -4- 1\n98 -2- 0\n99 -0- -1\n");
        
        Iterator<MazeNode> entrancesIter = entrances.iterator();
        output = "=======Entrances========\n";
        while (entrancesIter.hasNext()) {
            MazeNode cNode = entrancesIter.next();
            output += cNode.getKey() + "\n";
        }
        assertEquals(output, "=======Entrances========\n49\n50\n51\n52\n53\n54\n55\n56\n57\n58\n59\n60\n61\n62\n63\n64\n65\n66\n67\n68\n69\n70\n71\n72\n73\n74\n75\n76\n77\n78\n79\n80\n81\n82\n83\n84\n85\n86\n87\n88\n89\n90\n91\n92\n93\n94\n95\n96\n97\n98\n99\n");
        
        Iterator<MazeNode> exitsIter = exits.iterator();
        output = "=======Exits========\n";
        while (exitsIter.hasNext()) {
            MazeNode cNode = exitsIter.next();
            output += cNode.getKey() + "\n";
        }
        assertEquals(output, "=======Exits========\n-1\n");
    }
}