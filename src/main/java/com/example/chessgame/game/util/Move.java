package com.example.chessgame.game.util;

import com.example.chessgame.game.pieces.Piece;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Move {
    private final Instant time;
    private final Class<? extends Piece> pieceType;
    private final Point from;
    private final Point to;
    private final Color color;

    public Move(Class<? extends Piece> piece, Color color, File fileFrom, Row rowFrom,
                File fileTo, Row rowTo, Instant time) {
        this.time = time;
        this.color = color;
        this.from = new Point(fileFrom, rowFrom);
        this.to = new Point(fileTo, rowTo);
        this.pieceType = piece;
    }

}
