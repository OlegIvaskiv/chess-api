package com.example.chessgame.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Embeddable
public class MoveEntity {
    private int index;
    private String move;
    private Instant time;
}
