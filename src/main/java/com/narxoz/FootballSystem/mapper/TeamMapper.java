package com.narxoz.FootballSystem.mapper;

import com.narxoz.FootballSystem.dto.TeamDto;
import com.narxoz.FootballSystem.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "coachName", source = "coachName")
    TeamDto toDto(Team team);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "coachName", source = "coachName")
    Team toEntity(TeamDto dto);

    List<TeamDto> toDtoList(List<Team> teams);
}
