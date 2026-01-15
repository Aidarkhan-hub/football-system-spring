package com.narxoz.FootballSystem.service;

import com.narxoz.FootballSystem.dto.TicketDto;

import java.util.List;

public interface TicketService {
    TicketDto create(TicketDto dto);
    TicketDto getById(Long id);
    List<TicketDto> getAll();
    List<TicketDto> getByMatchId(Long matchId);

    TicketDto buy(Long ticketId, String buyerName);
    TicketDto cancel(Long ticketId);

    void delete(Long id);
}
