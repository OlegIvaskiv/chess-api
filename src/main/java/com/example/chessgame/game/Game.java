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


    /*
    *
[Date "2023.10.09"]
[Round "-"]
[White "OlehIvaskiv"]
[Black "maximumanimal"]
[Result "1/2-1/2"]
[WhiteElo "1265"]
[BlackElo "1235"]
[TimeControl "600"]
[EndTime "1:39:34 PDT"]
[Termination "Game drawn - insufficient material"]
    * */
    //1. e4 {[%clk 0:09:56.5]} 1... c6 {[%clk 0:09:59.4]}
    public String saveToPGN() {
        String patter = """
                [Date "%s"]
                [Round "%s"]
                [White "%s"]
                [Black "%s"]
                [Result "%s"]
                [WhiteElo "%d"]
                [BlackElo "%d"]
                [TimeControl "%d"]
                [EndTime "%d"]
                [%s"]
                \n
                \n
                """;

        StringBuilder result = new StringBuilder();
        MoveParser moveParser = new MoveParser();
        List<Move> moves = board.getAllMoves();
        int totalMoves = moves.size();
        int count = 1;
        for (int i = 0; i < totalMoves; i++) {
            Move move = moves.get(i);
            result.append(moveParser.parseMove(count, move));
            if (i % 2 == 0) count++;
        }
        return result.toString();
    }


}
