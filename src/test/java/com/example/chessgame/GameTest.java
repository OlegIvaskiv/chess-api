package com.example.chessgame;

import com.example.chessgame.game.Game;
import com.example.chessgame.game.pieces.King;
import com.example.chessgame.game.pieces.Knight;
import com.example.chessgame.game.pieces.Pawn;
import com.example.chessgame.game.pieces.Queen;
import com.example.chessgame.game.util.Color;
import com.example.chessgame.game.util.Move;
import com.example.chessgame.game.util.MoveResult;
import org.junit.jupiter.api.Test;

import static com.example.chessgame.game.util.File.*;
import static com.example.chessgame.game.util.MoveResult.*;
import static com.example.chessgame.game.util.Row.*;
import static java.time.Instant.now;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    private Game game = new Game();

    @Test
    public void pawnLegalMove() {
        MoveResult moveResult = game.move(new Move(Pawn.class, Color.WHITE, E, _2,
                E, _4, now()));
        assertEquals(moveResult, MADE);
        assertEquals(1, game.getGameMoves().size());
    }

    @Test
    public void longCastling() {
        game = new Game("rnbqkbnr/ppp3pp/3p1p2/4p3/3P1B2/2NQ4/PPP1PPPP/R3KBNR", Color.WHITE);
        MoveResult moveResult = game.move(new Move(King.class, Color.WHITE, E, _1,
                C, _1, now()));
        assertEquals(moveResult, MADE);
    }

    @Test
    public void longCastlingWithFullPieces() {
        game = new Game("rnb1kbnr/ppp2ppp/3p1q2/4p3/4P3/2NP4/PPPQ1PPP/R3KBNR", Color.WHITE);
        MoveResult moveResult = game.move(new Move(King.class, Color.WHITE, E, _1,
                C, _1, now()));
        assertEquals(moveResult, MADE);
    }

    @Test
    public void longCastlingNotAllowed() {
        game = new Game("rnb1kbnr/ppp2ppp/3p3q/3Np3/1Q2P3/3P4/PPP2PPP/R3KBNR", Color.WHITE);
        MoveResult moveResult = game.move(new Move(King.class, Color.WHITE, E, _1,
                C, _1, now()));
        assertEquals(moveResult, BAD_MOVE);
    }

    @Test
    public void moveWhenCheckAllowed() {
        game = new Game("rnb1kbnr/pppp1ppp/4p3/8/5P1q/4P3/PPPP2PP/RNBQKBNR", Color.WHITE);
        MoveResult moveResult = game.move(new Move(Pawn.class, Color.WHITE, G, _2,
                G, _3, now()));
        assertEquals(MADE, moveResult);
    }

    @Test
    public void moveWhenCheckNotAllowed() {
        game = new Game("rnb1kbnr/ppp2ppp/3p4/3Np3/1Q2P2q/3P1P2/PPP3PP/R3KBNR", Color.WHITE);
        MoveResult moveResult = game.move(new Move(Pawn.class, Color.WHITE, G, _2,
                G, _4, now()));
        assertEquals(moveResult, BAD_MOVE);
    }

    @Test
    public void moveWhenCheckNotAllowed1() {
        game = new Game("rnb1kbnr/ppp2ppp/3p4/3Np3/1Q2P2q/3P1P2/PPP3PP/R3KBNR", Color.WHITE);
        MoveResult moveResult = game.move(new Move(Pawn.class, Color.WHITE, G, _2,
                G, _4, now()));
        assertEquals(moveResult, BAD_MOVE);
    }

    @Test
    public void moveWhenPinnedPawn() {
        game = new Game("rnb1kbnr/pppp1ppp/8/4p3/4P2q/8/PPPP1PPP/RNBQKBNR", Color.WHITE);
        MoveResult moveResult1 = game.move(new Move(Pawn.class, Color.WHITE, F, _2,
                F, _4, now()));
        MoveResult moveResult2 = game.move(new Move(Pawn.class, Color.WHITE, F, _2,
                F, _4, now()));
        assertEquals(moveResult1, BAD_MOVE);
        assertEquals(moveResult2, BAD_MOVE);
    }

    @Test
    public void check1() {
        game = new Game("rnb1kbnr/pppp1ppp/8/4p3/4P3/1P6/P1PP1qPP/RNBQKBNR", Color.BLACK);
        MoveResult moveResult = game.move(new Move(Pawn.class, Color.WHITE, C, _2,
                C, _3, now()));
        assertEquals(BAD_MOVE, moveResult);
    }

    @Test
    public void checkMate() {
        game = new Game("rnb1k1nr/pppp1ppp/8/2b1p3/4P2q/1P6/P1PP1PPP/RNBQKBNR", Color.BLACK);
        MoveResult moveResult = game.move(new Move(Queen.class, Color.BLACK, H, _4,
                F, _2, now()));
        assertEquals(CHECK_MATE, moveResult);
    }

    @Test
    public void draw() {
        game = new Game("k7/7R/8/8/8/8/K7/2Q5", Color.WHITE);
        MoveResult moveResult = game.move(new Move(Queen.class, Color.WHITE, C, _1,
                B, _2, now()));
        assertEquals(DRAW, moveResult);
    }

    @Test
    public void draw1() {
        game = new Game("8/8/7Q/2k5/6n1/3K4/8/8", Color.BLACK);
        MoveResult moveResult = game.move(new Move(Knight.class, Color.BLACK, G, _4,
                H, _6, now()));
        assertEquals(DRAW, moveResult);
    }

    @Test
    public void draw2() {
        game = new Game("2B5/2k5/8/8/8/8/K7/8", Color.BLACK);
        MoveResult moveResult = game.move(new Move(King.class, Color.BLACK, C, _7,
                C, _8, now()));
        assertEquals(DRAW, moveResult);
    }

    @Test
    public void draw3() {
        game = new Game("2R5/2k5/8/8/8/8/K7/8", Color.BLACK);
        MoveResult moveResult = game.move(new Move(King.class, Color.BLACK, C, _7,
                C, _8, now()));
        assertEquals(DRAW, moveResult);
    }

    @Test
    public void draw4() {
        game = new Game("k7/P7/8/K7/5B2/8/8/8", Color.WHITE);
        MoveResult moveResult = game.move(new Move(King.class, Color.WHITE, A, _5,
                A, _6, now()));
        assertEquals(DRAW, moveResult);
    }

    @Test
    public void promote() {
        game = new Game("8/6P1/1k6/8/8/8/1K6/8", Color.WHITE);
        MoveResult moveResult = game.move(new Move(Pawn.class, Color.WHITE, G, _7,
                G, _8, now(), Queen.class));
        assertEquals(MADE, moveResult);
    }

}
