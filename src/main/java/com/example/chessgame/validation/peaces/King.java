package com.example.chessgame.validation.peaces;

import com.example.chessgame.validation.Board;
import com.example.chessgame.validation.util.Color;
import com.example.chessgame.validation.util.Point;

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

        List<Point> enemySquares = getAllNeighboursSquares(xp, yp)
                .stream()
                .filter(p -> getPiece(p.x, p.y) != null && getPiece(p.x, p.y).color != color).toList();

        List<Point> squares = new ArrayList<>();

        squares.addAll(emptySquares);
        squares.addAll(enemySquares);
        squares = squares.stream()
                         .filter(p -> !isNearEnemyKing(p.x, p.y))
                         .map(p -> new Point(p.x * 70 + 23, p.y * 70 + 23)).toList();
        legalSquares.addAll(squares);
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
