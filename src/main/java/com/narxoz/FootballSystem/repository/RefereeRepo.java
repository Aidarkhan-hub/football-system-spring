package com.narxoz.FootballSystem.repository;

import com.narxoz.FootballSystem.model.Referee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefereeRepo extends JpaRepository<Referee, Long> {
}
