package com.narxoz.FootballSystem.mapper;

import com.narxoz.FootballSystem.dto.PlayerDto;
import com.narxoz.FootballSystem.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "position", source = "position")
    @Mapping(target = "age", source = "age")
    @Mapping(target = "jerseyNumber", source = "jerseyNumber")
    @Mapping(target = "goals", source = "goals")
    @Mapping(target = "teamId", source = "team.id")
    PlayerDto toDto(Player player);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "position", source = "position")
    @Mapping(target = "age", source = "age")
    @Mapping(target = "jerseyNumber", source = "jerseyNumber")
    @Mapping(target = "goals", source = "goals")
    @Mapping(target = "team.id", source = "teamId")
    Player toEntity(PlayerDto dto);

    List<PlayerDto> toDtoList(List<Player> players);
}
