package com.narxoz.FootballSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    @Column(nullable = false)
    private Integer homeGoals;

    @Column(nullable = false)
    private Integer awayGoals;

    @Column(name = "match_time", nullable = false)
    private LocalDateTime matchTime;

    private String stadium;

    @ManyToMany
    @JoinTable(
            name = "match_referees",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "referee_id")
    )
    private Set<Referee> referees = new HashSet<>();

}
