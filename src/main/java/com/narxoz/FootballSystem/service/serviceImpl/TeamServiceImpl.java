package com.narxoz.FootballSystem.service.serviceImpl;

import com.narxoz.FootballSystem.dto.TeamDto;
import com.narxoz.FootballSystem.mapper.TeamMapper;
import com.narxoz.FootballSystem.model.Team;
import com.narxoz.FootballSystem.repository.TeamRepo;
import com.narxoz.FootballSystem.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepo teamRepo;
    private final TeamMapper teamMapper;

    @Override
    public TeamDto create(TeamDto dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Team name is required");
        }
        if (teamRepo.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Team with name already exists: " + dto.getName());
        }
        Team team = teamMapper.toEntity(dto);
        return teamMapper.toDto(teamRepo.save(team));
    }

    @Override
    public TeamDto update(Long id, TeamDto dto) {
        Team team = teamRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team not found: " + id));
        team.setName(dto.getName());
        team.setCity(dto.getCity());
        team.setCoachName(dto.getCoachName());
        return teamMapper.toDto(teamRepo.save(team));
    }

    @Override
    public TeamDto getById(Long id) {
        return teamMapper.toDto(teamRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team not found: " + id)));
    }

    @Override
    public List<TeamDto> getAll() {
        return teamMapper.toDtoList(teamRepo.findAll());
    }

    @Override
    public void delete(Long id) {
        teamRepo.deleteById(id);
    }
}
