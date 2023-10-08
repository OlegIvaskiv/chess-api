package com.example.chessgame.game.util;

import com.example.chessgame.game.pieces.Piece;
import com.example.chessgame.game.pieces.PromotedPiece;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Move {
    private boolean capture = false;
    private boolean check = false;
    private boolean checkMate = false;
    private final Instant time;
    private final Class<? extends Piece> pieceType;
    private final Point from;
    private final Point to;
    private final Color color;
    private MoveResult moveResult;
    private Class<? extends PromotedPiece> promoteType;

    public Move(Class<? extends Piece> piece, Color color, File fileFrom, Row rowFrom,
                File fileTo, Row rowTo, Instant time) {
        this.time = time;
        this.color = color;
        this.from = new Point(fileFrom, rowFrom);
        this.to = new Point(fileTo, rowTo);
        this.pieceType = piece;
    }

    public Move(Class<? extends Piece> piece, Color color, File fileFrom, Row rowFrom,
                File fileTo, Row rowTo, Instant time, Class<? extends PromotedPiece> promoteType) {
        this(piece, color, fileFrom, rowFrom, fileTo, rowTo, time);
        this.promoteType = promoteType;
    }

    public void setMoveResult(MoveResult moveResult) {
        this.moveResult = moveResult;
    }

    public void setCapture() {
        this.capture = true;
    }

    public void setCheck() {
        this.check = true;
    }

    public void setCheckMate() {
        this.checkMate = true;
    }
}
