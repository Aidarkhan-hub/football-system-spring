package com.narxoz.FootballSystem.mapper;

import com.narxoz.FootballSystem.dto.MatchDto;
import com.narxoz.FootballSystem.model.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    @Mapping(target = "homeTeamId", source = "homeTeam.id")
    @Mapping(target = "awayTeamId", source = "awayTeam.id")
    MatchDto toDto(Match match);

    @Mapping(target = "homeTeam", ignore = true)
    @Mapping(target = "awayTeam", ignore = true)
    Match toEntity(MatchDto dto);

    List<MatchDto> toDtoList(List<Match> matches);
}
