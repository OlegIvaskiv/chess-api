package com.example.chessgame.game;

import com.example.chessgame.game.pieces.*;
import com.example.chessgame.game.util.Color;
import com.example.chessgame.game.util.Move;

public class MoveParser {

    public String parseMove(int i, Move move) {
        StringBuilder result = new StringBuilder();
        if (move.getPieceType().equals(Pawn.class)) return pawnMove(i, move);
        String letter = toPieceLetter(move.getPieceType());
        if (move.isCapture()) {
            if (move.getColor() == Color.WHITE) {
                result.append(i)
                      .append(". ")
                      .append(letter)
                      .append("x")
                      .append(toLetter(move.getTo().x))
                      .append(reverseIndex(move.getTo().y))
                      .append(" ");
            } else {
                result.append(letter)
                      .append("x")
                      .append(toLetter(move.getTo().x))
                      .append(reverseIndex(move.getTo().y))
                      .append("\n");
            }
        } else {
            if (move.getColor() == Color.WHITE) {
                result.append(i)
                      .append(". ")
                      .append(letter)
                      .append(toLetter(move.getTo().x))
                      .append(reverseIndex(move.getTo().y))
                      .append(" ");
            } else {
                result.append(letter)
                      .append(toLetter(move.getTo().x))
                      .append(reverseIndex(move.getTo().y))
                      .append("\n");
            }
        }
        return result.toString();
    }


    private String pawnMove(int i, Move move) {
        StringBuilder result = new StringBuilder();
        String promoteLetter = "";
        if (move.getPromoteType() != null) {
            promoteLetter = "=" + toPieceLetter(move.getPromoteType());
        }
        if (move.isCapture()) {
            String row = toLetter(move.getFrom().x);
            if (move.getColor() == Color.WHITE) {
                result.append(i)
                      .append(". ").append(row).append("x").append(toLetter(move.getTo().x))
                      .append(reverseIndex(move.getTo().y)).append(promoteLetter).append(" ");
            } else {
                result.append(row).append("x")
                      .append(toLetter(move.getTo().x))
                      .append(reverseIndex(move.getTo().y)).append(promoteLetter).append("\n");
            }
        } else {
            if (move.getColor() == Color.WHITE) {
                result.append(i).append(". ").append(toLetter(move.getTo().x))
                      .append(reverseIndex(move.getTo().y)).append(promoteLetter).append(" ");

            } else {
                result.append(" ").append(toLetter(move.getTo().x))
                      .append(reverseIndex(move.getTo().y)).append(promoteLetter).append("\n");
            }
        }
        return result.toString();
    }

    private String toPieceLetter(Class<? extends Piece> type) {
        if (type.equals(Queen.class)) {
            return "Q";
        } else if (type.equals(Knight.class)) {
            return "N";
        } else if (type.equals(Bishop.class)) {
            return "B";
        } else if (type.equals(Rook.class)) {
            return "R";
        } else if (type.equals(King.class)) {
            return "K";
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

    private int reverseIndex(int n) {
        return 8 - n;
    }
}
