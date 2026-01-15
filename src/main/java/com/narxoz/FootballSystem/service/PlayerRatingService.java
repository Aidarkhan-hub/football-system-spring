package com.narxoz.FootballSystem.service;

import com.narxoz.FootballSystem.dto.PlayerRatingDto;

public interface PlayerRatingService {
    PlayerRatingDto create(PlayerRatingDto dto);
    PlayerRatingDto updateByPlayerId(Long playerId, PlayerRatingDto dto);
    PlayerRatingDto getByPlayerId(Long playerId);
    void deleteByPlayerId(Long playerId);
}
