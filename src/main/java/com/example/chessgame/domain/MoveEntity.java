package com.example.chessgame.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MoveEntity {
    private int index;
    private String move;
    private Instant time;
}
