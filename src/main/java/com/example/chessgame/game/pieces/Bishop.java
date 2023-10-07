package com.example.chessgame.game.pieces;

import com.example.chessgame.game.Board;
import com.example.chessgame.game.util.Color;
import com.example.chessgame.game.util.Point;

public class Bishop extends Piece {
    public Bishop(int file, int row, Color color, Board board) {
        super(file, row, color, board);
    }

    @Override
    public void calculateAllLegalSquares() {
        legalSquares.clear();
        int xTemp = xp;
        int yTemp = yp;
        while (xTemp < 7 && yTemp < 7) {
            xTemp++;
            yTemp++;
            if (getPiece(xTemp, yTemp) == null) {
                legalSquares.add(new Point((xTemp), (yTemp)));
            } else {
                if (getPiece(xTemp, yTemp).color != color) {
                    legalSquares.add(new Point((xTemp), (yTemp)));
                }
                break;
            }
        }
        xTemp = xp;
        yTemp = yp;
        while (xTemp < 7 && yTemp > 0) {
            xTemp++;
            yTemp--;
            if (getPiece(xTemp, yTemp) == null) {
                legalSquares.add(new Point((xTemp), (yTemp)));
            } else {
                if (getPiece(xTemp, yTemp).color != color) {
                    legalSquares.add(new Point((xTemp), (yTemp)));
                }
                break;
            }
        }
        xTemp = xp;
        yTemp = yp;
        while (yTemp < 7 && xTemp > 0) {
            xTemp--;
            yTemp++;
            if (getPiece(xTemp, yTemp) == null) {
                legalSquares.add(new Point((xTemp), (yTemp)));
            } else {
                if (getPiece(xTemp, yTemp).color != color) {
                    legalSquares.add(new Point((xTemp), (yTemp)));
                }
                break;
            }
        }
        xTemp = xp;
        yTemp = yp;
        while (xTemp > 0 && yTemp > 0) {
            xTemp--;
            yTemp--;
            if (getPiece(xTemp, yTemp) == null) {
                legalSquares.add(new Point((xTemp), (yTemp)));
            } else {
                if (getPiece(xTemp, yTemp).color != color) {
                    legalSquares.add(new Point((xTemp), (yTemp)));
                }
                break;
            }
        }
    }

}
