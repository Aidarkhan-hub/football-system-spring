package com.narxoz.FootballSystem.service.serviceImpl;

import com.narxoz.FootballSystem.dto.RefereeDto;
import com.narxoz.FootballSystem.mapper.RefereeMapper;
import com.narxoz.FootballSystem.model.Referee;
import com.narxoz.FootballSystem.repository.RefereeRepo;
import com.narxoz.FootballSystem.service.RefereeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefereeServiceImpl implements RefereeService {

    private final RefereeRepo refereeRepo;
    private final RefereeMapper refereeMapper;

    @Override
    public RefereeDto create(RefereeDto dto) {
        Referee saved = refereeRepo.save(refereeMapper.toEntity(dto));
        return refereeMapper.toDto(saved);
    }

    @Override
    public List<RefereeDto> getAll() {
        return refereeRepo.findAll()
                .stream()
                .map(refereeMapper::toDto)
                .toList();
    }

    @Override
    public RefereeDto getById(Long id) {
        Referee referee = refereeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Referee not found"));
        return refereeMapper.toDto(referee);
    }

    @Override
    public RefereeDto update(Long id, RefereeDto dto) {
        Referee referee = refereeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Referee not found"));

        referee.setFullName(dto.getFullName());
        referee.setCategory(dto.getCategory());

        return refereeMapper.toDto(refereeRepo.save(referee));
    }

    @Override
    public void delete(Long id) {
        if (!refereeRepo.existsById(id)) {
            throw new RuntimeException("Referee not found");
        }
        refereeRepo.deleteById(id);
    }
}
