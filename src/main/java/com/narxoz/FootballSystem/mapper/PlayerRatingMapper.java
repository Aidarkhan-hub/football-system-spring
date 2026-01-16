package com.narxoz.FootballSystem.mapper;

import com.narxoz.FootballSystem.dto.PlayerRatingDto;
import com.narxoz.FootballSystem.model.PlayerRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerRatingMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "playerId", source = "player.id")
    @Mapping(target = "overall", source = "overall")
    @Mapping(target = "speed", source = "speed")
    @Mapping(target = "stamina", source = "stamina")
    @Mapping(target = "shooting", source = "shooting")
    PlayerRatingDto toDto(PlayerRating rating);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "player", ignore = true)
    @Mapping(target = "overall", source = "overall")
    @Mapping(target = "speed", source = "speed")
    @Mapping(target = "stamina", source = "stamina")
    @Mapping(target = "shooting", source = "shooting")
    PlayerRating toEntity(PlayerRatingDto dto);
}
