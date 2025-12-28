package com.narxoz.FootballSystem.service;

import com.narxoz.FootballSystem.dto.MatchDto;

import java.util.List;

public interface MatchService {
    MatchDto create(MatchDto dto);
    MatchDto update(Long id, MatchDto dto);
    MatchDto getById(Long id);
    List<MatchDto> getAll();
    void delete(Long id);
}
