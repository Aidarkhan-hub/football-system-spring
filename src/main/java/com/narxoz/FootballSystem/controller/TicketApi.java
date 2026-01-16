package com.narxoz.FootballSystem.controller;

import com.narxoz.FootballSystem.dto.TicketDto;
import com.narxoz.FootballSystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketApi {

    private final TicketService ticketService;

    @PostMapping
    public TicketDto create(@RequestBody TicketDto dto) {
        return ticketService.create(dto);
    }

    @GetMapping("/{id}")
    public TicketDto getById(@PathVariable Long id) {
        return ticketService.getById(id);
    }

    @GetMapping
    public List<TicketDto> getAll() {
        return ticketService.getAll();
    }

    @GetMapping("/by-match/{matchId}")
    public List<TicketDto> getByMatch(@PathVariable Long matchId) {
        return ticketService.getByMatchId(matchId);
    }

    @PostMapping("/{ticketId}/buy")
    public TicketDto buy(@PathVariable Long ticketId, @RequestParam String buyerName) {
        return ticketService.buy(ticketId, buyerName);
    }

    @PostMapping("/{ticketId}/cancel")
    public TicketDto cancel(@PathVariable Long ticketId) {
        return ticketService.cancel(ticketId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ticketService.delete(id);
    }
}
