package com.example.chessgame.validation.peaces;

import com.example.chessgame.validation.Board;
import com.example.chessgame.validation.util.Color;
import com.example.chessgame.validation.util.Point;

public class Rook extends Piece {
    public Rook(int file, int row, Color color, Board board) {
        super(file, row, color, board);
    }

    @Override
    public void calculateAllLegalSquares() {
        legalSquares.clear();
        for (int i = xp; i <= 7; i++) {
            if (getPiece(i + 1, yp) == null) {
                legalSquares.add(new Point((i + 1), yp));
            } else {
                if (getPiece(i + 1, yp).color != color) {
                    legalSquares.add(new Point((i + 1), yp));
                }
                break;
            }
        }
        for (int i = xp; i >= 0; i--) {
            if (getPiece(i - 1, yp) == null) {
                legalSquares.add(new Point((i - 1), yp));
            } else {
                if (getPiece(i - 1, yp).color != color) {
                    legalSquares.add(new Point((i - 1), yp));
                }
                break;
            }
        }
        for (int i = yp; i <= 7; i++) {
            if (getPiece(xp, i + 1) == null) {
                legalSquares.add(new Point(xp, (i + 1)));
            } else {
                if (getPiece(xp, i + 1).color != color) {
                    legalSquares.add(new Point(xp, (i + 1)));
                }
                break;
            }
        }
        for (int i = yp; i >= 0; i--) {
            if (getPiece(xp, i - 1) == null) {
                legalSquares.add(new Point(xp, (i - 1)));
            } else {
                if (getPiece(xp, i - 1).color != color) {
                    legalSquares.add(new Point(xp, (i - 1)));
                }
                break;
            }
        }
    }

}
