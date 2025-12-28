package com.narxoz.FootballSystem.mapper;

import com.narxoz.FootballSystem.dto.TeamDto;
import com.narxoz.FootballSystem.model.Team;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamDto toDto(Team team);
    Team toEntity(TeamDto dto);
    List<TeamDto> toDtoList(List<Team> teams);
}
