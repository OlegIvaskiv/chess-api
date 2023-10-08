package com.example.chessgame.game;

import com.example.chessgame.game.pieces.Piece;
import com.example.chessgame.game.util.Color;
import com.example.chessgame.game.util.Move;
import com.example.chessgame.game.util.MoveResult;

import java.time.Instant;
import java.util.List;

import static java.time.Instant.now;

public class Game {
    private final Instant started;
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
        MoveParser moveParser = new MoveParser();
        StringBuilder result = new StringBuilder();
        List<Move> moves = board.getAllMoves();
        int totalMoves = moves.size();
        int count = 1;
        for (int i = 0; i < totalMoves; i++) {
            Move wMove = moves.get(i);
            result.append(moveParser.parseMove(count, wMove));
            if (i % 2 == 0) count++;
        }
        return result.toString();
    }


}
