package com.narxoz.FootballSystem;

import com.narxoz.FootballSystem.dto.TicketDto;
import com.narxoz.FootballSystem.mapper.TicketMapper;
import com.narxoz.FootballSystem.model.Match;
import com.narxoz.FootballSystem.model.Ticket;
import com.narxoz.FootballSystem.model.TicketStatus;
import com.narxoz.FootballSystem.repository.MatchRepo;
import com.narxoz.FootballSystem.repository.TicketRepo;
import com.narxoz.FootballSystem.service.serviceImpl.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TicketServiceImplTest {

    TicketRepo ticketRepo = mock(TicketRepo.class);
    MatchRepo matchRepo = mock(MatchRepo.class);
    TicketMapper ticketMapper = mock(TicketMapper.class);
    TicketServiceImpl service = new TicketServiceImpl(ticketRepo, matchRepo, ticketMapper);

    @Test
    void create_ok_setsDefaults() {
        TicketDto dto = new TicketDto();
        dto.setMatchId(1L);

        Match match = new Match();
        match.setId(1L);

        Ticket saved = new Ticket();
        TicketDto out = new TicketDto();

        when(matchRepo.findById(1L)).thenReturn(Optional.of(match));
        when(ticketRepo.save(any(Ticket.class))).thenAnswer(inv -> {
            Ticket t = inv.getArgument(0);
            assertSame(match, t.getMatch());
            assertEquals(TicketStatus.AVAILABLE, t.getStatus());
            assertNull(t.getBuyerName());
            assertNotNull(t.getCreatedAt());
            return saved;
        });
        when(ticketMapper.toDto(saved)).thenReturn(out);

        assertEquals(out, service.create(dto));
    }

    @Test
    void getById_notFound() {
        when(ticketRepo.findById(1L)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.getById(1L));
        assertEquals("Ticket not found", ex.getMessage());
    }

    @Test
    void buy_ok() {
        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatus.AVAILABLE);

        Ticket saved = new Ticket();
        TicketDto out = new TicketDto();

        when(ticketRepo.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepo.save(ticket)).thenReturn(saved);
        when(ticketMapper.toDto(saved)).thenReturn(out);

        assertEquals(out, service.buy(1L, "John"));
        assertEquals(TicketStatus.SOLD, ticket.getStatus());
        assertEquals("John", ticket.getBuyerName());
    }

    @Test
    void buy_notAvailable() {
        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatus.SOLD);

        when(ticketRepo.findById(1L)).thenReturn(Optional.of(ticket));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.buy(1L, "John"));
        assertEquals("Ticket is not available", ex.getMessage());
        verify(ticketRepo, never()).save(any());
    }

    @Test
    void cancel_ok() {
        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatus.AVAILABLE);

        Ticket saved = new Ticket();
        TicketDto out = new TicketDto();

        when(ticketRepo.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepo.save(ticket)).thenReturn(saved);
        when(ticketMapper.toDto(saved)).thenReturn(out);

        assertEquals(out, service.cancel(1L));
        assertEquals(TicketStatus.CANCELLED, ticket.getStatus());
    }

    @Test
    void delete_notFound() {
        when(ticketRepo.existsById(1L)).thenReturn(false);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.delete(1L));
        assertEquals("Ticket not found", ex.getMessage());
    }

    @Test
    void getAll_ok() {
        when(ticketRepo.findAll()).thenReturn(List.of(new Ticket(), new Ticket(), new Ticket()));
        when(ticketMapper.toDto(any(Ticket.class))).thenReturn(new TicketDto());

        assertEquals(3, service.getAll().size());
    }

    @Test
    void getByMatchId_ok() {
        when(ticketRepo.findByMatchId(2L)).thenReturn(List.of(new Ticket(), new Ticket()));
        when(ticketMapper.toDto(any(Ticket.class))).thenReturn(new TicketDto());

        assertEquals(2, service.getByMatchId(2L).size());
    }
}
