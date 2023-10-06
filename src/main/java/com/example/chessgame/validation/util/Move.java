package com.example.chessgame.validation.util;

import com.example.chessgame.validation.peaces.Piece;
import lombok.Getter;

@Getter
public class Move {
    private final Class<? extends Piece> pieceType;
    private final Point from;
    private final Point to;

    public Move(Class<? extends Piece> piece, Point from, Point to) {
        this.from = from;
        this.to = to;
        this.pieceType = piece;
    }

}
