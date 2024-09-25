import java.util.Scanner;

public class Main {
    private static char[][] board = {
            {' ', 'A', 'B', 'C'},
            {'1', ' ', ' ', ' '},
            {'2', ' ', ' ', ' '},
            {'3', ' ', ' ', ' '}
    };
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameWon = false;

        while (!gameWon && !isBoardFull()) {
            printBoard();
            System.out.println("Player " + currentPlayer + " next move (e.g., A1): ");
            String move = scanner.nextLine();
            if (isValidMove(move)) {
                makeMove(move);
                gameWon = checkWin();
                if (!gameWon) {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        printBoard();
        if (gameWon) {
            System.out.println("Player " + currentPlayer + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
        scanner.close();
    }

    private static void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(String move) {
        if (move.length() != 2) return false;
        char col = move.charAt(0);
        char row = move.charAt(1);
        if (col < 'A' || col > 'C' || row < '1' || row > '3') return false;
        return board[row - '0'][col - 'A' + 1] == ' ';
    }

    private static void makeMove(String move) {
        char col = move.charAt(0);
        char row = move.charAt(1);
        board[row - '0'][col - 'A' + 1] = currentPlayer;
    }

    private static boolean checkWin() {
        // Check rows, columns, and diagonals
        for (int i = 1; i <= 3; i++) {
            if (board[i][1] == currentPlayer && board[i][2] == currentPlayer && board[i][3] == currentPlayer) return true;
            if (board[1][i] == currentPlayer && board[2][i] == currentPlayer && board[3][i] == currentPlayer) return true;
        }
        if (board[1][1] == currentPlayer && board[2][2] == currentPlayer && board[3][3] == currentPlayer) return true;
        if (board[1][3] == currentPlayer && board[2][2] == currentPlayer && board[3][1] == currentPlayer) return true;
        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }
}
