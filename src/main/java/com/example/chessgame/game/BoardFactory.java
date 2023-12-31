package com.example.chessgame.game;

import com.example.chessgame.game.pieces.*;
import com.example.chessgame.game.util.Color;

import static com.example.chessgame.game.util.Color.BLACK;
import static com.example.chessgame.game.util.Color.WHITE;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.toLowerCase;

public class BoardFactory {

    public static Board board() {
        Board board = new Board();
        setUpDefaultPosition(board);
        return board;
    }

    public static Board boardWithCustomPosition(String position) {
        Board board = new Board();
        setUpCustomPosition(position, board);
        return board;
    }

    private static void setUpCustomPosition(String position, Board board) {
        String[] rows = position.split("/");
        rows[7] = rows[7].split(" ")[0];
        for (int i = 0; i < rows.length; i++) {
            String currRow = rows[i];
            processRow(currRow, i, board);
        }
    }

    private static void setUpDefaultPosition(Board board) {
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
        board.addPiece(new Pawn(0, 1, BLACK, board));
        board.addPiece(new Pawn(1, 1, BLACK, board));
        board.addPiece(new Pawn(2, 1, BLACK, board));
        board.addPiece(new Pawn(3, 1, BLACK, board));
        board.addPiece(new Pawn(4, 1, BLACK, board));
        board.addPiece(new Pawn(5, 1, BLACK, board));
        board.addPiece(new Pawn(6, 1, BLACK, board));
        board.addPiece(new Pawn(7, 1, BLACK, board));

    }

    private static void processRow(String row, int rowIndex, Board board) {
        int pieceIndex = 0;
        int i = 0;
        while (i < row.length()) {
            char character = row.charAt(i);
            if (Character.isLetter(character)) {
                Color color = isLowerCase(character) ? BLACK : WHITE;
                createPeace(toLowerCase(character), color, pieceIndex, rowIndex, board);
                pieceIndex++;
            } else {
                pieceIndex += Integer.parseInt(String.valueOf(character));
            }
            i++;
        }
    }

    private static void createPeace(char peace, Color color, int file, int row, Board board) {
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
