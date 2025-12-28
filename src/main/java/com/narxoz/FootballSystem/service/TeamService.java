package com.narxoz.FootballSystem.service;

import com.narxoz.FootballSystem.dto.TeamDto;

import java.util.List;

public interface TeamService {
    TeamDto create(TeamDto dto);
    TeamDto update(Long id, TeamDto dto);
    TeamDto getById(Long id);
    List<TeamDto> getAll();
    void delete(Long id);
}
