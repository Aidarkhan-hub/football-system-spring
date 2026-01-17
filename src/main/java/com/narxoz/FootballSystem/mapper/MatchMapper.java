package com.narxoz.FootballSystem.mapper;

import com.narxoz.FootballSystem.dto.MatchDto;
import com.narxoz.FootballSystem.model.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "homeTeamId", source = "homeTeam.id")
    @Mapping(target = "awayTeamId", source = "awayTeam.id")
    @Mapping(target = "homeGoals", source = "homeGoals")
    @Mapping(target = "awayGoals", source = "awayGoals")
    @Mapping(target = "matchTime", source = "matchTime")
    @Mapping(target = "stadium", source = "stadium")
    MatchDto toDto(Match match);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "homeTeam.id", source = "homeTeamId")
    @Mapping(target = "awayTeam.id", source = "awayTeamId")
    @Mapping(target = "homeGoals", source = "homeGoals")
    @Mapping(target = "awayGoals", source = "awayGoals")
    @Mapping(target = "matchTime", source = "matchTime")
    @Mapping(target = "stadium", source = "stadium")
    @Mapping(target = "referees", ignore = true)
    Match toEntity(MatchDto dto);

    List<MatchDto> toDtoList(List<Match> matches);
}
