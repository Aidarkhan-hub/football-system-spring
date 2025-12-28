package com.narxoz.FootballSystem.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchDto {
    private Long id;
    private Long homeTeamId;
    private Long awayTeamId;
    private Integer homeGoals;
    private Integer awayGoals;
    private LocalDateTime matchTime;
    private String stadium;
}
