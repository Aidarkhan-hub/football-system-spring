package com.narxoz.FootballSystem.mapper;

import com.narxoz.FootballSystem.dto.PlayerDto;
import com.narxoz.FootballSystem.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "teamId", source = "team.id")
    PlayerDto toDto(Player player);

    @Mapping(target = "team", ignore = true) // set in serviceImpl via TeamRepo
    Player toEntity(PlayerDto dto);

    List<PlayerDto> toDtoList(List<Player> players);
}
