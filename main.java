import java.util.*;
import java.io.*;
/**
 * Main runs a game of Chutes and Ladders and Pots
 * 
 * @Eric Weber
 * @5/7/16
 */
public class main {
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
        main m = new main();
        m.run(args);
    }
    public main() {
    }
    public void run(String[] args) {
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
            for (int p = 0; p < Integer.parseInt(args[1]); p ++) {
                players.add(new FinishFirstPlayer(p, Integer.parseInt(args[2])));
            }
            /**Initialize user interface.*/
            boolean repeat = true;
            BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
            board.printBoard();
            finishedTokens = new ArrayList<Token>();
            while (repeat) {
                System.out.println("Enter what you want to do: ");
                /**Interpret and execute user commands.*/
                String line = terminalReader.readLine();
                if (line.equals("p")) {
                    /**Print a map of the board.*/
                    repeat = true;
                    printMap(board);
                }
                else if (line.equals("c")) {
                    /**Take one turn*/
                    repeat = true;
                    System.out.println();
                    
                    boolean allFinished = true;
                    for (Player p : players) {
                        for (Token t : p.getTokens()) {
                            if (!finishedTokens.contains(t)) {
                                allFinished = false;
                            }
                        }
                    }
                    if (!allFinished) {
                        takeTurn(Integer.parseInt(args[1]));
                    }
                    else {
                        repeat = false;
                    }
                }
                else if (line.equals("i")) {
                    /**Take turns until the game ends*/
                    System.out.println();
                    boolean finished = false;
                    while (!finished) {
                        finished = takeTurn(Integer.parseInt(args[1]));
                    }
                    repeat = false;
                }
                else if (line.equals("r")) {
                    /**Print player status*/
                    repeat = true;
                    System.out.println();
                    printPlayers(players, numberOfSpaces - 1, winningPoints, piecesPoints);
                }
                else {
                    /**End game*/
                    repeat = false;
                }
            }
        }
        catch (java.io.IOException e) {
            System.out.println("Config file scan has failed.");
            System.out.println(e.getLocalizedMessage());
        }
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