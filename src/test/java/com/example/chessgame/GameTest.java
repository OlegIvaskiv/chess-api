package com.example.chessgame;

import com.example.chessgame.game.Game;
import com.example.chessgame.game.pieces.King;
import com.example.chessgame.game.pieces.Pawn;
import com.example.chessgame.game.util.Color;
import com.example.chessgame.game.util.Move;
import org.junit.jupiter.api.Test;

import static com.example.chessgame.game.util.File.C;
import static com.example.chessgame.game.util.File.E;
import static com.example.chessgame.game.util.Row.*;
import static java.time.Instant.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    private Game game = new Game();

    @Test
    public void pawnLegalMove() {
        boolean whitePawnMove = game.move(new Move(Pawn.class, Color.WHITE, E, _2,
                E, _4, now()));
        assertTrue(whitePawnMove);
        assertEquals(1, game.getGameMoves().size());
    }

    @Test
    public void longCastling() {
        game = new Game("8/8/8/8/8/8/8/R3K3", Color.WHITE);
        boolean kingMove = game.move(new Move(King.class, Color.WHITE, E, _1,
                C, _1, now()));
        assertTrue(kingMove);
    }

    @Test
    public void longCastlingWithFullPieces() {
        game = new Game("rnb1kbnr/ppp2ppp/3p1q2/4p3/4P3/2NP4/PPPQ1PPP/R3KBNR", Color.WHITE);
        boolean kingMove = game.move(new Move(King.class, Color.WHITE, E, _1,
                C, _1, now()));
        assertTrue(kingMove);
    }

}
