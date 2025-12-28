package com.narxoz.FootballSystem.controller;

import com.narxoz.FootballSystem.dto.TeamDto;
import com.narxoz.FootballSystem.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamApi {

    private final TeamService teamService;

    @PostMapping
    public TeamDto create(@RequestBody TeamDto dto) {
        return teamService.create(dto);
    }

    @PutMapping("/{id}")
    public TeamDto update(@PathVariable Long id, @RequestBody TeamDto dto) {
        return teamService.update(id, dto);
    }

    @GetMapping("/{id}")
    public TeamDto getById(@PathVariable Long id) {
        return teamService.getById(id);
    }

    @GetMapping
    public List<TeamDto> getAll() {
        return teamService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teamService.delete(id);
    }
}
