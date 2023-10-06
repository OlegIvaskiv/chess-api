package com.example.chessgame.validation.peaces;

import com.example.chessgame.validation.Board;
import com.example.chessgame.validation.util.Color;
import com.example.chessgame.validation.util.Move;
import com.example.chessgame.validation.util.Point;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Piece {
    protected final Color color;
    public int xp;
    public int yp;
    protected List<Point> legalSquares = new ArrayList<>();
    public final Board board;

    public Piece(int xp, int yp, Color color, Board gameInfo) {
        this.color = color;
        this.xp = xp;
        this.yp = yp;
        this.board = gameInfo;
    }

    public abstract void calculateAllLegalSquares();

    private boolean isLegalMove(Move move) {
        if (move == null || move.getPieceType() != this.getClass()) return false;
        return legalSquares.stream().anyMatch(p -> p.x == xp && p.y == yp);
    }

    public void filterSafeMoves() {
        legalSquares = new ArrayList<>(legalSquares.stream()
                                                   .filter(this::isPreventCheck).toList());
    }

    private boolean isPreventCheck(Point point) {
        int tempX = this.xp;
        int tempY = this.yp;
        this.xp = point.x;
        this.yp = point.y;
        boolean isCheck = !isCheckForUs();
        this.xp = tempX;
        this.yp = tempY;
        return isCheck;
    }

    public boolean isCheckForUs() {
        return amountOfChecks() > 0;
    }

    public boolean isPined() {
        if (this instanceof King) return false;
        int prevAmount = amountOfChecks();
        board.removePiece(this);
        int currAmount = amountOfChecks();
        board.addPiece(this);
        return prevAmount != currAmount;
    }

    public int amountOfChecks() {
        King king = getKing();
        List<Piece> opponentsPieces = new ArrayList<>();
        List<Point> legalSquares = new ArrayList<>();
        board.getAllPieces().stream().filter(p -> p.getColor() != color).forEach(opponentsPieces::add);
        opponentsPieces.forEach(p -> {
            p.calculateAllLegalSquares();
            var temp = p.getLegalSquares().stream()
                        .map(point -> new Point(point.x, point.y)).toList();
            legalSquares.addAll(temp);
        });
        return (int) legalSquares.stream()
                                 .filter(p -> p.x == king.xp && p.y == king.yp).count();
    }

    private King getKing() {
        King king = null;
        for (Piece piece : board.getAllPieces()) {
            if (piece instanceof King && piece.getColor() == color) {
                king = (King) piece;
            }
        }
        return king;
    }


    private boolean theSamePosition(int x, int y) {
        return this.xp == x && this.yp == y;
    }

    public boolean move(Move move) {
        int xp = move.getTo().x;
        int yp = move.getTo().y;
        if (isLegalMove(move) && !theSamePosition(xp, yp) && this.color == board.moveFor()) {
            if (getPiece(xp, yp) != null) {
                if (getPiece(xp, yp).color != color) {
                    getPiece(xp, yp).kill();
                }
            }
            board.addMove(new Move(this.getClass(), new Point(this.xp, this.yp), new Point(xp, yp)));
            this.xp = xp;
            this.yp = yp;
            legalSquares.clear();
            board.toggleMove();
            return true;
        }
        return false;
    }

    Piece getPiece(int x, int y) {
        for (Piece p : board.getAllPieces()) {
            if (p.xp == x && p.yp == y) {
                return p;
            }
        }
        return null;
    }

    public void kill() {
        board.removePiece(this);
    }

}
