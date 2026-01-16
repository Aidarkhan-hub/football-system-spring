package com.narxoz.FootballSystem.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Integer jerseyNumber;

    @Column(nullable = false)
    private Integer goals;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private PlayerRating rating;

}
