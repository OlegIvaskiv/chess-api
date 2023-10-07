package com.example.chessgame.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Game {
    private Long id;
    @Setter(AccessLevel.PRIVATE)

    private List<MoveEntity> moves = new ArrayList<>();
    private Instant start = Instant.now();
    private Instant end;
    public void addMove(MoveEntity move) {
        moves.add(move);
    }
}
