package com.narxoz.FootballSystem.service.serviceImpl;

import com.narxoz.FootballSystem.dto.MatchDto;
import com.narxoz.FootballSystem.mapper.MatchMapper;
import com.narxoz.FootballSystem.model.Match;
import com.narxoz.FootballSystem.model.Team;
import com.narxoz.FootballSystem.repository.MatchRepo;
import com.narxoz.FootballSystem.repository.TeamRepo;
import com.narxoz.FootballSystem.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepo matchRepo;
    private final TeamRepo teamRepo;
    private final MatchMapper matchMapper;

    @Override
    public MatchDto create(MatchDto dto) {
        if (dto.getHomeTeamId().equals(dto.getAwayTeamId())) {
            throw new IllegalArgumentException("Home and away teams must be different");
        }
        Team home = teamRepo.findById(dto.getHomeTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Home team not found: " + dto.getHomeTeamId()));
        Team away = teamRepo.findById(dto.getAwayTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Away team not found: " + dto.getAwayTeamId()));

        Match match = matchMapper.toEntity(dto);
        match.setHomeTeam(home);
        match.setAwayTeam(away);
        return matchMapper.toDto(matchRepo.save(match));
    }

    @Override
    public MatchDto update(Long id, MatchDto dto) {
        Match match = matchRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Match not found: " + id));

        Team home = teamRepo.findById(dto.getHomeTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Home team not found: " + dto.getHomeTeamId()));
        Team away = teamRepo.findById(dto.getAwayTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Away team not found: " + dto.getAwayTeamId()));

        match.setHomeTeam(home);
        match.setAwayTeam(away);
        match.setHomeGoals(dto.getHomeGoals());
        match.setAwayGoals(dto.getAwayGoals());
        match.setMatchTime(dto.getMatchTime());
        match.setStadium(dto.getStadium());

        return matchMapper.toDto(matchRepo.save(match));
    }

    @Override
    public MatchDto getById(Long id) {
        return matchMapper.toDto(matchRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Match not found: " + id)));
    }

    @Override
    public List<MatchDto> getAll() {
        return matchMapper.toDtoList(matchRepo.findAll());
    }

    @Override
    public void delete(Long id) {
        matchRepo.deleteById(id);
    }
}
