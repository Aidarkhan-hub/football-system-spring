package com.narxoz.FootballSystem.mapper;

import com.narxoz.FootballSystem.dto.PlayerRatingDto;
import com.narxoz.FootballSystem.model.PlayerRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerRatingMapper {

    @Mapping(source = "player.id", target = "playerId")
    PlayerRatingDto toDto(PlayerRating rating);
}
