package com.narxoz.FootballSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "player_ratings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "player_id", nullable = false, unique = true)
    private Player player;

    @Column(nullable = false)
    private Integer overall;

    @Column(nullable = false)
    private Integer speed;

    @Column(nullable = false)
    private Integer stamina;

    @Column(nullable = false)
    private Integer shooting;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
