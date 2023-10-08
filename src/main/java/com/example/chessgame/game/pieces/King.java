package com.example.chessgame.game.pieces;

import com.example.chessgame.game.Board;
import com.example.chessgame.game.util.Color;
import com.example.chessgame.game.util.Point;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(int file, int row, Color color, Board board) {
        super(file, row, color, board);
    }

    @Override
    public void calculateAllLegalSquares() {
        legalSquares.clear();
        List<Point> emptySquares = getAllNeighboursSquares(xp, yp)
                .stream()
                .filter(p -> getPiece(p.x, p.y) == null).toList();
        if (board.moveFor() != color) {
            legalSquares.addAll(emptySquares);
            return;
        }

        List<Point> captures = getAllNeighboursSquares(xp, yp)
                .stream()
                .filter(p -> getPiece(p.x, p.y) != null
                        && getPiece(p.x, p.y).color != color).toList();

        List<Point> squares = new ArrayList<>();
        squares.addAll(emptySquares);
        squares.addAll(captures);
        List<Point> filtered = new ArrayList<>();

        for (Point p : squares) {
            if (!isNearEnemyKing(p.x, p.y) && !isCovered(p)) {
                filtered.add(p);
            }
        }

        if (this.color == Color.WHITE) {
            if (castleLongPossible()) {
                legalSquares.add(new Point(2, 7));
            }
            if (castleShortPossible()) {
                legalSquares.add(new Point(6, 7));
            }
        } else {
            if (castleLongPossible()) {
                legalSquares.add(new Point(2, 0));
            }
            if (castleShortPossible()) {
                legalSquares.add(new Point(6, 0));
            }
        }

        legalSquares.addAll(filtered);
    }


    public boolean isCovered(Point point) {
        List<Point> points = getAllEnemyLegalSquares(false);
        Piece piece = getPiece(point.x, point.y);
        if (piece == null || piece.color != this.color) {
            int tempX = xp;
            int tempY = yp;
            xp = point.x;
            yp = point.y;
            points = getAllEnemyLegalSquares(false);
            xp = tempX;
            yp = tempY;
        }
        return points.contains(point);
    }

    private boolean castleLongPossible() {
        return !kingMoved()
                && castleLongAreaEmpty()
                && !longSideRockMoved()
                && !castleLongUnderAttack();
    }

    private boolean castleShortPossible() {
        return !kingMoved()
                && castleShortAreaEmpty()
                && !shortSideRockMoved()
                && !castleShortUnderAttack();
    }

    private boolean castleLongAreaEmpty() {
        if (this.color == Color.WHITE) {
            return getPiece(1, 7) == null &&
                    getPiece(2, 7) == null &&
                    getPiece(3, 7) == null;
        } else {
            return getPiece(1, 0) == null &&
                    getPiece(2, 0) == null &&
                    getPiece(3, 0) == null;
        }
    }

    private boolean castleShortAreaEmpty() {
        if (this.color == Color.WHITE) {
            return getPiece(5, 7) == null &&
                    getPiece(6, 7) == null;

        } else {
            return getPiece(5, 0) == null &&
                    getPiece(6, 0) == null;
        }
    }

    private boolean longSideRockMoved() {
        if (this.color == Color.WHITE) {
            Piece piece = getPiece(0, 7);
            return board.getAllMoves().stream()
                        .anyMatch(m -> m.getFrom().equals(new Point(0, 7)))
                    || piece == null;
        } else {
            Piece piece = getPiece(0, 0);
            return board.getAllMoves().stream()
                        .anyMatch(m -> m.getFrom().equals(new Point(0, 0)))
                    || piece == null;
        }
    }

    private boolean shortSideRockMoved() {
        if (this.color == Color.WHITE) {
            Piece piece = getPiece(7, 7);
            return board.getAllMoves().stream()
                        .anyMatch(m -> m.getFrom().equals(new Point(7, 7)))
                    || piece == null;
        } else {
            Piece piece = getPiece(7, 0);
            return board.getAllMoves().stream()
                        .anyMatch(m -> m.getFrom().equals(new Point(7, 0)))
                    || piece == null;
        }
    }

    private boolean castleLongUnderAttack() {
        if (this.color == Color.WHITE) {
            Point point1 = new Point(2, 7);
            Point point2 = new Point(3, 7);
            List<Point> squares = getAllEnemyLegalSquares(false);
            return squares.contains(point1) || squares.contains(point2);
        } else {
            Point point1 = new Point(2, 0);
            Point point2 = new Point(3, 0);
            List<Point> squares = getAllEnemyLegalSquares(false);
            return squares.contains(point1) || squares.contains(point2);
        }
    }

    private boolean castleShortUnderAttack() {
        if (this.color == Color.WHITE) {
            Point point1 = new Point(5, 7);
            Point point2 = new Point(6, 7);
            List<Point> squares = getAllEnemyLegalSquares(false);
            return squares.contains(point1) || squares.contains(point2);
        } else {
            Point point1 = new Point(5, 7);
            Point point2 = new Point(6, 7);
            List<Point> squares = getAllEnemyLegalSquares(false);
            return squares.contains(point1) || squares.contains(point2);
        }
    }


    private boolean kingMoved() {
        return board.getAllMoves().stream()
                    .anyMatch(m -> m.getPieceType().equals(King.class)
                            && m.getColor() == this.color);
    }

    private boolean isNearEnemyKing(int x, int y) {
        return getAllNeighboursSquares(x, y).stream()
                                            .anyMatch(p -> getPiece(p.x, p.y) instanceof King
                                                    && getPiece(p.x, p.y).color != color);
    }

    private List<Point> getAllNeighboursSquares(int x, int y) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(x + 1, y));
        points.add(new Point(x - 1, y));
        points.add(new Point(x, y + 1));
        points.add(new Point(x, y - 1));
        points.add(new Point(x + 1, y - 1));
        points.add(new Point(x - 1, y + 1));
        points.add(new Point(x + 1, y + 1));
        points.add(new Point(x - 1, y - 1));
        return points.stream()
                     .filter(p -> p.x >= 0 && p.x <= 7)
                     .filter(p -> p.y >= 0 && p.y <= 7).toList();
    }
}
