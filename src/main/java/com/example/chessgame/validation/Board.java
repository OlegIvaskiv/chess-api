package com.example.chessgame.validation;

import com.example.chessgame.validation.peaces.Piece;
import com.example.chessgame.validation.util.Color;
import com.example.chessgame.validation.util.Move;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static com.example.chessgame.validation.util.Color.BLACK;
import static com.example.chessgame.validation.util.Color.WHITE;

public class Board {
    private int checkMate = 0;
    private static Color moveFor;
    private final Stack<Move> moves = new Stack<>();
    private final LinkedList<Piece> pieces = new LinkedList<>();

    public void addMove(Move move) {
        moves.push(move);
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public Piece getPiece(int x, int y) {
        for (Piece p : pieces) {
            if (p.xp == x && p.yp == y) {
                return p;
            }
        }
        return null;
    }

    public List<Move> getAllMoves() {
        return moves.stream().toList();
    }

    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }

    public Move getLastMove() {
        return moves.peek();
    }

    public List<Piece> getAllPieces() {
        return pieces;
    }

    public Color moveFor() {
        return moveFor;
    }

    public void toggleMove() {
        moveFor = moveFor == WHITE ? BLACK : WHITE;
    }

    public void checkMate() {
        checkMate++;
    }

    public boolean isCheckMate() {
        return checkMate > 0;
    }
}
