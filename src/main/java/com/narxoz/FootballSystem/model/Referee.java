package com.narxoz.FootballSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "referees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Referee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    private String category;

    @ManyToMany(mappedBy = "referees")
    private Set<Match> matches = new HashSet<>();
}
