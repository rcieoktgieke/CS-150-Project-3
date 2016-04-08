import java.util.*;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
/**
 * Main writes a bunch of config files for Project 2.
 * 
 * @Eric Weber
 * @4/8/16
 */
public class Main
{
    public static void main(String[] args) {
        for (int i = 0; i < 90; i ++) {
            File file1 = new File("Configs1/config" + i + ".txt");
            try {
                file1.createNewFile();
                FileWriter printer = new FileWriter(file1);
                printer.write("board 10 10 3\nvalues 33 1.5\nHoldQ " + (90 - i) + " 2 5\nHold 5 1 3\ntreasurePotA 0 5 50\nPriorityHold " + i + " 2 4\ntreasurePotB 0 60\nJStack 5");
                printer.close();
            }
            catch (Exception e) {
                System.out.println("Creating your file failed.");
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
}