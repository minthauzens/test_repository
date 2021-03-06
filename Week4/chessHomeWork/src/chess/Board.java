package chess;
import chess.figures.*;

import java.util.Scanner;


class Board {

    private Figure[][] board;
    private Scanner scanner;
    private Player currentPlayer;

    Board() {
        this.board = new Figure[8][8];
        this.scanner = new Scanner(System.in);
        this.currentPlayer = Player.WHITE;
        fillBoard();
    }

    private void fillBoard() {
        // todo finish implementation;

        // PAWNS
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Player.WHITE);
            board[6][i] = new Pawn(Player.BLACK);
        }
        // ROOKS
        board[0][0] = new Rook(Player.WHITE);
        board[0][7] = new Rook(Player.WHITE);
        board[7][0] = new Rook(Player.BLACK);
        board[7][7] = new Rook(Player.BLACK);

        // KNIGHTS
        board[0][1] = new Knight(Player.WHITE);
        board[0][6] = new Knight(Player.WHITE);
        board[7][1] = new Knight(Player.BLACK);
        board[7][6] = new Knight(Player.BLACK);

        // BISHOPS
        board[0][2] = new Bishop(Player.WHITE);
        board[0][5] = new Bishop(Player.WHITE);
        board[7][2] = new Bishop(Player.BLACK);
        board[7][5] = new Bishop(Player.BLACK);

        // QUEENS
        board[0][3] = new Queen(Player.WHITE);
        board[7][3] = new Queen(Player.BLACK);

        // KINGS
        board[0][4] = new King(Player.WHITE);
        board[7][4] = new King(Player.BLACK);

    }

    void print() {
        String abc = "ABCDEFGH";
        System.out.print("  ");
        char fullWidth = '\uFF21';
        for (int i = 0; i < abc.length(); i++) {
            char ch = (char) (fullWidth + i);
            System.out.printf("%-2s", ch);
        }

        System.out.println();
        for (int i = 7; i >= 0; i--) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    System.out.print(String.format("%-2s", board[i][j]));
                } else {
                    // some random symbol
                    System.out.print(String.format("%-2s", '\u2609'));
                }
            }
            System.out.print(" " + (i + 1));

            System.out.println();
        }
    }

    void move() {
        printPlayer();
        // get user input
        int colFrom = getInputColumnString("From which column? [A-H]");
        int rowFrom = getInputRowInt("From which row? [1-8]");
        int colTo = getInputColumnString("To which column? [A-H]");
        int rowTo = getInputRowInt("To which row? [1-8]");

        moveFigure(colFrom, rowFrom, colTo, rowTo);
        changePlayer();
    }

    private void printPlayer() {
        System.out.println("Current player: " + currentPlayer);
    }

    private void changePlayer() {
        if (this.currentPlayer == Player.WHITE) {
            this.currentPlayer = Player.BLACK;
        } else {
            this.currentPlayer = Player.WHITE;
        }
    }

    private void moveFigure(int colFrom, int rowFrom, int colTo, int rowTo) {
        Figure moving = this.board[rowFrom][colFrom];
        this.board[rowFrom][colFrom] = null;
        this.board[rowTo][colTo] = moving;
    }

    private int getInputRowInt(String message) {
        while (true) {
            System.out.println(message);
            int row = Integer.parseInt(scanner.nextLine()) - 1;
            if (row >= -1 && row < 8) {
                return row;
            } else {
                System.out.println("Invalid input for row");
            }
        }

    }

    private int getInputColumnString(String message) {
        while (true) {
            System.out.println(message);
            String column = scanner.nextLine();
            int col = convertInputToColumnNumber(column);
            if (col != -1) {
                return col;
            } else {
                System.out.println("Invalid input for column");
            }
        }
    }

    private int convertInputToColumnNumber(String column) {
        column = column.toUpperCase();
        switch (column) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
            default:
                return -1;
        }
    }
}
