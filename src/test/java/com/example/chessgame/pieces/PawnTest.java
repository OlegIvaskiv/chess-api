package com.example.chessgame.pieces;

import com.example.chessgame.game.Game;
import com.example.chessgame.game.pieces.Pawn;
import com.example.chessgame.game.util.Color;
import com.example.chessgame.game.util.Move;
import org.junit.jupiter.api.Test;

import static com.example.chessgame.game.util.File.D;
import static com.example.chessgame.game.util.File.E;
import static com.example.chessgame.game.util.Row.*;
import static java.time.Instant.now;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PawnTest {

    @Test
    public void twoSquareMove() {
        Game game = new Game();
        boolean whitePawnMove = game.move(new Move(Pawn.class, Color.WHITE, E, _2
                , E, _4, now()));

        boolean blackPawnMove = game.move(new Move(Pawn.class, Color.WHITE, D, _7
                , D, _5, now()));

        assertTrue(whitePawnMove);
        assertTrue(blackPawnMove);
    }
}
