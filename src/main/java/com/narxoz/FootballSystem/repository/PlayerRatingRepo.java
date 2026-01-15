package com.narxoz.FootballSystem.repository;

import com.narxoz.FootballSystem.model.PlayerRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRatingRepo extends JpaRepository<PlayerRating, Long> {
    Optional<PlayerRating> findByPlayerId(Long playerId);
}
