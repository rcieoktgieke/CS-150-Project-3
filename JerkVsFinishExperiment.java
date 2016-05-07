import java.util.*;
import java.io.*;
/**
 * JerkVsFinishExperiment tests how often TotalJerkPlayers win against FinishFirstPlayers as the board has increasing numbers of PHolds and HoldQs.
 * 
 * @Eric Weber
 * @5/7/16
 */
public class JerkVsFinishExperiment {
    private Die die;
    private LinkedList<Player> players;
    private Board board;
    private int x;
    private int y;
    private int z;
    private boolean finalSpaceVisited;
    private ArrayList<Integer> unvisitedLevels;
    private int winningPoints;
    private double piecesPoints;
    private int numberOfSpaces;
    private ArrayList<Token> finishedTokens;
    /**
     * Run a game of Chutes and Ladders and Pots.
     * 
     * Create a die, a list of players, and a board, and prompt the user for instructions. An input of p should print a map of the board, i.e. location of every token, aid, obstacle and treasure pot and its status. An input of c should move the game forward one turn. An input of i should continue the game until it ends. An input of r should print status of each player/token; the position on the board and the number of treasure pieces in their possession. Any other input should end the game.
     * @param args [0]: max value of the die. [1]: number of players. [2]: number of tokens per player. [3]: name of config file.  [4+]: names of graph files to be added to the board.
     */
    public static void main(String[] args) {
        for (int i = 0; i < 90; i ++) {
            final int j = i;
            for (int x = 0; x < 120; x ++) {
                final int y = x;
                new Thread() {
                    public void run() {
                        File file = new File("Experiment1/output" + j + "-" + y + ".csv");
                        try {
                            JerkVsFinishExperiment e = new JerkVsFinishExperiment();
                            String [] args = {"10", "10", "5", "Configs1/config" + j + ".txt", "new-maze-25-50.txt"};
                            file.createNewFile();
                            FileWriter printer = new FileWriter(file);
                            printer.write(j + ", " + e.run(args));
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
    }
    public JerkVsFinishExperiment() {
    }
    public double run(String[] args) {
        PrintStream dummyStream = new PrintStream(new OutputStream() {
            public void write(int b) {
                //NO-OP
            }
        });
        System.setOut(dummyStream);
        
        double percentWonByJerks = 0.0;
        
        /** Initialize all components of the game.*/
        die = new Die(Integer.parseInt(args[1]));
        players = new LinkedList<Player>();
        File configFile = new File(args[3]);
        try {
            Scanner configScan = new Scanner(configFile);
            ArrayList<Scanner> graphScans = new ArrayList<Scanner>();
            for (int i = 4; i < args.length; i ++) {
                graphScans.add(new Scanner(new File(args[i])));
            }
            board = new Board(configScan, graphScans, die);
            x = board.getX();
            y = board.getY();
            z = board.getZ();
            finalSpaceVisited = false;
            unvisitedLevels = new ArrayList<Integer>();
            for (int l = 0; l < z; l ++) {
                unvisitedLevels.add(l);
            }
            winningPoints = board.winningPoints();
            piecesPoints = board.piecesPoints();
            numberOfSpaces = board.numberOfSpaces();
            for (int p = 0; p < 20; p += 2) {
                players.add(new FinishFirstPlayer(p, 5));
                players.add(new TotalJerkPlayer(p + 1, 5));
            }
            finishedTokens = new ArrayList<Token>();
            boolean finished = false;
            while (!finished) {
                finished = takeTurn(Integer.parseInt(args[1]));
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
        catch (java.io.IOException e) {
            System.out.println("Config file scan has failed.");
            System.out.println(e.getLocalizedMessage());
        }
        
        return percentWonByJerks * 100;
    }
    /**
     * Take a turn.
     * 
     * Iterate through the list of players in turn order. Take the turn of each token each player chooses on its current space, and, if allowed, land them on their next space.
     * @param dRange upper number for the range of the die.
     * @return a token if all of a player's tokens have reached the end of the board.
     */
    public boolean takeTurn(int dRange) {
        int boardEnd = numberOfSpaces - 1;
        int w = winningPoints;
        double p = piecesPoints;
        Token cToken;
        Iterator<Player> turns = players.iterator();
        while (turns.hasNext()) {
            int roll = die.roll();
            Player cPlayer = turns.next();
            cToken = cPlayer.whichToken(roll, board, boardEnd, dRange, players, w, p);
            int tokenStartIndex = cToken.getIndex();
            boolean tokenFinished = false;
            for (Token t : finishedTokens) {
                if (t == cToken) {
                    tokenFinished = true;
                }
            }
            if (!tokenFinished) {
                boolean canMove = board.get(cToken.getIndex()).canMove(cToken, roll, boardEnd);
                if (board.get(cToken.getIndex()).takeTurn(cToken, roll, boardEnd)) {
                    /**If a token reaches the final space, print that it finished and its info.*/
                    System.out.println("Token finished: " + cToken.toString());
                    finishedTokens.add(cToken);
                    for (Player player : players) {
                        boolean playerFinished = true;
                        for (Token t : player.getTokens()) {
                            if (!finishedTokens.contains(t)) {
                                playerFinished = false;
                            }
                        }
                        if (playerFinished) {
                            System.out.println("Player has ended the game: " + player.toString());
                            return true;
                        }
                    }
                    if (!finalSpaceVisited) {
                        finalSpaceVisited = true;
                        System.out.println(cPlayer.toString() + " has reached the end of the board first and recieves " + w + " points.");
                        cPlayer.addPoints(w);
                    }
                }
                else {
                    if (canMove && board.get(tokenStartIndex).advanced()) {
                        /**If the token can move, call land on its new space.*/
                        if (unvisitedLevels.contains(cToken.getIndex() / (x*y))) {
                            unvisitedLevels.remove(new Integer(cToken.getIndex() / (x*y)));
                            System.out.println(cPlayer.toString() + " has reached level " + cToken.getIndex() / (x*y) + " first and recieves " + (int) ((w/2.0 * 10) + 5) / 10 + " points.");
                            cPlayer.addPoints((int) ((w/2.0 * 10) + 5) / 10);
                        }
                        board.get(cToken.getIndex()).land(cToken, die);
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
        return false;
    }
    /**
     * Print a map of the board.
     * 
     * @param board the board used for the game.
     */
    public void printMap(Board board) {
        board.printBoard();
    }
    /**
     * Print each player, their tokens, and their pieces.
     * 
     * @param players list of players in turn order.
     */
    public void printPlayers(LinkedList<Player> players, int boardEnd, int w, double p) {
        Player cPlayer;
        Iterator<Player> playerIter = players.iterator();
        while (playerIter.hasNext()) {
            cPlayer = playerIter.next();
            System.out.println(cPlayer + ": " + cPlayer.getPoints(boardEnd, w, p));
            cPlayer.printTokens();
        }
    }
}