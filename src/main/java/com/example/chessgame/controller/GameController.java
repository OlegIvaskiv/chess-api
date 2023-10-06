package com.example.chessgame.controller;

import com.example.chessgame.dto.GameDto;
import com.example.chessgame.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class GameController {
    private GameService gameService;

    @GetMapping("/")
    public GameDto loadGame(){
        return new GameDto();
    }

}
