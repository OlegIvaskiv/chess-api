package com.example.chessgame.game.pieces;

import com.example.chessgame.game.Board;
import com.example.chessgame.game.util.Color;

public class Queen extends  PromotedPiece {
    private final Bishop bishop;
    private final Rook rook;

    public Queen(int file, int row, Color color, Board board) {
        super(file, row, color, board);
        bishop = new Bishop(file, row, color, board);
        rook = new Rook(file, row, color, board);
    }

    @Override
    public void calculateAllLegalSquares() {
        legalSquares.clear();
        bishop.xp = xp;
        bishop.yp = yp;
        rook.xp = xp;
        rook.yp = yp;
        bishop.calculateAllLegalSquares();
        rook.calculateAllLegalSquares();
        legalSquares.addAll(bishop.legalSquares);
        legalSquares.addAll(rook.legalSquares);
    }


}
