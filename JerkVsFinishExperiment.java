import java.util.*;
import java.io.*;
/**
 * JerkVsFinishExperiment tests how often TotalJerkPlayers win against FinishFirstPlayers as the board has increasing numbers of PHolds and HoldQs.
 * 
 * @Eric Weber
 * @4/9/16
 */
public class JerkVsFinishExperiment {
    
    /**
     * Run and analyze games of Chutes and Ladders and Pots.
     */
    public static void main(String[] args) {
        for (int i = 0; i < 90; i ++) {
            final int j = i;
            new Thread() {
                public void run() {
                    File file = new File("Experiment1/output" + j + ".csv");
                    try {
                        file.createNewFile();
                        FileWriter printer = new FileWriter(file);
                        printer.write(j + ", " + runExperiment(j));
                        printer.close();
                    }
                    catch (Exception e) {
                        System.out.println("Creating your file failed.");
                        System.out.println(e.getLocalizedMessage());
                    }
                }
            }.start();
        }
    }
    
    /**
     * Run a game and analyze results.
     * 
     * @param eNumber this experiment's number.
     * @return percent of games run by TotalJerkPlayers.
     */
    public static double runExperiment(int eNumber) {
        PrintStream dummyStream = new PrintStream(new OutputStream() {
            public void write(int b) {
                //NO-OP
            }
        });
        System.setOut(dummyStream);
        
        double percentWonByJerks = 0.0;
        for (int i = 0; i < 1000; i ++) {
            /** Initialize all components of the game.*/
            Die die = new Die(10);
            LinkedList<Player> players = new LinkedList<Player>();
            File configFile = new File("Configs1/config" + eNumber + ".txt");
            try {
                Scanner configScan = new Scanner(configFile);
                Board board = new Board(configScan, die);
                int x = board.getX();
                int y = board.getY();
                int z = board.getZ();
                int winningPoints = board.winningPoints();
                double piecesPoints = board.piecesPoints();
                int numberOfSpaces = board.numberOfSpaces();
                for (int p = 0; p < 20; p += 2) {
                    players.add(new FinishFirstPlayer(p, 10));
                    players.add(new TotalJerkPlayer(p + 1, 10));
                }
                /**Initialize user interface.*/
                boolean repeat = true;
                
                /**Take turns until the game ends*/
                while (!(takeTurn(players, board, die, 10, numberOfSpaces - 1, winningPoints, piecesPoints))) {
                }
                /**Determine winning type*/
                Iterator<Player> playerIter = players.iterator();
                Player wonPlayer = new FinishFirstPlayer(1, 1);
                double wonPoints = 0;
                while (playerIter.hasNext()) {
                    Player cPlayer = playerIter.next();
                    if (cPlayer.getPoints(numberOfSpaces - 1, winningPoints, piecesPoints) > wonPoints) {
                        wonPlayer = cPlayer;
                        wonPoints = cPlayer.getPoints(numberOfSpaces - 1, winningPoints, piecesPoints);
                    }
                }
                if (wonPlayer instanceof TotalJerkPlayer) {
                    percentWonByJerks += 1;
                }
            }
            catch (Exception e) {
                System.out.println("Config file scan has failed.");
                System.out.println(e.getLocalizedMessage());
            }
        }
        return percentWonByJerks / 10.0;
    }
    
    /**
     * Take a turn.
     * 
     * Iterate through the list of players in turn order. Take the turn of each token each player chooses on its current space, and, if allowed, land them on their next space.
     * @param players list of players in the game in turn order.
     * @param board the board used for the game.
     * @param d die that is used for the game.
     * @param dRange upper number for the range of the die.
     * @param boardEnd the index of the final space on the board.
     * @param w the points for winning in this game.
     * @param p the points for pieces in this game.
     * @return if the game has ended.
     */
    public static boolean takeTurn(LinkedList<Player> players, Board board, Die die, int dRange, int boardEnd, int w, double p) {
        Token cToken;
        Iterator<Player> turns = players.iterator();
        while (turns.hasNext()) {
            int roll = die.roll();
            cToken = turns.next().whichToken(roll, board, boardEnd, dRange, players, w, p);
            int tokenStartIndex = cToken.getIndex();
            boolean canMove = board.get(cToken.getIndex()).canMove(cToken, roll, boardEnd);
            if (board.get(cToken.getIndex()).takeTurn(cToken, roll, boardEnd)) {
                /**If a token reaches the final space, print that the game is over and token info.*/
                System.out.println("GAME OVER");
                System.out.println();
                printPlayers(players, boardEnd, w, p);
                return true;
            }
            else {
                if (canMove && board.get(tokenStartIndex).advanced()) {
                    /**If the token can move, call land on its new space.*/
                    board.get(cToken.getIndex()).land(cToken, die);
                }
            }
            System.out.println();
        }
        System.out.println();
        return false;
    }
    /**
     * Print a map of the board.
     * 
     * @param board the board used for the game.
     */
    public static void printMap(Board board) {
        board.printBoard();
    }
    /**
     * Print each player, their tokens, and their pieces.
     * 
     * @param players list of players in turn order.
     */
    public static void printPlayers(LinkedList<Player> players, int boardEnd, int w, double p) {
        Player cPlayer;
        Iterator<Player> playerIter = players.iterator();
        while (playerIter.hasNext()) {
            cPlayer = playerIter.next();
            System.out.println(cPlayer + ": " + cPlayer.getPoints(boardEnd, w, p));
            cPlayer.printTokens();
        }
    }
}