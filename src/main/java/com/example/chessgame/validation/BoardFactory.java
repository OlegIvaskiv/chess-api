package com.example.chessgame.validation;

import com.example.chessgame.validation.peaces.*;
import com.example.chessgame.validation.util.Color;

import java.util.Arrays;

import static com.example.chessgame.validation.util.Color.BLACK;
import static com.example.chessgame.validation.util.Color.WHITE;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.toLowerCase;

public class BoardFactory {
    private static final Board board = new Board();

    public static Board board() {
        setUpDefaultPosition();
        return board;
    }

    public static Board boardWithCustomPosition(String position) {
        setUpCustomPosition(position);
        return board;
    }

    private static void setUpCustomPosition(String position) {
        String[] rows = position.split("/");
        System.out.println(Arrays.toString(rows));
        rows[7] = rows[7].split(" ")[0];
        for (int i = 0; i < rows.length; i++) {
            String currRow = rows[i];
            processRow(currRow, i);
        }
    }

    private static void setUpDefaultPosition() {
        board.addPiece(new Rook(0, 7, WHITE, board));
        board.addPiece(new Rook(7, 7, WHITE, board));
        board.addPiece(new Knight(1, 7, WHITE, board));
        board.addPiece(new Knight(6, 7, WHITE, board));
        board.addPiece(new Bishop(2, 7, WHITE, board));
        board.addPiece(new Bishop(5, 7, WHITE, board));
        board.addPiece(new Queen(3, 7, WHITE, board));
        board.addPiece(new King(4, 7, WHITE, board));
        board.addPiece(new Pawn(0, 6, WHITE, board));
        board.addPiece(new Pawn(1, 6, WHITE, board));
        board.addPiece(new Pawn(2, 6, WHITE, board));
        board.addPiece(new Pawn(3, 6, WHITE, board));
        board.addPiece(new Pawn(4, 6, WHITE, board));
        board.addPiece(new Pawn(5, 6, WHITE, board));
        board.addPiece(new Pawn(6, 6, WHITE, board));
        board.addPiece(new Pawn(7, 6, WHITE, board));

        board.addPiece(new Rook(0, 0, BLACK, board));
        board.addPiece(new Rook(7, 0, BLACK, board));
        board.addPiece(new Knight(1, 0, BLACK, board));
        board.addPiece(new Knight(6, 0, BLACK, board));
        board.addPiece(new Bishop(2, 0, BLACK, board));
        board.addPiece(new Bishop(5, 0, BLACK, board));
        board.addPiece(new Queen(3, 0, BLACK, board));
        board.addPiece(new King(4, 0, BLACK, board));
        board.addPiece(new Pawn(0, 0, BLACK, board));
        board.addPiece(new Pawn(0, 1, BLACK, board));
        board.addPiece(new Pawn(0, 2, BLACK, board));
        board.addPiece(new Pawn(0, 3, BLACK, board));
        board.addPiece(new Pawn(0, 4, BLACK, board));
        board.addPiece(new Pawn(0, 5, BLACK, board));
        board.addPiece(new Pawn(0, 6, BLACK, board));
        board.addPiece(new Pawn(0, 7, BLACK, board));
    }

    private static void processRow(String row, int rowIndex) {
        int currIndex = 0;
        while (currIndex < row.length()) {
            char character = row.charAt(currIndex);
            if (Character.isLetter(character)) {
                Color color = isLowerCase(character) ? BLACK : WHITE;
                createPeace(toLowerCase(character), color, currIndex, rowIndex);
                currIndex++;
            } else {
                currIndex += Integer.parseInt(String.valueOf(character));
            }
        }
    }

    private static void createPeace(char peace, Color color, int file, int row) {
        switch (peace) {
            case 'r' -> board.addPiece(new Rook(file, row, color, board));
            case 'n' -> board.addPiece(new Knight(file, row, color, board));
            case 'b' -> board.addPiece(new Bishop(file, row, color, board));
            case 'q' -> board.addPiece(new Queen(file, row, color, board));
            case 'k' -> board.addPiece(new King(file, row, color, board));
            case 'p' -> board.addPiece(new Pawn(file, row, color, board)
            );
        }
    }
}
