import java.util.*;
import java.io.*;
/**
 * EndVsWinExperiment tests how often each type of player that wins the game also owns the token that ends the game, as the number of points for pieces increase relative to the number of points for winning.
 * 
 * @Eric Weber
 * @4/9/16
 */
public class EndVsWinExperiment {
    
    /**
     * Run and analyze games of Chutes and Ladders and Pots.
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100; i ++) {
            final int j = i;
            new Thread() {
                public void run() {
                    File file = new File("Experiment3/output" + j + ".csv");
                    try {
                        file.createNewFile();
                        FileWriter printer = new FileWriter(file);
                        ArrayList<Integer> output = runExperiment(j);
                        printer.write(j + ", " + output.get(0) + ", " + output.get(1) + ", " + output.get(2) + ", " + output.get(3) + ", " + output.get(4) + ", " + output.get(5) + ", " + output.get(6) + ", " + output.get(7));
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
     * @return game winners
     */
    public static ArrayList<Integer> runExperiment(int eNumber) {
        PrintStream dummyStream = new PrintStream(new OutputStream() {
            public void write(int b) {
                //NO-OP
            }
        });
        System.setOut(dummyStream);
        
        ArrayList<Integer> winTimes = new ArrayList<Integer>(8);
        winTimes.add(0);
        winTimes.add(0);
        winTimes.add(0);
        winTimes.add(0);
        winTimes.add(0);
        winTimes.add(0);
        winTimes.add(0);
        winTimes.add(0);
        for (int i = 0; i < 1000; i ++) {
            /** Initialize all components of the game.*/
            Die die = new Die(10);
            LinkedList<Player> players = new LinkedList<Player>();
            File configFile = new File("Configs2/config" + eNumber + ".txt");
            try {
                Scanner configScan = new Scanner(configFile);
                Board board = new Board(configScan, die);
                int x = board.getX();
                int y = board.getY();
                int z = board.getZ();
                int winningPoints = board.winningPoints();
                double piecesPoints = board.piecesPoints();
                int numberOfSpaces = board.numberOfSpaces();
                for (int p = 0; p < 40; p += 4) {
                    players.add(new FinishFirstPlayer(p, 10));
                    players.add(new JustPointsPlayer(p + 1, 10));
                    players.add(new TotalJerkPlayer(p + 2, 10));
                    players.add(new JustInFrontPlayer(p + 3, 10));
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
                if (wonPlayer instanceof FinishFirstPlayer) {
                    winTimes.set(0, winTimes.get(0) + 1);
                }
                else if (wonPlayer instanceof JustPointsPlayer) {
                    winTimes.set(2, winTimes.get(2) + 1);
                }
                else if (wonPlayer instanceof TotalJerkPlayer) {
                    winTimes.set(4, winTimes.get(4) + 1);
                }
                else if (wonPlayer instanceof JustInFrontPlayer) {
                    winTimes.set(6, winTimes.get(6) + 1);
                }
                if (wonPlayer instanceof FinishFirstPlayer && wonPlayer.getHighestIndex() == numberOfSpaces - 1) {
                    winTimes.set(1, winTimes.get(1) + 1);
                }
                else if (wonPlayer instanceof JustPointsPlayer && wonPlayer.getHighestIndex() == numberOfSpaces - 1) {
                    winTimes.set(3, winTimes.get(3) + 1);
                }
                else if (wonPlayer instanceof TotalJerkPlayer && wonPlayer.getHighestIndex() == numberOfSpaces - 1) {
                    winTimes.set(5, winTimes.get(5) + 1);
                }
                else if (wonPlayer instanceof JustInFrontPlayer && wonPlayer.getHighestIndex() == numberOfSpaces - 1) {
                    winTimes.set(7, winTimes.get(7) + 1);
                }
            }
            catch (Exception e) {
                System.out.println("Config file scan has failed.");
                System.out.println(e.getLocalizedMessage());
            }
        }
        return winTimes;
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