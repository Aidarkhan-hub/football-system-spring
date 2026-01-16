package com.narxoz.FootballSystem.mapper;

import com.narxoz.FootballSystem.dto.RefereeDto;
import com.narxoz.FootballSystem.model.Referee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RefereeMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "category", source = "category")
    RefereeDto toDto(Referee referee);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "matches", ignore = true)
    Referee toEntity(RefereeDto dto);
}
