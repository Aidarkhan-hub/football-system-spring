package com.narxoz.FootballSystem.controller;

import com.narxoz.FootballSystem.dto.RefereeDto;
import com.narxoz.FootballSystem.service.RefereeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/referees")
public class RefereeController {

    private final RefereeService refereeService;

    @PostMapping
    public RefereeDto create(@RequestBody RefereeDto dto) {
        return refereeService.create(dto);
    }

    @GetMapping
    public List<RefereeDto> getAll() {
        return refereeService.getAll();
    }

    @GetMapping("/{id}")
    public RefereeDto getById(@PathVariable Long id) {
        return refereeService.getById(id);
    }

    @PutMapping("/{id}")
    public RefereeDto update(@PathVariable Long id, @RequestBody RefereeDto dto) {
        return refereeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        refereeService.delete(id);
    }
}
