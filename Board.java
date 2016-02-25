/**
 * The board stores spaces and provides access to them to main.
 * 
 * @Eric Weber
 * @2/25/16
 */
import java.util.*;
import java.lang.Math.*;
public class Board {
    
    private ArrayList<Space> board;
    private int dimension;
    private int numberOfSpaces;
    public Board(int dimension, double fairPotFreq, double randomPotFreq, double holdFreq, double pHoldFreq) {
        this.dimension = dimension;
        numberOfSpaces = (int) Math.pow(dimension, 2);
        board = new ArrayList<Space>(numberOfSpaces);
        Random rand = new Random();
        for (int i = 0; i < (numberOfSpaces); i ++) {
            double spaceRand = rand.nextDouble();
            if (spaceRand <= fairPotFreq) {
                board.add(new FairTPot(15, 5));
            }
            else if (spaceRand <= (fairPotFreq + randomPotFreq)) {
                board.add(new Space());
            }
            else {
                board.add(new Space());
            }
        }
    }
    /*public void addSpecialSpaces(double fairPotFreq, double randomPotFreq, double holdFreq, double pHoldFreq) {
        //Gaussian distribution
        //for each space add a space of type defined by Gaussian number. What numbers correspond to which types is determined by freqencies
        board.add(new FairTPot(15, 5));
        
    }*/
    public Space get(int index) {
        return board.get(index);
    }
    public String toString() {
        String returnStr = "";
        for (int i = 0; i < dimension; i ++) {
            for (int j = 0; j < dimension; j ++) {
                returnStr += board.get(i*10 + j) + "\t";
            }
            returnStr += "\n";
        }
        return returnStr;
    }
}