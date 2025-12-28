package com.narxoz.FootballSystem.repository;

import com.narxoz.FootballSystem.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<Team, Long> {
    boolean existsByName(String name);
}
