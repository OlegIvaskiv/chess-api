package com.example.chessgame.game.pieces;

import com.example.chessgame.game.Board;
import com.example.chessgame.game.util.Color;
import com.example.chessgame.game.util.Move;
import com.example.chessgame.game.util.MoveResult;
import com.example.chessgame.game.util.Point;
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
        calculateAllLegalSquares();
        if (isCheckForUs()) {
            filterSafeMoves();
        }
        if (move == null || move.getPieceType() != this.getClass()) return false;
        return legalSquares.stream().anyMatch(p -> p.x == move.getTo().x && p.y == move.getTo().y);
    }

    public void filterSafeMoves() {
        List<Point> temp = new ArrayList<>(legalSquares);
        legalSquares.clear();
        legalSquares.addAll(new ArrayList<>(temp.stream()
                                                .filter(this::isPreventCheck).toList()));
    }

    public boolean isCheckForEnemy() {
        King king = getEnemyKing();
        calculateAllLegalSquares();
        return legalSquares.stream()
                           .distinct()
                           .anyMatch(p -> p.x == king.xp && p.y == king.yp);
    }

    public boolean isCheckMateForEnemy() {
        List<Point> squares = getAllEnemyLegalSquares(true).stream().distinct().toList();
        return isCheckForEnemy() && squares.isEmpty();
    }

    public boolean isDraw() {
        List<Piece> ourPieces = board.getAllPieces().stream().filter(p -> p.color == this.color).toList();
        List<Piece> enemyPieces = board.getAllPieces().stream().filter(p -> p.color != this.color).toList();

        if (ourPieces.size() <= 2 && enemyPieces.size() <= 2) {
            if (ourPieces.size() == 0 && enemyPieces.size() == 0) return true;
            if (lightPieceAndKing(enemyPieces) && lightPieceAndKing(ourPieces)) {
                return true;
            }
        }


        List<Point> squares = getAllEnemyLegalSquares(true).stream().distinct().toList();
        return !isCheckForEnemy() && squares.isEmpty();
    }

    private boolean lightPieceAndKing(List<Piece> pieces) {
        return pieces.size() == 2 && pieces.stream()
                                           .filter(p -> !p.getClass().equals(King.class))
                                           .anyMatch(p ->
                                                   p.getClass().equals(Knight.class)
                                                           || p.getClass().equals(Bishop.class));
    }


    public boolean isPined() {
        if (this instanceof King) return false;
        int prevAmount = amountOfChecks();
        board.removePiece(this);
        int currAmount = amountOfChecks();
        board.addPiece(this);
        return prevAmount < currAmount;
    }

    public int amountOfChecks() {
        King king = getKing(color);
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

    private King getKing(Color color) {
        King king = null;
        for (Piece piece : board.getAllPieces()) {
            if (piece instanceof King && piece.getColor() == color) {
                king = (King) piece;
            }
        }
        return king;
    }

    private boolean theSamePosition(Move move) {
        return move.getFrom().equals(move.getTo());
    }

    public MoveResult move(Move move) {
        int xp = move.getTo().x;
        int yp = move.getTo().y;
        isLegalMove(move);
        if (!isPined() && isLegalMove(move) && !theSamePosition(move) && this.color == board.moveFor()) {
            if (getPiece(xp, yp) != null) {
                if (getPiece(xp, yp).color != color) {
                    getPiece(xp, yp).kill();
                }
            }
            board.addMove(move);
            this.xp = xp;
            this.yp = yp;
            board.toggleMoveSide();
            if (isCheckMateForEnemy()) {
                move.setMoveResult(MoveResult.CHECK_MATE);
                return MoveResult.CHECK_MATE;
            } else if (isCheckForEnemy()) {
                move.setMoveResult(MoveResult.CHECK);
                return MoveResult.CHECK;
            } else if (isDraw()) {
                move.setMoveResult(MoveResult.DRAW);
                return MoveResult.DRAW;
            }

            move.setMoveResult(MoveResult.MADE);
            return MoveResult.MADE;
        }
        move.setMoveResult(MoveResult.BAD_MOVE);
        return MoveResult.BAD_MOVE;
    }

    Piece getPiece(int x, int y) {
        for (Piece p : board.getAllPieces()) {
            if (p.xp == x && p.yp == y) {
                return p;
            }
        }
        return null;
    }

    public List<Point> getAllEnemyLegalSquares(boolean check) {
        List<Point> squares = new ArrayList<>();
        List<Piece> pieces = board.getAllPieces().stream().filter(p -> p.color != this.color).toList();
        for (Piece p : pieces) {
            p.calculateAllLegalSquares();
            if (check) {
                p.filterSafeMoves();
            }
            squares.addAll(p.legalSquares);
        }
        return squares;
    }

    public boolean isPreventCheck(Point point) {
        if (this.getClass() == King.class || point == null) return false;
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
        King king = getKing(color);
        List<Point> squares = getAllEnemyLegalSquares(false);
        return squares.stream()
                      .distinct()
                      .anyMatch(p -> p.x == king.xp && p.y == king.yp);
    }

    public King getEnemyKing() {
        Color color = this.color == Color.WHITE ? Color.BLACK : Color.WHITE;
        return getKing(color);
    }

    public void kill() {
        board.removePiece(this);
    }

}
