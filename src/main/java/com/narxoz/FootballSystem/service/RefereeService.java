package com.narxoz.FootballSystem.service;

import com.narxoz.FootballSystem.dto.RefereeDto;

import java.util.List;

public interface RefereeService {
    RefereeDto create(RefereeDto dto);
    List<RefereeDto> getAll();
    RefereeDto getById(Long id);
    RefereeDto update(Long id, RefereeDto dto);
    void delete(Long id);
}
