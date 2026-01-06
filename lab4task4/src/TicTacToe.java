import java.util.Random;

public class TicTacToe {
    private char[][] board;
    private static final char EMPTY = ' ';
    private static final char PLAYER = 'X';
    private static final char COMPUTER = 'O';

    public TicTacToe() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void printBoard() {
        System.out.println("   0    1    2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + "   ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println(" --------------");
        }
        System.out.println();
    }

    public boolean checkWin(char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) return true;
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;

        return false;
    }

    // Simple AI: Find winning move, block opponent, or choose random
    public void computerMove() {

        // Try to win
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = COMPUTER;
                    if (checkWin(COMPUTER)) return;
                    board[i][j] = EMPTY;
                }
            }
        }

        // Try to block player
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = PLAYER;
                    if (checkWin(PLAYER)) {
                        board[i][j] = COMPUTER;
                        return;
                    }
                    board[i][j] = EMPTY;
                }
            }
        }

        // Choose center if available
        if (board[1][1] == EMPTY) {
            board[1][1] = COMPUTER;
            return;
        }

        // Choose random available position
        Random rand = new Random();
        boolean hasPosition = false;
        do {
            int rand1 = rand.nextInt(3);
            int rand2 = rand.nextInt(3);
            // check if board location is empty and can be used
            if (isCellEmpty(rand1, rand2)) {
                board[rand1][rand2] = COMPUTER;
                hasPosition = true;
            }
        } while (!hasPosition);
    }

    // Player assign location
    public boolean playerMove(int row, int col){
        if(isCellEmpty(row,col)){
            board[row][col] = PLAYER;
            return true;
        }
        return false;
    }

    // Check if there are any empty cells for draw or for AI to know if it should make move
    public boolean areEmptyCells() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return true;
                }
            }
        return false;
    }

    // Check if selected cell is empty
    public boolean isCellEmpty(int row, int col) {
        return board[row][col] == EMPTY;
    }

    // reset board
    public void resetBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }
}