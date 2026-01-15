package com.narxoz.FootballSystem.controller;

import com.narxoz.FootballSystem.dto.PlayerRatingDto;
import com.narxoz.FootballSystem.service.PlayerRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player-ratings")
@RequiredArgsConstructor
public class PlayerRatingApi {

    private final PlayerRatingService playerRatingService;

    @PostMapping
    public PlayerRatingDto create(@RequestBody PlayerRatingDto dto) {
        return playerRatingService.create(dto);
    }

    @GetMapping("/by-player/{playerId}")
    public PlayerRatingDto getByPlayerId(@PathVariable Long playerId) {
        return playerRatingService.getByPlayerId(playerId);
    }

    @PutMapping("/by-player/{playerId}")
    public PlayerRatingDto updateByPlayerId(@PathVariable Long playerId,
                                            @RequestBody PlayerRatingDto dto) {
        return playerRatingService.updateByPlayerId(playerId, dto);
    }

    @DeleteMapping("/by-player/{playerId}")
    public void deleteByPlayerId(@PathVariable Long playerId) {
        playerRatingService.deleteByPlayerId(playerId);
    }
}
