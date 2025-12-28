package com.narxoz.FootballSystem.controller;

import com.narxoz.FootballSystem.dto.PlayerDto;
import com.narxoz.FootballSystem.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerApi {

    private final PlayerService playerService;

    @PostMapping
    public PlayerDto create(@RequestBody PlayerDto dto) {
        return playerService.create(dto);
    }

    @PutMapping("/{id}")
    public PlayerDto update(@PathVariable Long id, @RequestBody PlayerDto dto) {
        return playerService.update(id, dto);
    }

    @GetMapping("/{id}")
    public PlayerDto getById(@PathVariable Long id) {
        return playerService.getById(id);
    }

    @GetMapping
    public List<PlayerDto> getAll() {
        return playerService.getAll();
    }

    @GetMapping("/by-team/{teamId}")
    public List<PlayerDto> getByTeamId(@PathVariable Long teamId) {
        return playerService.getByTeamId(teamId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playerService.delete(id);
    }
}
