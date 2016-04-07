import java.util.*;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
/**
 * Main writes a bunch of config files for Project 2.
 * 
 * @Eric Weber
 * @4/7/16
 */
public class Main
{
    public static void main(String[] args) {
        for (int i = 0; i < 100; i ++) {
            File file1 = new File("Configs1/config" + i + ".csv");
            try {
                file1.createNewFile();
                FileWriter printer = new FileWriter(file1);
                printer.write("");
                printer.close();
            }
            catch (Exception e) {
                System.out.println("Creating your file failed.");
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
}