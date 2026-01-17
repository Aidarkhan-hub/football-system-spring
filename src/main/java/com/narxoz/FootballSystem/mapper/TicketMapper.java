package com.narxoz.FootballSystem.mapper;

import com.narxoz.FootballSystem.dto.TicketDto;
import com.narxoz.FootballSystem.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(source = "match.id", target = "matchId")
    @Mapping(source = "status", target = "status")
    TicketDto toDto(Ticket ticket);

    @Mapping(target = "match", ignore = true)
    @Mapping(target = "status", ignore = true)
    Ticket toEntity(TicketDto dto);
}
