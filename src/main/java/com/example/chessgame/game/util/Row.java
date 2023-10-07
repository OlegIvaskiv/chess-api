package com.example.chessgame.game.util;

public enum Row {
    _1(7),
    _2(6),
    _3(5),
    _4(4),
    _5(3),
    _6(2),
    _7(1),
    _8(0);

    final int y;

    Row(int y) {
        this.y = y;
    }
}
