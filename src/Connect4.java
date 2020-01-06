import java.awt.image.AreaAveragingScaleFilter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Connect4
 *
 * Allows two users to play "Connect 4." The first player to get 4 pieces in a row in any direction wins.
 *
 * @author Benjamin Bowers, lab sec 04
 *
 * @version 6/30/2018
 *
 */

public class Connect4 {
    private char[][] gameBoard; //2D array where the game pieces are stored.
    public int moveCounter; //The current move number.

    public Connect4() {
        this.gameBoard = new char[7][8];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j] = ' ';
            }
        }
        this.moveCounter = 1;
    }

    public char[][] getBoard() {
        return gameBoard.clone();
    }

    public char checkAlignment(int rows, int column) {
        char winner = ' ';
        boolean winnerFound = false;
        int row = 6;

            while (row >= 0 && winnerFound == false) {
                for (int col = 0; col < 8; col++) {
                    //Checks if a piece is present before looking for matches
                    if (gameBoard[row][col] != ' ') {
                        // Checks up and left diagonally 3 spaces for matching pieces:
                        if (col - 3 >= 0 && row - 3 >= 0) {
                            int currentCol = col - 1;
                            int currentRow = row - 1;
                            int matches = 0;

                            while (matches < 3) {
                                if (gameBoard[currentRow][currentCol] == gameBoard[row][col]) {
                                    matches++;
                                    currentCol--;
                                    currentRow--;
                                } else {
                                    break;
                                }
                            }
                            if (matches == 3) {
                                winnerFound = true;
                                winner = gameBoard[row][col];
                            }
                        }

                        // Checks up and right diagonally 3 spaces for matching pieces:
                        if (col + 3 < 8 && row - 3 >= 0) {
                            int currentCol = col + 1;
                            int currentRow = row - 1;
                            int matches = 0;

                            while (matches < 3) {
                                if (gameBoard[currentRow][currentCol] == gameBoard[row][col]) {
                                    matches++;
                                    currentCol++;
                                    currentRow--;
                                } else {
                                    break;
                                }
                            }
                            if (matches == 3) {
                                winnerFound = true;
                                winner = gameBoard[row][col];
                            }
                        }

                        // Checks up 3 spaces for matching pieces:
                        if (row - 3 >= 0) {
                            int currentRow = row - 1;
                            int matches = 0;

                            while (matches < 3) {
                                if (gameBoard[currentRow][col] == gameBoard[row][col]) {
                                    matches++;
                                    currentRow--;
                                } else {
                                    break;
                                }
                            }
                            if (matches == 3) {
                                winnerFound = true;
                                winner = gameBoard[row][col];
                                break;
                            }
                        }

                        // Check right 3 spaces for matching pieces:
                        if (col + 3 < 8) {
                            int currentCol = col + 1;
                            int matches = 0;

                            while (matches < 3) {
                                if (gameBoard[row][currentCol] == gameBoard[row][col]) {
                                    matches++;
                                    currentCol++;
                                } else {
                                    break;
                                }
                            }
                            if (matches == 3) {
                                winnerFound = true;
                                winner = gameBoard[row][col];
                                break;
                            }
                        }
                    }
                }
                row--;
            }

        return winner;
    }

    public void printScreen() {
        System.out.print("    0   1   2   3   4   5   6   7  ");

        for (int row = 0; row < 7; row++) {
            System.out.println();
            System.out.println("  ---------------------------------");
            System.out.print((char)('A' + row) + " |");
            for (int col = 0; col < 8; col++){
                System.out.print(" " + gameBoard[row][col] + " |");
            }
        }
        System.out.println();
        System.out.println("  ---------------------------------");
    }

    public void play() {
        Scanner scan = new Scanner(System.in);
        char winner = ' ';
        char currentPlayer = 'O';
        int column = -1;
        int row = -1;

        while (winner == ' ') {
            printScreen();
            System.out.println("Current player: " + "'" + currentPlayer + "'");
            System.out.println("Current Move: " + moveCounter);
            do {
                System.out.print("Choose a column: ");
                try {
                    column = Integer.parseInt(scan.nextLine());
                } catch (NumberFormatException e) {
                    column = -1;
                    continue;
                }
                row = putPiece(column, currentPlayer);
            } while (column < 0 || column > 7 || row == -1);

            winner = checkAlignment(row, column);
            moveCounter++;
            if (currentPlayer == 'O')
                currentPlayer = 'X';
            else
                currentPlayer = 'O';

            System.out.println();
        }

        printScreen();
        System.out.println();
        System.out.println("!!! After " + moveCounter + " moves, the winner is Player '" + winner + "' !!!");
    }


    public int putPiece(int column, char currentPlayer) {
        for (int row = 6; row >= 0; row--) {
            if (gameBoard[row][column] == ' '){
                gameBoard[row][column] = currentPlayer;
                return row;
            }
        }
        return -1;
    }

}
