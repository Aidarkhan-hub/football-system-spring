package com.narxoz.FootballSystem;

import com.narxoz.FootballSystem.dto.PlayerDto;
import com.narxoz.FootballSystem.mapper.PlayerMapper;
import com.narxoz.FootballSystem.model.Player;
import com.narxoz.FootballSystem.model.Team;
import com.narxoz.FootballSystem.repository.PlayerRepo;
import com.narxoz.FootballSystem.repository.TeamRepo;
import com.narxoz.FootballSystem.service.serviceImpl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PlayerServiceImplTest {

    PlayerRepo playerRepo = mock(PlayerRepo.class);
    TeamRepo teamRepo = mock(TeamRepo.class);
    PlayerMapper playerMapper = mock(PlayerMapper.class);
    PlayerServiceImpl service = new PlayerServiceImpl(playerRepo, teamRepo, playerMapper);

    @Test
    void create_setsGoals() {
        PlayerDto dto = new PlayerDto();
        dto.setTeamId(1L);

        Team team = new Team();
        team.setId(1L);

        Player entity = new Player();
        entity.setGoals(null);

        Player saved = new Player();
        PlayerDto out = new PlayerDto();

        when(teamRepo.findById(1L)).thenReturn(Optional.of(team));
        when(playerMapper.toEntity(dto)).thenReturn(entity);
        when(playerRepo.save(entity)).thenAnswer(inv -> {
            Player p = inv.getArgument(0);
            assertEquals(0, p.getGoals());
            return saved;
        });
        when(playerMapper.toDto(saved)).thenReturn(out);

        assertEquals(out, service.create(dto));
    }

    @Test
    void create_teamNotFound() {
        PlayerDto dto = new PlayerDto();
        dto.setTeamId(10L);

        when(teamRepo.findById(10L)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.create(dto));
        assertEquals("Team not found: 10", ex.getMessage());
        verify(teamRepo).findById(10L);
        verifyNoInteractions(playerRepo, playerMapper);
    }

    @Test
    void update_ok() {
        PlayerDto dto = new PlayerDto();
        dto.setTeamId(2L);
        dto.setFullName("A");
        dto.setPosition("FW");
        dto.setAge(20);
        dto.setJerseyNumber(9);
        dto.setGoals(5);

        Player player = new Player();
        player.setTeam(null);

        when(playerRepo.findById(1L)).thenReturn(Optional.of(player));
        when(teamRepo.findById(2L)).thenReturn(Optional.of(new Team()));
        when(playerRepo.save(player)).thenAnswer(inv -> {
            Player p = inv.getArgument(0);
            assertEquals("A", p.getFullName());
            assertEquals("FW", p.getPosition());
            assertEquals(20, p.getAge());
            assertEquals(9, p.getJerseyNumber());
            assertEquals(5, p.getGoals());
            assertNotNull(p.getTeam());
            assertEquals(2L, p.getTeam().getId());
            return p;
        });
        PlayerDto out = new PlayerDto();
        when(playerMapper.toDto(any(Player.class))).thenReturn(out);

        assertEquals(out, service.update(1L, dto));
    }

    @Test
    void update_playerNotFound() {
        when(playerRepo.findById(1L)).thenReturn(Optional.empty());

        PlayerDto dto = new PlayerDto();
        dto.setTeamId(1L);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.update(1L, dto));
        assertEquals("Player not found: 1", ex.getMessage());
        verify(playerRepo).findById(1L);
        verifyNoInteractions(teamRepo, playerMapper);
    }

    @Test
    void update_teamNotFound() {
        Player player = new Player();
        when(playerRepo.findById(1L)).thenReturn(Optional.of(player));
        when(teamRepo.findById(99L)).thenReturn(Optional.empty());

        PlayerDto dto = new PlayerDto();
        dto.setTeamId(99L);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.update(1L, dto));
        assertEquals("Team not found: 99", ex.getMessage());
        verify(playerRepo).findById(1L);
        verify(teamRepo).findById(99L);
        verify(playerRepo, never()).save(any());
        verifyNoInteractions(playerMapper);
    }

    @Test
    void getById_ok() {
        Player player = new Player();
        PlayerDto out = new PlayerDto();

        when(playerRepo.findById(1L)).thenReturn(Optional.of(player));
        when(playerMapper.toDto(player)).thenReturn(out);

        assertEquals(out, service.getById(1L));
    }

    @Test
    void getById_notFound_throw() {
        when(playerRepo.findById(7L)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.getById(7L));
        assertEquals("Player not found: 7", ex.getMessage());
    }

    @Test
    void getAll_ok() {
        List<Player> players = List.of(new Player(), new Player());
        List<PlayerDto> out = List.of(new PlayerDto(), new PlayerDto());

        when(playerRepo.findAll()).thenReturn(players);
        when(playerMapper.toDtoList(players)).thenReturn(out);

        assertEquals(out, service.getAll());
    }

    @Test
    void getByTeamId_ok() {
        List<Player> players = List.of(new Player(), new Player(), new Player());
        List<PlayerDto> out = List.of(new PlayerDto());

        when(playerRepo.findByTeamId(5L)).thenReturn(players);
        when(playerMapper.toDtoList(players)).thenReturn(out);

        assertEquals(out, service.getByTeamId(5L));
    }

    @Test
    void delete_ok() {
        service.delete(3L);
        verify(playerRepo).deleteById(3L);
    }
}
