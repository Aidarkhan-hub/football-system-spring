package com.narxoz.FootballSystem;

import com.narxoz.FootballSystem.dto.PlayerRatingDto;
import com.narxoz.FootballSystem.mapper.PlayerRatingMapper;
import com.narxoz.FootballSystem.model.Player;
import com.narxoz.FootballSystem.model.PlayerRating;
import com.narxoz.FootballSystem.repository.PlayerRatingRepo;
import com.narxoz.FootballSystem.repository.PlayerRepo;
import com.narxoz.FootballSystem.service.serviceImpl.PlayerRatingServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PlayerRatingServiceImplTest {

    PlayerRepo playerRepo = mock(PlayerRepo.class);
    PlayerRatingRepo playerRatingRepo = mock(PlayerRatingRepo.class);
    PlayerRatingMapper playerRatingMapper = mock(PlayerRatingMapper.class);
    PlayerRatingServiceImpl service = new PlayerRatingServiceImpl(playerRepo, playerRatingRepo, playerRatingMapper);

    @Test
    void create_ok() {
        PlayerRatingDto dto = new PlayerRatingDto();
        dto.setPlayerId(1L);
        dto.setOverall(80);
        dto.setSpeed(85);
        dto.setStamina(78);
        dto.setShooting(82);

        Player player = new Player();
        player.setId(1L);

        PlayerRating saved = new PlayerRating();
        PlayerRatingDto out = new PlayerRatingDto();

        when(playerRepo.findById(1L)).thenReturn(Optional.of(player));
        when(playerRatingRepo.findByPlayerId(1L)).thenReturn(Optional.empty());
        when(playerRatingRepo.save(any(PlayerRating.class))).thenReturn(saved);
        when(playerRatingMapper.toDto(saved)).thenReturn(out);

        assertEquals(out, service.create(dto));
        verify(playerRepo).findById(1L);
        verify(playerRatingRepo).findByPlayerId(1L);
        verify(playerRatingRepo).save(any(PlayerRating.class));
        verify(playerRatingMapper).toDto(saved);
    }

    @Test
    void create_playerNotFound() {
        PlayerRatingDto dto = new PlayerRatingDto();
        dto.setPlayerId(1L);

        when(playerRepo.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.create(dto));
        assertEquals("Player not found", ex.getMessage());
        verify(playerRepo).findById(1L);
        verifyNoInteractions(playerRatingRepo, playerRatingMapper);
    }

    @Test
    void create_ratingAlreadyExists() {
        PlayerRatingDto dto = new PlayerRatingDto();
        dto.setPlayerId(1L);

        Player player = new Player();
        player.setId(1L);

        when(playerRepo.findById(1L)).thenReturn(Optional.of(player));
        when(playerRatingRepo.findByPlayerId(1L)).thenReturn(Optional.of(new PlayerRating()));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.create(dto));
        assertEquals("Rating for this player already exists", ex.getMessage());
        verify(playerRepo).findById(1L);
        verify(playerRatingRepo).findByPlayerId(1L);
        verify(playerRatingRepo, never()).save(any());
        verifyNoInteractions(playerRatingMapper);
    }

    @Test
    void update_ok() {
        PlayerRatingDto dto = new PlayerRatingDto();
        dto.setOverall(90);
        dto.setSpeed(91);
        dto.setStamina(92);
        dto.setShooting(93);

        PlayerRating existing = new PlayerRating();
        PlayerRating saved = new PlayerRating();
        PlayerRatingDto out = new PlayerRatingDto();

        when(playerRatingRepo.findByPlayerId(1L)).thenReturn(Optional.of(existing));
        when(playerRatingRepo.save(existing)).thenReturn(saved);
        when(playerRatingMapper.toDto(saved)).thenReturn(out);

        assertEquals(out, service.updateByPlayerId(1L, dto));
        verify(playerRatingRepo).findByPlayerId(1L);
        verify(playerRatingRepo).save(existing);
        verify(playerRatingMapper).toDto(saved);
    }

    @Test
    void update_notFound() {
        PlayerRatingDto dto = new PlayerRatingDto();

        when(playerRatingRepo.findByPlayerId(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.updateByPlayerId(1L, dto));
        assertEquals("Rating not found", ex.getMessage());
        verify(playerRatingRepo).findByPlayerId(1L);
        verify(playerRatingRepo, never()).save(any());
        verifyNoInteractions(playerRatingMapper);
    }

    @Test
    void getByPlayerId_ok() {
        PlayerRating rating = new PlayerRating();
        PlayerRatingDto out = new PlayerRatingDto();

        when(playerRatingRepo.findByPlayerId(1L)).thenReturn(Optional.of(rating));
        when(playerRatingMapper.toDto(rating)).thenReturn(out);

        assertEquals(out, service.getByPlayerId(1L));
        verify(playerRatingRepo).findByPlayerId(1L);
        verify(playerRatingMapper).toDto(rating);
    }

    @Test
    void getByPlayerId_notFound() {
        when(playerRatingRepo.findByPlayerId(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.getByPlayerId(1L));
        assertEquals("Rating not found", ex.getMessage());
        verify(playerRatingRepo).findByPlayerId(1L);
        verifyNoInteractions(playerRatingMapper);
    }

    @Test
    void delete_ok() {
        PlayerRating rating = new PlayerRating();

        when(playerRatingRepo.findByPlayerId(1L)).thenReturn(Optional.of(rating));

        service.deleteByPlayerId(1L);

        verify(playerRatingRepo).findByPlayerId(1L);
        verify(playerRatingRepo).delete(rating);
        verifyNoInteractions(playerRatingMapper);
    }

    @Test
    void delete_notFound() {
        when(playerRatingRepo.findByPlayerId(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.deleteByPlayerId(1L));
        assertEquals("Rating not found", ex.getMessage());
        verify(playerRatingRepo).findByPlayerId(1L);
        verify(playerRatingRepo, never()).delete(any());
        verifyNoInteractions(playerRatingMapper);
    }
}
