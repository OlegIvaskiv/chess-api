package com.example.chessgame.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameInfo {
    String date;
    String round;
    String white;
    String black;
    String result;
    int whiteElo;
    int blackElo;
    int timeControl;
    String endTime;
    String termination;
}
