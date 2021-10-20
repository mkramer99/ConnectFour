import java.util.Scanner;

public class ConnectFour {

    // THIS IS MY LAB06 COMMIT COMMENT
    // THIS IS MY SECOND LAB06 COMMIT COMMENT
    public static void main(String[] args) {
        // create the playing board
        Scanner scan = new Scanner(System.in);
        System.out.println("What would you like the height of the board to be? ");
        int height = scan.nextInt();
        System.out.println("What would you like the length of the board to be? ");
        int length = scan.nextInt();
        char[][] boardArray = new char[height][length];
        initializeBoard(boardArray);

        System.out.println("Player 1: x");
        System.out.println("Player 2: o");

        // call insertChip, checkIfWinner
        Boolean winner = false;
        int col;
        char playerOne = 'x';
        char playerTwo = 'o';
        int rowReturn;
        int boardSpaces = height * length;

        // until there is a winner or draw
        while (winner == false) {
            // PLAYER 1
            System.out.println("Player 1: Which column would you like to choose? ");
            col = scan.nextInt();
            // call insertChip, checkIfWinner
            rowReturn = insertChip(boardArray, col, playerOne);
            --boardSpaces;
            printBoard(boardArray);
            if (checkIfWinner(boardArray, col, rowReturn, playerOne)) {
                System.out.println("Player 1 won the game!");
                winner = true;
                break;
            }
            // PLAYER 2
            System.out.println("Player 2: Which column would you like to choose? ");
            col = scan.nextInt();
            // call insertChip, checkIfWinner
            rowReturn = insertChip(boardArray, col, playerTwo);
            --boardSpaces;
            printBoard(boardArray);
            if (checkIfWinner(boardArray, col, rowReturn, playerTwo)) {
                System.out.println("Player 2 won the game!");
                winner = true;
                break;
            }
            // if all spaces full with no winner - draw
            if (boardSpaces == 0) {
                System.out.println("Draw. Nobody wins.");
                break;
            }
        }
    }

    public static void printBoard(char[][] array) {
        // print the board with given array size
        for (int row = array.length - 1; row >= 0; row--) {
            for (int col = 0; col < array[0].length; col++) {
                System.out.print(array[row][col]);
            }
            System.out.println();
        }
    }
    public static void initializeBoard(char[][] array) {
        // set each spot in the array to "-"
        for (int row = array.length - 1; row >= 0; row--) {
            for (int col = 0; col < array[0].length; col++) {
                array[row][col] = '-';
                System.out.print(array[row][col]);
            }
            System.out.println();
        }
    }
    public static int insertChip(char[][] array, int col, char chipType) {
        // place token in user selected column
        // will find next available spot in that column if tokens already filled
        // the row that the token is placed in is returned
        int rowReturn = 0;
        for (int r = 0; r < array.length; r++) {
        //for (int row = array.length - 1; row >= 0; row--) {
            if (array[r][col] == '-') {
                array[r][col] = chipType;
                rowReturn = r;
                break;
            }
            else {
                continue;
            }
        }
        return rowReturn;
    }
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        // after a token is added, check whether the token in this location
        // of the specified type creates four in a row
        // return if someone won, or false if otherwise
        Boolean winner = false;
        int connect = 0;
        // check for column winner
        for (int r = 0; r < array.length; r++) {
            if (array[r][col] == chipType) {
                connect++;
                if (connect == 4) {
                    winner = true;
                    connect = 0;
                    break;
                }
            }
            else {
                winner = false;
                connect = 0;
            }
        }
        // if no column win, check for row win
        if (winner == false) {
            // check for row winner
            for (int i = 0; i < array[row].length; i++) {
                if (array[row][i] == chipType) {
                    connect++;
                    if (connect == 4) {
                        winner = true;
                        connect = 0;
                        break;
                    }
                } else {
                    winner = false;
                    connect = 0;
                }
            }
        }
        return winner;
    }
}
