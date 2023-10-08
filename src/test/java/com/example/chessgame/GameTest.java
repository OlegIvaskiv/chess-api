package com.example.chessgame;

import com.example.chessgame.game.Game;
import com.example.chessgame.game.pieces.*;
import com.example.chessgame.game.util.Color;
import com.example.chessgame.game.util.Move;
import com.example.chessgame.game.util.MoveResult;
import org.junit.jupiter.api.Test;

import static com.example.chessgame.game.util.File.*;
import static com.example.chessgame.game.util.MoveResult.*;
import static com.example.chessgame.game.util.Row.*;
import static java.time.Instant.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        MoveResult moveResult1 = game.move(new Move(King.class, Color.BLACK, B, _6,
                C, _7, now()));
        MoveResult moveResult2 = game.move(new Move(Queen.class, Color.BLACK, G, _8,
                A, _2, now()));
        assertEquals(MADE, moveResult);
        assertEquals(MADE, moveResult1);
        assertEquals(MADE, moveResult2);
    }

    @Test
    public void bugTest() {
        game = new Game("r1bQ4/pp6/k1n4R/1qbp4/qQ6/Q4K2/5P2/6N1", Color.WHITE);
        MoveResult moveResult1 = game.move(new Move(Queen.class, Color.WHITE, A, _3,
                A, _4, now(), Queen.class));

        MoveResult moveResult2 = game.move(new Move(Queen.class, Color.BLACK, B, _5,
                A, _4, now(), Queen.class));

        assertEquals(CHECK, moveResult1);
        assertEquals(MADE, moveResult2);
    }

    @Test
    public void saveGameToPGN() {
        Game game = new Game();
        testGameMoves(game);
        String pgn = game.saveToPGN();
        System.out.println(pgn);
    }

    private void testGameMoves(Game game) {
        MoveResult m1 = game.move(new Move(Pawn.class, Color.WHITE, E, _2, E, _4, now())); // 1 move
        MoveResult m2 = game.move(new Move(Pawn.class, Color.BLACK, E, _7, E, _6, now()));

        MoveResult m3 = game.move(new Move(Pawn.class, Color.WHITE, D, _2, D, _4, now()));
        MoveResult m4 = game.move(new Move(Pawn.class, Color.BLACK, D, _7, D, _5, now())); // 2

        MoveResult m5 = game.move(new Move(Knight.class, Color.WHITE, B, _1, C, _3, now()));
        MoveResult m6 = game.move(new Move(Knight.class, Color.BLACK, G, _8, F, _6, now())); // 3

        MoveResult m7 = game.move(new Move(Bishop.class, Color.WHITE, C, _1, G, _5, now()));
        MoveResult m8 = game.move(new Move(Bishop.class, Color.BLACK, F, _8, B, _4, now())); // 4

        MoveResult m9 = game.move(new Move(Pawn.class, Color.WHITE, E, _4, E, _5, now()));
        MoveResult m10 = game.move(new Move(Pawn.class, Color.BLACK, H, _7, H, _6, now())); // 5

        MoveResult m11 = game.move(new Move(Pawn.class, Color.WHITE, E, _5, F, _6, now()));
        MoveResult m12 = game.move(new Move(Pawn.class, Color.BLACK, H, _6, G, _5, now())); // 6

        MoveResult m13 = game.move(new Move(Pawn.class, Color.WHITE, F, _6, G, _7, now()));
        MoveResult m14 = game.move(new Move(Rook.class, Color.BLACK, H, _8, G, _8, now())); // 7

        MoveResult m15 = game.move(new Move(Pawn.class, Color.WHITE, H, _2, H, _4, now()));
        MoveResult m16 = game.move(new Move(Pawn.class, Color.BLACK, G, _5, H, _4, now())); // 8

        MoveResult m17 = game.move(new Move(Queen.class, Color.WHITE, D, _1, G, _4, now()));
        MoveResult m18 = game.move(new Move(Bishop.class, Color.BLACK, B, _4, E, _7, now())); // 9

        MoveResult m19 = game.move(new Move(Pawn.class, Color.WHITE, G, _2, G, _3, now()));
        MoveResult m20 = game.move(new Move(Pawn.class, Color.BLACK, C, _7, C, _5, now())); // 10

        MoveResult m21 = game.move(new Move(Pawn.class, Color.WHITE, G, _3, H, _4, now()));
        MoveResult m22 = game.move(new Move(Pawn.class, Color.BLACK, C, _5, D, _4, now())); // 11


        MoveResult m23 = game.move(new Move(Pawn.class, Color.WHITE, H, _4, H, _5, now()));
        MoveResult m24 = game.move(new Move(Pawn.class, Color.BLACK, D, _4, C, _3, now())); // 12


        MoveResult m25 = game.move(new Move(Pawn.class, Color.WHITE, H, _5, H, _6, now()));
        MoveResult m26 = game.move(new Move(Pawn.class, Color.BLACK, C, _3, B, _2, now())); // 13

        MoveResult m27 = game.move(new Move(Rook.class, Color.WHITE, A, _1, B, _1, now()));
        MoveResult m28 = game.move(new Move(Queen.class, Color.BLACK, D, _8, A, _5, now())); // 14

        MoveResult m29 = game.move(new Move(King.class, Color.WHITE, E, _1, E, _2, now()));
        MoveResult m30 = game.move(new Move(Queen.class, Color.BLACK, A, _5, A, _2, now())); // 15

        MoveResult m31 = game.move(new Move(Pawn.class, Color.WHITE, H, _6, H, _7, now()));
        MoveResult m32 = game.move(new Move(Queen.class, Color.BLACK, A, _2, B, _1, now())); // 16

        MoveResult m33 = game.move(new Move(Pawn.class, Color.WHITE, H, _7, G, _8, now(), Queen.class));
        MoveResult m34 = game.move(new Move(King.class, Color.BLACK, E, _8, D, _7, now()));              // 17

        MoveResult m35 = game.move(new Move(Queen.class, Color.WHITE, G, _8, F, _7, now()));
        MoveResult m36 = game.move(new Move(Queen.class, Color.BLACK, B, _1, C, _2, now())); // 18


        MoveResult m37 = game.move(new Move(King.class, Color.WHITE, E, _2, F, _3, now()));
        MoveResult m38 = game.move(new Move(Knight.class, Color.BLACK, B, _8, C, _6, now())); // 19

        MoveResult m39 = game.move(new Move(Queen.class, Color.WHITE, G, _4, E, _6, now()));
        MoveResult m40 = game.move(new Move(King.class, Color.BLACK, D, _7, C, _7, now())); // 20

        MoveResult m41 = game.move(new Move(Queen.class, Color.WHITE, F, _7, F, _4, now()));
        MoveResult m42 = game.move(new Move(King.class, Color.BLACK, C, _7, B, _6, now())); // 21


        MoveResult m43 = game.move(new Move(Queen.class, Color.WHITE, E, _6, E, _3, now()));
        MoveResult m44 = game.move(new Move(Bishop.class, Color.BLACK, E, _7, C, _5, now())); // 22

        MoveResult m45 = game.move(new Move(Pawn.class, Color.WHITE, G, _7, G, _8, now(), Queen.class));
        MoveResult m46 = game.move(new Move(Pawn.class, Color.BLACK, B, _2, B, _1, now(), Queen.class)); // 23

        MoveResult m47 = game.move(new Move(Rook.class, Color.WHITE, H, _1, H, _6, now()));
        MoveResult m48 = game.move(new Move(Queen.class, Color.BLACK, B, _1, F, _1, now())); // 24

        MoveResult m49 = game.move(new Move(Queen.class, Color.WHITE, F, _4, B, _4, now()));
        MoveResult m50 = game.move(new Move(Queen.class, Color.BLACK, F, _1, B, _5, now())); // 25

        MoveResult m51 = game.move(new Move(Queen.class, Color.WHITE, G, _8, D, _8, now()));
        MoveResult m52 = game.move(new Move(King.class, Color.BLACK, B, _6, A, _6, now())); // 26

        MoveResult m53 = game.move(new Move(Queen.class, Color.WHITE, E, _3, A, _3, now()));
        MoveResult m54 = game.move(new Move(Queen.class, Color.BLACK, C, _2, A, _4, now())); // 27

        MoveResult m55 = game.move(new Move(Queen.class, Color.WHITE, A, _3, A, _4, now()));
        MoveResult m56 = game.move(new Move(Queen.class, Color.BLACK, B, _5, A, _4, now())); // 28

        MoveResult m57 = game.move(new Move(Queen.class, Color.WHITE, B, _4, A, _4, now())); // 29 # 1-0


        assertNotEquals(BAD_MOVE, m1);
        assertNotEquals(BAD_MOVE, m2);
        assertNotEquals(BAD_MOVE, m3);
        assertNotEquals(BAD_MOVE, m4);
        assertNotEquals(BAD_MOVE, m5);
        assertNotEquals(BAD_MOVE, m6);
        assertNotEquals(BAD_MOVE, m7);
        assertNotEquals(BAD_MOVE, m8);
        assertNotEquals(BAD_MOVE, m9);
// Продовжуємо збільшувати m
        assertNotEquals(BAD_MOVE, m10);
        assertNotEquals(BAD_MOVE, m11);
        assertNotEquals(BAD_MOVE, m12);
        assertNotEquals(BAD_MOVE, m13);
        assertNotEquals(BAD_MOVE, m14);
        assertNotEquals(BAD_MOVE, m15);
        assertNotEquals(BAD_MOVE, m16);
        assertNotEquals(BAD_MOVE, m17);
        assertNotEquals(BAD_MOVE, m18);
        assertNotEquals(BAD_MOVE, m19);
        assertNotEquals(BAD_MOVE, m20);
        assertNotEquals(BAD_MOVE, m21);
        assertNotEquals(BAD_MOVE, m22);
        assertNotEquals(BAD_MOVE, m23);
        assertNotEquals(BAD_MOVE, m24);
        assertNotEquals(BAD_MOVE, m25);
        assertNotEquals(BAD_MOVE, m26);
        assertNotEquals(BAD_MOVE, m27);
        assertNotEquals(BAD_MOVE, m28);
        assertNotEquals(BAD_MOVE, m29);
        assertNotEquals(BAD_MOVE, m30);
        assertNotEquals(BAD_MOVE, m31);
        assertNotEquals(BAD_MOVE, m32);
        assertNotEquals(BAD_MOVE, m33);
        assertNotEquals(BAD_MOVE, m34);
        assertNotEquals(BAD_MOVE, m35);
        assertNotEquals(BAD_MOVE, m36);
        assertNotEquals(BAD_MOVE, m37);
        assertNotEquals(BAD_MOVE, m38);
        assertNotEquals(BAD_MOVE, m39);
        assertNotEquals(BAD_MOVE, m40);
        assertNotEquals(BAD_MOVE, m41);
        assertNotEquals(BAD_MOVE, m42);
        assertNotEquals(BAD_MOVE, m43);
        assertNotEquals(BAD_MOVE, m44);
        assertNotEquals(BAD_MOVE, m45);
        assertNotEquals(BAD_MOVE, m46);
        assertNotEquals(BAD_MOVE, m47);
        assertNotEquals(BAD_MOVE, m48);
        assertNotEquals(BAD_MOVE, m49);
        assertNotEquals(BAD_MOVE, m50);
        assertNotEquals(BAD_MOVE, m51);
        assertNotEquals(BAD_MOVE, m52);
        assertNotEquals(BAD_MOVE, m53);
        assertNotEquals(BAD_MOVE, m54);
        assertNotEquals(BAD_MOVE, m55);
        assertNotEquals(BAD_MOVE, m56);
        assertNotEquals(BAD_MOVE, m57);
    }

}
