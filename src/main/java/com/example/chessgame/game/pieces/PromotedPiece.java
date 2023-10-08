package com.example.chessgame.game.pieces;

import com.example.chessgame.game.Board;
import com.example.chessgame.game.util.Color;

public class PromotedPiece extends Piece {
    public PromotedPiece(int xp, int yp, Color color, Board gameInfo) {
        super(xp, yp, color, gameInfo);
    }

    @Override
    public void calculateAllLegalSquares() {
    }
}
