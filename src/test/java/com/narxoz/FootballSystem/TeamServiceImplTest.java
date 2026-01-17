package com.narxoz.FootballSystem;

import com.narxoz.FootballSystem.dto.TeamDto;
import com.narxoz.FootballSystem.mapper.TeamMapper;
import com.narxoz.FootballSystem.model.Team;
import com.narxoz.FootballSystem.repository.TeamRepo;
import com.narxoz.FootballSystem.service.serviceImpl.TeamServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TeamServiceImplTest {

    TeamRepo teamRepo = mock(TeamRepo.class);
    TeamMapper teamMapper = mock(TeamMapper.class);
    TeamServiceImpl service = new TeamServiceImpl(teamRepo, teamMapper);

    @Test
    void create_ok() {
        TeamDto dto = new TeamDto();
        dto.setName("A");

        Team entity = new Team();
        Team saved = new Team();
        TeamDto out = new TeamDto();

        when(teamRepo.existsByName("A")).thenReturn(false);
        when(teamMapper.toEntity(dto)).thenReturn(entity);
        when(teamRepo.save(entity)).thenReturn(saved);
        when(teamMapper.toDto(saved)).thenReturn(out);

        assertEquals(out, service.create(dto));
    }

    @Test
    void create_blankName_throw() {
        TeamDto dto = new TeamDto();
        dto.setName("  ");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.create(dto));
        assertEquals("Team name is required", ex.getMessage());
        verifyNoInteractions(teamRepo, teamMapper);
    }

    @Test
    void create_duplicateName_throw() {
        TeamDto dto = new TeamDto();
        dto.setName("A");

        when(teamRepo.existsByName("A")).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.create(dto));
        assertEquals("Team with name already exists: A", ex.getMessage());
        verify(teamRepo).existsByName("A");
        verifyNoInteractions(teamMapper);
        verify(teamRepo, never()).save(any());
    }

    @Test
    void update_ok() {
        TeamDto dto = new TeamDto();
        dto.setName("N");
        dto.setCity("C");
        dto.setCoachName("K");

        Team team = new Team();
        Team saved = new Team();
        TeamDto out = new TeamDto();

        when(teamRepo.findById(1L)).thenReturn(Optional.of(team));
        when(teamRepo.save(team)).thenReturn(saved);
        when(teamMapper.toDto(saved)).thenReturn(out);

        assertEquals(out, service.update(1L, dto));
        assertEquals("N", team.getName());
        assertEquals("C", team.getCity());
        assertEquals("K", team.getCoachName());
    }

    @Test
    void getById_notFound_throw() {
        when(teamRepo.findById(1L)).thenReturn(Optional.empty());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.getById(1L));
        assertEquals("Team not found: 1", ex.getMessage());
    }

    @Test
    void getAll_ok() {
        List<Team> list = List.of(new Team(), new Team());
        List<TeamDto> out = List.of(new TeamDto());

        when(teamRepo.findAll()).thenReturn(list);
        when(teamMapper.toDtoList(list)).thenReturn(out);

        assertEquals(out, service.getAll());
    }

    @Test
    void delete_ok() {
        service.delete(5L);
        verify(teamRepo).deleteById(5L);
    }
}
