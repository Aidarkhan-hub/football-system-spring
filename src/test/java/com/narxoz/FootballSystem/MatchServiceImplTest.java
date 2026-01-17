package com.narxoz.FootballSystem;

import com.narxoz.FootballSystem.dto.MatchDto;
import com.narxoz.FootballSystem.mapper.MatchMapper;
import com.narxoz.FootballSystem.model.Match;
import com.narxoz.FootballSystem.model.Team;
import com.narxoz.FootballSystem.repository.MatchRepo;
import com.narxoz.FootballSystem.repository.TeamRepo;
import com.narxoz.FootballSystem.service.serviceImpl.MatchServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MatchServiceImplTest {

    MatchRepo matchRepo = mock(MatchRepo.class);
    TeamRepo teamRepo = mock(TeamRepo.class);
    MatchMapper matchMapper = mock(MatchMapper.class);
    MatchServiceImpl service = new MatchServiceImpl(matchRepo, teamRepo, matchMapper);

    @Test
    void create_ok() {
        MatchDto dto = new MatchDto();
        dto.setHomeTeamId(1L);
        dto.setAwayTeamId(2L);

        Team home = new Team();
        Team away = new Team();
        Match match = new Match();
        Match saved = new Match();
        MatchDto out = new MatchDto();

        when(teamRepo.findById(1L)).thenReturn(Optional.of(home));
        when(teamRepo.findById(2L)).thenReturn(Optional.of(away));
        when(matchMapper.toEntity(dto)).thenReturn(match);
        when(matchRepo.save(match)).thenReturn(saved);
        when(matchMapper.toDto(saved)).thenReturn(out);

        assertEquals(out, service.create(dto));
    }

    @Test
    void create_sameTeams_throw() {
        MatchDto dto = new MatchDto();
        dto.setHomeTeamId(1L);
        dto.setAwayTeamId(1L);

        assertThrows(IllegalArgumentException.class, () -> service.create(dto));
    }

    @Test
    void update_ok() {
        MatchDto dto = new MatchDto();
        dto.setHomeTeamId(1L);
        dto.setAwayTeamId(2L);

        Match match = new Match();
        Team home = new Team();
        Team away = new Team();
        MatchDto out = new MatchDto();

        when(matchRepo.findById(1L)).thenReturn(Optional.of(match));
        when(teamRepo.findById(1L)).thenReturn(Optional.of(home));
        when(teamRepo.findById(2L)).thenReturn(Optional.of(away));
        when(matchRepo.save(match)).thenReturn(match);
        when(matchMapper.toDto(match)).thenReturn(out);

        assertEquals(out, service.update(1L, dto));
    }

    @Test
    void getById_notFound() {
        when(matchRepo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.getById(99L));
    }
}
