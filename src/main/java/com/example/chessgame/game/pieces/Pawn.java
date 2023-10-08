package com.example.chessgame.game.pieces;

import com.example.chessgame.game.Board;
import com.example.chessgame.game.util.Color;
import com.example.chessgame.game.util.Move;
import com.example.chessgame.game.util.Point;

public class Pawn extends Piece {
    public Pawn(int file, int row, Color color, Board board) {
        super(file, row, color, board);
    }

    @Override
    public void calculateAllLegalSquares() {
        legalSquares.clear();
        enPassantCheck();
        promotingCheck();
        if (color == Color.WHITE) {
            if (yp == 6) {
                if (getPiece(xp, yp - 2) == null) {
                    legalSquares.add(new Point(xp, yp - 2));
                }
            }
            if (getPiece(xp, yp - 1) == null) {
                legalSquares.add(new Point(xp, (yp - 1)));
            }
            if (getPiece(xp + 1, yp - 1) != null) {
                if (getPiece(xp + 1, yp - 1).color != color) {
                    legalSquares.add(new Point((xp + 1), (yp - 1)));
                }
            }
            if (getPiece(xp - 1, yp - 1) != null) {
                if (getPiece(xp - 1, yp - 1).color != color) {
                    legalSquares.add(new Point((xp - 1), (yp - 1)));
                }
            }
        } else {
            if (yp == 1) {
                if (getPiece(xp, yp + 2) == null) {
                    legalSquares.add(new Point(xp, (yp + 2)));
                }
            }
            if (getPiece(xp, yp + 1) == null) {
                legalSquares.add(new Point(xp, (yp + 1)));
            }
            if (getPiece(xp - 1, yp + 1) != null) {
                if (getPiece(xp - 1, yp + 1).color != color) {
                    legalSquares.add(new Point((xp - 1), (yp + 1)));
                }
            }
            if (getPiece(xp + 1, yp + 1) != null) {
                if (getPiece(xp + 1, yp + 1).color != color) {
                    legalSquares.add(new Point((xp + 1), (yp + 1)));
                }
            }
        }
    }

    private void promotingCheck() {
        if (color == Color.WHITE && yp == 1 && getPiece(xp, 0) == null) {
            legalSquares.add(new Point(xp, 0));
        } else if (color == Color.BLACK && yp == 7 && getPiece(xp, 7) == null) {
            legalSquares.add(new Point(xp, 7));
        }
    }


    private void enPassantCheck() {
        Move prevMove = board.getLastMove();
        if (prevMove == null) return;
        if (color == Color.BLACK && yp == 4) {
            if (prevMove.getPieceType() == Pawn.class &&
                    prevMove.getFrom().x == xp - 1
                    && prevMove.getFrom().y == 6 && prevMove.getTo().y == 4) {
                legalSquares.add(new Point((xp - 1), 5));
            }
            if (prevMove.getPieceType() == Pawn.class &&
                    prevMove.getFrom().x == xp + 1
                    && prevMove.getFrom().y == 6 && prevMove.getTo().y == 4) {
                legalSquares.add(new Point((xp + 1), 5));
            }
        } else if (color == Color.WHITE && yp == 3) {
            if (prevMove.getPieceType() == Pawn.class &&
                    prevMove.getFrom().x == xp - 1
                    && prevMove.getFrom().y == 1
                    && prevMove.getTo().y == 3) {
                legalSquares.add(new Point((xp - 1), 2));
            }
            if (prevMove.getPieceType() == Pawn.class &&
                    prevMove.getFrom().x == xp + 1
                    && prevMove.getFrom().y == 1 && prevMove.getTo().y == 3) {
                legalSquares.add(new Point((xp + 1), 2));
            }
        }
    }

}
