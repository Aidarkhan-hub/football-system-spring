package com.narxoz.FootballSystem.service;

import com.narxoz.FootballSystem.dto.PlayerDto;

import java.util.List;

public interface PlayerService {
    PlayerDto create(PlayerDto dto);
    PlayerDto update(Long id, PlayerDto dto);
    PlayerDto getById(Long id);
    List<PlayerDto> getAll();
    List<PlayerDto> getByTeamId(Long teamId);
    void delete(Long id);
}
