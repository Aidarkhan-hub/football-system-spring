package com.narxoz.FootballSystem.repository;

import com.narxoz.FootballSystem.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepo extends JpaRepository<Player, Long> {
    List<Player> findByTeamId(Long teamId);
}
