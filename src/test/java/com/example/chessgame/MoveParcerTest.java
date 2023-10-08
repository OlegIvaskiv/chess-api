package com.example.chessgame;

import com.example.chessgame.game.MoveParser;
import com.example.chessgame.game.pieces.Bishop;
import com.example.chessgame.game.pieces.King;
import com.example.chessgame.game.pieces.Pawn;
import com.example.chessgame.game.util.Color;
import com.example.chessgame.game.util.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.example.chessgame.game.util.File.*;
import static com.example.chessgame.game.util.Row.*;
import static java.time.Instant.now;

public class MoveParcerTest {
    @Test
    public void parse() {
        MoveParser moveParser = new MoveParser();
        String s1 = moveParser.parseMove(1, new Move(Pawn.class, Color.WHITE, G, _2,
                G, _4, now()));

        String s2 = moveParser.parseMove(1, new Move(Pawn.class, Color.BLACK, E, _7,
                E, _5, now()));

        Assertions.assertEquals("1. g4 ", s1);
        Assertions.assertEquals("1. g4 e5\n", s1 + s2);
    }

    @Test
    public void parseKingMoveCapt() {
        MoveParser moveParser = new MoveParser();
        Move move = new Move(King.class, Color.WHITE, G, _2, F, _3, now());
        move.setCapture();
        String s1 = moveParser.parseMove(39, move);

        Assertions.assertEquals("39. Kxf3 ", s1);
    }

    @Test
    public void parseKingMove() {
        MoveParser moveParser = new MoveParser();
        Move move = new Move(King.class, Color.WHITE, G, _2, F, _3, now());
        String s1 = moveParser.parseMove(39, move);
        Assertions.assertEquals("39. Kf3 ", s1);
    }

    @Test
    public void parseBishopMoveCapt() {
        MoveParser moveParser = new MoveParser();
        Move move = new Move(Bishop.class, Color.WHITE, G, _2, F, _3, now());
        move.setCapture();
        String s1 = moveParser.parseMove(39, move);

        Assertions.assertEquals("39. Bxf3 ", s1);
    }

    @Test
    public void parseBishopMove() {
        MoveParser moveParser = new MoveParser();
        Move move = new Move(Bishop.class, Color.WHITE, G, _2, F, _3, now());
        String s1 = moveParser.parseMove(39, move);
        Assertions.assertEquals("39. Bf3 ", s1);
    }
}
