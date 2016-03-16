import java.util.*;
import java.io.*;
/**
 * Main runs a game of Chutes and Ladders and Pots
 * 
 * @Eric Weber
 * @3/16/16
 */
public class main {
    /**
     * Run a game of Chutes and Ladders and Pots.
     * 
     * Create a die, a list of players, and a board, and prompt the user for instructions. An input of p should print a map of the board, i.e. location of every token, aid, obstacle and treasure pot and its status. An input of c should move the game forward one turn. An input of i should continue the game until it ends. An input of r should print status of each player/token; the position on the board and the number of treasure pieces in their possession. Any other input should end the game.
     * @param args [0]: max value of the die. [1]: number of players. [2]: number of tokens per player. [3]: name of config file.
     */
    public static void main(String[] args) {
        /** Initialize all components of the game.*/
        Die die = new Die(Integer.parseInt(args[1]));
        LinkedList<Token> tokens = new LinkedList<Token>();
        File configFile = new File(args[3]);
        try {
            Scanner configScan = new Scanner(configFile);
            configScan.next();
            int x = configScan.nextInt();
            int y = configScan.nextInt();
            int z = configScan.nextInt();
            configScan.next();
            int winningPoints = configScan.nextInt();
            double piecesPoints = configScan.nextDouble(); 
            System.out.println(configScan.nextLine());
            System.out.println(configScan.nextLine());
            Board board = new Board(x, y, z, configScan);
            int numberOfSpaces = board.numberOfSpaces();
            for (int p = 0; p < Integer.parseInt(args[2]); p ++) {
                Token token = new Token();
                tokens.add(token);
            }
            /**Initialize user interface.*/
            boolean repeat = true;
            BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
            board.printBoard();
            while (repeat) {
                System.out.println("Enter what you want to do: ");
                /**Interpret and execute user commands.*/
                try {
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
                        if (takeTurn(tokens, board, die, numberOfSpaces - 1)) {
                            repeat = false;
                        }
                    }
                    else if (line.equals("i")) {
                        /**Take turns until the game ends*/
                        System.out.println();
                        while (!(takeTurn(tokens, board, die, numberOfSpaces - 1))) {
                            System.out.println();
                        }
                        repeat = false;
                    }
                    else if (line.equals("r")) {
                        /**Print token status*/
                        repeat = true;
                        System.out.println();
                        printTokens(tokens);
                    }
                    else {
                        /**End game*/
                        repeat = false;
                    }
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (Exception e) {
            System.out.println("Config file scan has failed.");
            System.out.println(e.getLocalizedMessage());
        }
    }
    /**
     * Take a turn.
     * 
     * Iterate through the list of tokens in turn order. Take the turn of each token on their current space, and, if allowed, land them on their next space.
     * @param tokens list of tokens in the game in turn order.
     * @param board the board used for the game.
     * @param d die that is used for the game.
     * @param boardEnd the index of the final space on the board.
     * @return if the game has ended.
     */
    public static boolean takeTurn(LinkedList<Token> tokens, Board board, Die die, int boardEnd) {
        Token cToken;
        Iterator<Token> turns = tokens.iterator();
        while (turns.hasNext()) {
            cToken = turns.next();
            int tokenStartIndex = cToken.getIndex();
            if (board.get(cToken.getIndex()).takeTurn(cToken, die, boardEnd)) {
                /**If a token reaches the final space, print that the game is over and token info.*/
                System.out.println("GAME OVER");
                System.out.println();
                printTokens(tokens);
                return true;
            }
            else {
                if (board.get(tokenStartIndex).canMove()) {
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
     * Print each token, their index, and their pieces.
     * 
     * @param tokens list of tokens in turn order.
     */
    public static void printTokens(LinkedList<Token> tokens) {
        Token cToken;
        Iterator<Token> tokenIter = tokens.iterator();
        while (tokenIter.hasNext()) {
            cToken = tokenIter.next();
            System.out.println(cToken + ": " + cToken.getIndex() + " Pieces: " + cToken.getPieces());
        }
    }
}