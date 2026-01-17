package com.narxoz.FootballSystem;

import com.narxoz.FootballSystem.dto.RefereeDto;
import com.narxoz.FootballSystem.mapper.RefereeMapper;
import com.narxoz.FootballSystem.model.Referee;
import com.narxoz.FootballSystem.repository.RefereeRepo;
import com.narxoz.FootballSystem.service.serviceImpl.RefereeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RefereeServiceImplTest {

    RefereeRepo refereeRepo = mock(RefereeRepo.class);
    RefereeMapper refereeMapper = mock(RefereeMapper.class);
    RefereeServiceImpl service = new RefereeServiceImpl(refereeRepo, refereeMapper);

    @Test
    void create_ok() {
        RefereeDto dto = new RefereeDto();
        Referee entity = new Referee();
        Referee saved = new Referee();
        RefereeDto out = new RefereeDto();

        when(refereeMapper.toEntity(dto)).thenReturn(entity);
        when(refereeRepo.save(entity)).thenReturn(saved);
        when(refereeMapper.toDto(saved)).thenReturn(out);

        assertEquals(out, service.create(dto));
    }

    @Test
    void getById_notFound() {
        when(refereeRepo.findById(1L)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.getById(1L));
        assertEquals("Referee not found", ex.getMessage());
    }

    @Test
    void update_ok() {
        RefereeDto dto = new RefereeDto();
        dto.setFullName("A");
        dto.setCategory("B");

        Referee ref = new Referee();
        Referee saved = new Referee();
        RefereeDto out = new RefereeDto();

        when(refereeRepo.findById(1L)).thenReturn(Optional.of(ref));
        when(refereeRepo.save(ref)).thenReturn(saved);
        when(refereeMapper.toDto(saved)).thenReturn(out);

        assertEquals(out, service.update(1L, dto));
        assertEquals("A", ref.getFullName());
        assertEquals("B", ref.getCategory());
    }

    @Test
    void delete_notFound() {
        when(refereeRepo.existsById(1L)).thenReturn(false);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.delete(1L));
        assertEquals("Referee not found", ex.getMessage());
    }

    @Test
    void getAll_ok() {
        List<Referee> list = List.of(new Referee(), new Referee());
        when(refereeRepo.findAll()).thenReturn(list);
        when(refereeMapper.toDto(any(Referee.class))).thenReturn(new RefereeDto());

        assertEquals(2, service.getAll().size());
    }
}
