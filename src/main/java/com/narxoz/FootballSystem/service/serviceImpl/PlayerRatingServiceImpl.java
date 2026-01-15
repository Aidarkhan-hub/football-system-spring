package com.narxoz.FootballSystem.service.serviceImpl;

import com.narxoz.FootballSystem.dto.PlayerRatingDto;
import com.narxoz.FootballSystem.mapper.PlayerRatingMapper;
import com.narxoz.FootballSystem.model.Player;
import com.narxoz.FootballSystem.model.PlayerRating;
import com.narxoz.FootballSystem.repository.PlayerRatingRepo;
import com.narxoz.FootballSystem.repository.PlayerRepo;
import com.narxoz.FootballSystem.service.PlayerRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PlayerRatingServiceImpl implements PlayerRatingService {

    private final PlayerRepo playerRepo;
    private final PlayerRatingRepo playerRatingRepo;
    private final PlayerRatingMapper playerRatingMapper;

    @Override
    public PlayerRatingDto create(PlayerRatingDto dto) {
        Player player = playerRepo.findById(dto.getPlayerId())
                .orElseThrow(() -> new RuntimeException("Player not found"));

        if (playerRatingRepo.findByPlayerId(player.getId()).isPresent()) {
            throw new RuntimeException("Rating for this player already exists");
        }

        PlayerRating rating = new PlayerRating();
        rating.setPlayer(player);
        rating.setOverall(dto.getOverall());
        rating.setSpeed(dto.getSpeed());
        rating.setStamina(dto.getStamina());
        rating.setShooting(dto.getShooting());
        rating.setUpdatedAt(LocalDateTime.now());

        return playerRatingMapper.toDto(playerRatingRepo.save(rating));
    }

    @Override
    public PlayerRatingDto updateByPlayerId(Long playerId, PlayerRatingDto dto) {
        PlayerRating rating = playerRatingRepo.findByPlayerId(playerId)
                .orElseThrow(() -> new RuntimeException("Rating not found"));

        rating.setOverall(dto.getOverall());
        rating.setSpeed(dto.getSpeed());
        rating.setStamina(dto.getStamina());
        rating.setShooting(dto.getShooting());
        rating.setUpdatedAt(LocalDateTime.now());

        return playerRatingMapper.toDto(playerRatingRepo.save(rating));
    }

    @Override
    public PlayerRatingDto getByPlayerId(Long playerId) {
        PlayerRating rating = playerRatingRepo.findByPlayerId(playerId)
                .orElseThrow(() -> new RuntimeException("Rating not found"));
        return playerRatingMapper.toDto(rating);
    }

    @Override
    public void deleteByPlayerId(Long playerId) {
        PlayerRating rating = playerRatingRepo.findByPlayerId(playerId)
                .orElseThrow(() -> new RuntimeException("Rating not found"));
        playerRatingRepo.delete(rating);
    }
}
