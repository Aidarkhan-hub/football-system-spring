package com.narxoz.FootballSystem.service.serviceImpl;

import com.narxoz.FootballSystem.dto.TicketDto;
import com.narxoz.FootballSystem.mapper.TicketMapper;
import com.narxoz.FootballSystem.model.Match;
import com.narxoz.FootballSystem.model.Ticket;
import com.narxoz.FootballSystem.model.TicketStatus;
import com.narxoz.FootballSystem.repository.MatchRepo;
import com.narxoz.FootballSystem.repository.TicketRepo;
import com.narxoz.FootballSystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepo ticketRepo;
    private final MatchRepo matchRepo;
    private final TicketMapper ticketMapper;

    @Override
    public TicketDto create(TicketDto dto) {
        Match match = matchRepo.findById(dto.getMatchId())
                .orElseThrow(() -> new RuntimeException("Match not found"));

        Ticket ticket = new Ticket();
        ticket.setMatch(match);
        ticket.setSector(dto.getSector());
        ticket.setSeat(dto.getSeat());
        ticket.setPrice(dto.getPrice());

        ticket.setStatus(TicketStatus.AVAILABLE);
        ticket.setBuyerName(null);
        ticket.setCreatedAt(LocalDateTime.now());

        return ticketMapper.toDto(ticketRepo.save(ticket));
    }

    @Override
    public TicketDto getById(Long id) {
        Ticket ticket = ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        return ticketMapper.toDto(ticket);
    }

    @Override
    public List<TicketDto> getAll() {
        return ticketRepo.findAll().stream().map(ticketMapper::toDto).toList();
    }

    @Override
    public List<TicketDto> getByMatchId(Long matchId) {
        return ticketRepo.findByMatchId(matchId).stream().map(ticketMapper::toDto).toList();
    }

    @Override
    public TicketDto buy(Long ticketId, String buyerName) {
        Ticket ticket = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getStatus() != TicketStatus.AVAILABLE) {
            throw new RuntimeException("Ticket is not available");
        }

        ticket.setStatus(TicketStatus.SOLD);
        ticket.setBuyerName(buyerName);

        return ticketMapper.toDto(ticketRepo.save(ticket));
    }

    @Override
    public TicketDto cancel(Long ticketId) {
        Ticket ticket = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setStatus(TicketStatus.CANCELLED);

        return ticketMapper.toDto(ticketRepo.save(ticket));
    }

    @Override
    public void delete(Long id) {
        if (!ticketRepo.existsById(id)) {
            throw new RuntimeException("Ticket not found");
        }
        ticketRepo.deleteById(id);
    }
}
