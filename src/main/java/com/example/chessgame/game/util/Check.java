package com.example.chessgame.game.util;

import lombok.Getter;

@Getter
public class Check {
    private boolean isCheck = false;
    private Color color;

    public void setCheck(Color color) {
        this.color = color;
        isCheck = true;
    }

    public void removeCheck() {
        isCheck = false;
    }

}
