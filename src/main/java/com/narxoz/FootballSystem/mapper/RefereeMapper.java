package com.narxoz.FootballSystem.mapper;

import com.narxoz.FootballSystem.dto.RefereeDto;
import com.narxoz.FootballSystem.model.Referee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefereeMapper {
    Referee toEntity(RefereeDto dto);
    RefereeDto toDto(Referee referee);
}
