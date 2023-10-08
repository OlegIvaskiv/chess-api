package com.example.chessgame.game;

import com.example.chessgame.game.pieces.*;
import com.example.chessgame.game.util.Color;
import com.example.chessgame.game.util.Move;

public class MoveParser {
    private String pattern;
    private String pawnPatern;
    private String captPattern;

    public String parseMove(int i, Move move) {
        pattern = move.getColor() == Color.WHITE ? "%d. %s%d " : "%s%d\n";
        pawnPatern = move.getColor() == Color.WHITE ? "%d. %s%d%s " : "%s%d%s\n";
        captPattern = move.getColor() == Color.WHITE ? "%d. %sx%s%d " : "%sx%s%d\n";
        Class<? extends Piece> type = move.getPieceType();

        if (type.equals(King.class)) {
            return kingMove(i, move);
        } else if (type.equals(Queen.class)) {
            return queenMove(i, move);
        } else if (type.equals(Rook.class)) {
            return rookMove(i, move);
        } else if (type.equals(Bishop.class)) {
            return bishopMove(i, move);
        } else if (type.equals(Knight.class)) {
            return knightMove(i, move);
        } else if (type.equals(Pawn.class)) {
            return pawnMove(i, move);
        }
        return "";
    }

    private String toLetter(int x) {
        String letter = "";
        switch (x) {
            case 0 -> letter = "a";
            case 1 -> letter = "b";
            case 2 -> letter = "c";
            case 3 -> letter = "d";
            case 4 -> letter = "e";
            case 5 -> letter = "f";
            case 6 -> letter = "g";
            case 7 -> letter = "h";
        }
        return letter;
    }

    private int reverse(int n) {
        return 8 - n;
    }

    private String kingMove(int i, Move move) {
        String result = "";
        if (move.isCapture()) {
            if (move.getColor() == Color.WHITE) {
                result += String.format(captPattern, i, "K", toLetter(move.getTo().x), reverse(move.getTo().y));
            } else {
                result += String.format(captPattern, toLetter(move.getTo().x), reverse(move.getTo().y));
                result += String.format(captPattern, "K", toLetter(move.getTo().x), reverse(move.getTo().y));
            }
        } else {
            if (move.getColor() == Color.WHITE) {
                result += String.format(pattern, i, "K" + toLetter(move.getTo().x), reverse(move.getTo().y));
            } else {
                result += String.format(pattern, "K" + toLetter(move.getTo().x), reverse(move.getTo().y));
            }
        }
        return result;
    }

    private String queenMove(int i, Move move) {
        String result = "";
        if (move.isCapture()) {
            if (move.getColor() == Color.WHITE) {
                result += String.format(captPattern, i, "Q", toLetter(move.getTo().x), reverse(move.getTo().y));
            } else {
                result += String.format(captPattern, "Q", toLetter(move.getTo().x), reverse(move.getTo().y));
            }
        } else {
            if (move.getColor() == Color.WHITE) {
                result += String.format(pattern, i, "Q" + toLetter(move.getTo().x), reverse(move.getTo().y));
            } else {
                result += String.format(pattern, "Q" + toLetter(move.getTo().x), reverse(move.getTo().y));
            }
        }
        return result;
    }

    private String rookMove(int i, Move move) {
        String result = "";
        if (move.isCapture()) {
            if (move.getColor() == Color.WHITE) {
                result += String.format(captPattern, i, "R", toLetter(move.getTo().x), reverse(move.getTo().y));
            } else {
                result += String.format(captPattern, toLetter(move.getTo().x), reverse(move.getTo().y));
                result += String.format(captPattern, "R", toLetter(move.getTo().x), reverse(move.getTo().y));
            }
        } else {
            if (move.getColor() == Color.WHITE) {
                result += String.format(pattern, i, "R" + toLetter(move.getTo().x), reverse(move.getTo().y));
            } else {
                result += String.format(pattern, "R" + toLetter(move.getTo().x), reverse(move.getTo().y));
            }
        }
        return result;
    }

    private String knightMove(int i, Move move) {
        String result = "";
        if (move.isCapture()) {
            if (move.getColor() == Color.WHITE) {
                result += String.format(captPattern, i, "N", toLetter(move.getTo().x), reverse(move.getTo().y));
            } else {
                result += String.format(captPattern, toLetter(move.getTo().x), reverse(move.getTo().y));
                result += String.format(captPattern, "N", toLetter(move.getTo().x), reverse(move.getTo().y));
            }
        } else {
            if (move.getColor() == Color.WHITE) {
                result += String.format(pattern, i, "N" + toLetter(move.getTo().x), reverse(move.getTo().y));
            } else {
                result += String.format(pattern, "N" + toLetter(move.getTo().x), reverse(move.getTo().y));
            }
        }
        return result;
    }

    private String bishopMove(int i, Move move) {
        String result = "";
        if (move.isCapture()) {
            if (move.getColor() == Color.WHITE) {
                result += String.format(captPattern, i, "B", toLetter(move.getTo().x), reverse(move.getTo().y));
            } else {
                result += String.format(captPattern, toLetter(move.getTo().x), reverse(move.getTo().y));
                result += String.format(captPattern, "B", toLetter(move.getTo().x), reverse(move.getTo().y));
            }
        } else {
            if (move.getColor() == Color.WHITE) {
                result += String.format(pattern, i, "B" + toLetter(move.getTo().x), reverse(move.getTo().y));
            } else {
                result += String.format(pattern, "B" + toLetter(move.getTo().x), reverse(move.getTo().y));
            }
        }
        return result;
    }

    private String pawnMove(int i, Move move) {
        String result = "";
        String promoteLetter = "";
        if (move.getPromoteType() != null) {
            promoteLetter = "=" + toLetter(move.getPromoteType());
        }
        if (move.getColor() == Color.WHITE) {
            result += String.format( pawnPatern, i, toLetter(move.getTo().x), reverse(move.getTo().y) , promoteLetter);
        } else {
            result += String.format( pawnPatern, toLetter(move.getTo().x), reverse(move.getTo().y),  promoteLetter);
        }
        return result;
    }

    private String toLetter(Class<? extends PromotedPiece> type) {
        if (type.equals(Queen.class)) {
            return "Q";
        } else if (type.equals(Knight.class)) {
            return "N";
        } else if (type.equals(Bishop.class)) {
            return "B";
        } else if (type.equals(Rook.class)) {
            return "R";
        }
        return "";
    }
}
