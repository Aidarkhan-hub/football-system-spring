package com.narxoz.FootballSystem.service.serviceImpl;

import com.narxoz.FootballSystem.dto.PlayerDto;
import com.narxoz.FootballSystem.mapper.PlayerMapper;
import com.narxoz.FootballSystem.model.Player;
import com.narxoz.FootballSystem.repository.PlayerRepo;
import com.narxoz.FootballSystem.repository.TeamRepo;
import com.narxoz.FootballSystem.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepo playerRepo;
    private final TeamRepo teamRepo;
    private final PlayerMapper playerMapper;

    @Override
    public PlayerDto create(PlayerDto dto) {
        teamRepo.findById(dto.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Team not found: " + dto.getTeamId()));

        Player player = playerMapper.toEntity(dto);

        if (player.getGoals() == null) {
            player.setGoals(0);
        }

        return playerMapper.toDto(playerRepo.save(player));
    }

    @Override
    public PlayerDto update(Long id, PlayerDto dto) {
        Player player = playerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Player not found: " + id));

        teamRepo.findById(dto.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Team not found: " + dto.getTeamId()));

        player.setFullName(dto.getFullName());
        player.setPosition(dto.getPosition());
        player.setAge(dto.getAge());
        player.setJerseyNumber(dto.getJerseyNumber());
        player.setGoals(dto.getGoals());

        if (player.getTeam() == null) {
            player.setTeam(new com.narxoz.FootballSystem.model.Team());
        }
        player.getTeam().setId(dto.getTeamId());

        return playerMapper.toDto(playerRepo.save(player));
    }

    @Override
    public PlayerDto getById(Long id) {
        return playerMapper.toDto(playerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Player not found: " + id)));
    }

    @Override
    public List<PlayerDto> getAll() {
        return playerMapper.toDtoList(playerRepo.findAll());
    }

    @Override
    public List<PlayerDto> getByTeamId(Long teamId) {
        return playerMapper.toDtoList(playerRepo.findByTeamId(teamId));
    }

    @Override
    public void delete(Long id) {
        playerRepo.deleteById(id);
    }
}
