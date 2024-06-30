import java.util.Scanner;

public class Main {
    private static char[][] board = new char[3][3];
    private static final char PLAYER1 = 'X';
    private static final char PLAYER2 = 'O';
    private static boolean isPlayer1Turn = true;

    public static void main(String[] args) {
        initializeBoard();
        printBoard();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter row (0-2):");
            int row = scanner.nextInt();
            System.out.println("Enter column (0-2):");
            int col = scanner.nextInt();

            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid input. Please enter a number between 0 and 2.");
                continue;
            }

            if (board[row][col] != ' ') {
                System.out.println("Cell is already occupied. Please choose another cell.");
                continue;
            }

            char player = isPlayer1Turn ? PLAYER1 : PLAYER2;
            board[row][col] = player;
            printBoard();

            if (checkWin(player)) {
                System.out.println("Player " + (isPlayer1Turn ? "1" : "2") + " wins!");
                break;
            }

            if (checkDraw()) {
                System.out.println("It's a draw!");
                break;
            }

            isPlayer1Turn = !isPlayer1Turn;
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }
    }

    private static boolean checkWin(char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }

        return false;
    }

    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}