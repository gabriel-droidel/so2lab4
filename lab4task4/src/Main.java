import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();

        System.out.println("=== TIC TAC TOE ===");
        System.out.println("This is the initial board");
        game.printBoard();
        while (true) {
            game.resetBoard();
            while (true) {
                // check if there are available cells on the board
                if (game.areEmptyCells()) {
                    // computer move
                    System.out.println("=== Computer Turn ===");
                    game.computerMove();
                    game.printBoard(); // print board
                    // check for computer win
                    if (game.checkWin('O')) {
                        System.out.println("=== The computer won! ===");
                        break;
                    }
                    if (!game.areEmptyCells()) {
                        System.out.println("=== DRAW! ===");
                        break;
                    }
                    // player move
                    // cell selection
                    while (true) {
                        System.out.println("=== Player Turn ===");
                        int row, col;

                        // row selection
                        System.out.print("Choose a row (0-2):");
                        while (true) {
                            row = scanner.nextInt();
                            if (!checkInput(row)) {
                                System.out.print("Invalid, choose from (0-2):");
                            } else {
                                break;
                            }
                        }

                        // column selection
                        System.out.print("Choose a column (0-2):");
                        while (true) {
                            col = scanner.nextInt();
                            if (!checkInput(col)) {
                                System.out.print("Invalid, choose from (0-2):");
                            } else {
                                break;
                            }
                        }

                        // validate player move on empty cell
                        if (game.playerMove(row, col)) {
                            break;
                        } else {
                            System.out.println("Cell is not empty!");
                        }

                    } // player selection loop

                    game.printBoard(); // print board after selection

                    // check if player won
                    if (game.checkWin('X')) {
                        System.out.println("The player won!");
                        break;
                    }
                    // check for draw
                    if (!game.areEmptyCells()) {
                        System.out.println("DRAW!");
                        break;
                    }
                    // if no more cells available, automatic draw
                } else {
                    System.out.println("DRAW!");
                    game.printBoard();
                    break;
                }
            }// round loop
            // play another round condition
            scanner.nextLine(); // consume the leftover newline1
            System.out.println("Play again? Input 42 for YES, everything else for NO.");
            String input = scanner.nextLine();
            if (!(input.trim().equals("42"))) {
                System.out.println("Thank you for playing!");
                break;
            }
            System.out.println("=== NEW ROUND ===");
        } // game loop

    }

    // user input validation
    public static boolean checkInput(int numb) {
        return numb >= 0 && numb <= 2;
    }
}