package com.narxoz.FootballSystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerDto {
    private Long id;
    private String fullName;
    private String position;
    private Integer age;
    private Integer jerseyNumber;
    private Integer goals;
    private Long teamId;
}
