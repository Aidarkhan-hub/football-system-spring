package com.narxoz.FootballSystem.repository;

import com.narxoz.FootballSystem.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchRepo extends JpaRepository<Match, Long> {
    List<Match> findByMatchTimeBetween(LocalDateTime from, LocalDateTime to);
}
