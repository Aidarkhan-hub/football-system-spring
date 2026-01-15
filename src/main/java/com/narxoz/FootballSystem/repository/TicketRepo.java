package com.narxoz.FootballSystem.repository;

import com.narxoz.FootballSystem.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
    List<Ticket> findByMatchId(Long matchId);
}
