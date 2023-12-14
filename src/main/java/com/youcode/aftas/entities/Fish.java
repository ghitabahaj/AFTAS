package com.youcode.aftas.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private double averageWeight;

    @OneToMany(mappedBy = "fish")
    @JsonIgnore
    private List<Hunting> huntings;

    @ManyToOne
    private Level level;

}
