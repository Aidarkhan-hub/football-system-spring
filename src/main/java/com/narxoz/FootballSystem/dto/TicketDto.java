package com.narxoz.FootballSystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private Long id;
    private Long matchId;

    private String sector;
    private String seat;

    private Integer price;
    private String status;

    private String buyerName;
}
