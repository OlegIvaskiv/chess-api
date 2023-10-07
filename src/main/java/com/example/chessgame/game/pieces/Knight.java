package com.example.chessgame.game.pieces;

import com.example.chessgame.game.Board;
import com.example.chessgame.game.util.Color;
import com.example.chessgame.game.util.Point;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(int file, int row, Color color, Board board) {
        super(file, row, color, board);
    }

    @Override
    public void calculateAllLegalSquares() {
        legalSquares.clear();
        List<Point> emptySquares = getAllPossiblePoints(xp, yp)
                .stream()
                .filter(p -> getPiece(p.x, p.y) == null)
                .map(p -> new Point(p.x, p.y)).toList();
        List<Point> enemyPices = getAllPossiblePoints(xp, yp)
                .stream()
                .filter(p -> getPiece(p.x, p.y) != null && getPiece(p.x, p.y).color != color)
                .map(p -> new Point(p.x, p.y)).toList();
        legalSquares.addAll(emptySquares);
        legalSquares.addAll(enemyPices);
    }

    private List<Point> getAllPossiblePoints(int x, int y) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(x - 2, y - 1));
        points.add(new Point(x - 1, y - 2));
        points.add(new Point(x + 1, y - 2));
        points.add(new Point(x + 2, y - 1));
        points.add(new Point(x - 2, y + 1));
        points.add(new Point(x - 1, y + 2));
        points.add(new Point(x + 1, y + 2));
        points.add(new Point(x + 2, y + 1));
        return points.stream().filter(p -> p.x >= 0 && p.x <= 7)
                     .filter(p -> p.y >= 0 && p.y <= 7)
                     .toList();
    }

}
