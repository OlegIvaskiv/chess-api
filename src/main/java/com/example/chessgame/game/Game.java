package com.example.chessgame.game;

import com.example.chessgame.game.pieces.Piece;
import com.example.chessgame.game.util.Color;
import com.example.chessgame.game.util.GameState;
import com.example.chessgame.game.util.Move;
import com.example.chessgame.game.util.MoveResult;

import java.time.Instant;
import java.util.List;

import static java.time.Instant.now;

public class Game {
    private final Instant started;
    private GameState gameState;
    private final Board board;

    public Game(String position, Color color) {
        started = now();
        board = BoardFactory.boardWithCustomPosition(position);
        if (board.moveFor() != color) board.toggleMoveSide();
    }

    public Game() {
        started = now();
        board = BoardFactory.board();
    }

    public MoveResult move(Move move) {
        Piece piece = board.getPiece(move.getFrom().x, move.getFrom().y);
        if (piece == null) return MoveResult.BAD_MOVE;
        return piece.move(move);
    }

    public List<Move> getGameMoves() {
        return board.getAllMoves();
    }

    public String saveToPGN() {
        return "";
    }

}
