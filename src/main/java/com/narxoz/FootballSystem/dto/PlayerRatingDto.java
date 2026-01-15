package com.narxoz.FootballSystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRatingDto {
    private Long id;
    private Long playerId;

    private Integer overall;
    private Integer speed;
    private Integer stamina;
    private Integer shooting;
}
