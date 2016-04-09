import java.util.*;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
/**
 * Main writes a bunch of config files for Project 2.
 * 
 * @Eric Weber
 * @4/9/16
 */
public class Main
{
    public static void main(String[] args) {
        for (int i = 0; i < 100; i ++) {
            File file1 = new File("Configs2/config" + i + ".txt");
            try {
                file1.createNewFile();
                FileWriter printer = new FileWriter(file1);
                printer.write("board 10 10 10\nvalues 33 " + i/10.0 + "\nHoldQ 5 2 5\nHold 10 -3 -1\ntreasurePotA 15 5 50\nPriorityHold 5 -2 2\ntreasurePotB 5 60\nJStack 10");
                printer.close();
            }
            catch (Exception e) {
                System.out.println("Creating your file failed.");
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
}