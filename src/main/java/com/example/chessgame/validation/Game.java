package com.example.chessgame.validation;

import com.example.chessgame.validation.peaces.Piece;
import com.example.chessgame.validation.util.GameState;
import com.example.chessgame.validation.util.Move;

import java.util.List;

public class Game {
    private GameState gameState;
    private final Board board = BoardFactory.board();

    public boolean move(Move move) {
        Piece piece = board.getPiece(move.getFrom().x, move.getFrom().y);
        if (piece == null) return false;
        return piece.move(move);
    }

    public List<Move> getGameMoves() {
        return board.getAllMoves();
    }

    public String saveToPGN() {
        return "";
    }

}
