package com.narxoz.FootballSystem.controller;

import com.narxoz.FootballSystem.dto.MatchDto;
import com.narxoz.FootballSystem.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchApi {

    private final MatchService matchService;

    @PostMapping
    public MatchDto create(@RequestBody MatchDto dto) {
        return matchService.create(dto);
    }

    @PutMapping("/{id}")
    public MatchDto update(@PathVariable Long id, @RequestBody MatchDto dto) {
        return matchService.update(id, dto);
    }

    @GetMapping("/{id}")
    public MatchDto getById(@PathVariable Long id) {
        return matchService.getById(id);
    }

    @GetMapping
    public List<MatchDto> getAll() {
        return matchService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        matchService.delete(id);
    }
}
