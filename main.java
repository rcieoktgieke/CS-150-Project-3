 /**
 * Main runs a game of Chutes and Ladders and Pots
 * 
 * @Eric Weber
 * @2/25/16
 */
import java.util.*;
public class main {
    
    public static void main(String[] args) {
        System.out.println();
        Die die = new Die(10);
        LinkedList<Player> players = new LinkedList<Player>();
        Board board = new Board(10, 0.1, .2, .03, .01);
        System.out.println(board);
        for (int z = 0; z < 15; z ++) {
            Player player = new Player();
            players.add(player);
            player.advance(die.roll());
        }
        Iterator<Player> turns = players.iterator();
        while (turns.hasNext()) {
            System.out.println(board.get(turns.next().getIndex()));
        }
        
        /*Random rand = new Random();
        double p = .10;
        double variance = 1;
        int found = 0;
        for (int i = 0; i < 10000; i ++) {
            double number = rand.nextGaussian();
            System.out.println(number);
            if (number > (-1*variance) && number < variance) {
                found ++;
            }
        }
        System.out.println(found/10000.0);*/
    }
}