package com.example.chessgame.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Game {
    @Id
    private Long id;
    @Setter(AccessLevel.PRIVATE)
    @ElementCollection
    private List<MoveEntity> moves = new ArrayList<>();
    private Instant start = Instant.now();
    private Instant end;

    public void addMove(MoveEntity move) {
        moves.add(move);
    }
}
