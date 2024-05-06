package com.fitcoach.fitcoach.dao.entity;

import com.fitcoach.fitcoach.enums.NiveauDifficulte;
import com.fitcoach.fitcoach.enums.TypeExercice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Exercice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    private String description;

    private String groupeMusculaire;

    @Enumerated(EnumType.STRING)
    private TypeExercice type=TypeExercice.MUSCULATION;

    @Enumerated(EnumType.STRING)
    private NiveauDifficulte niveau=NiveauDifficulte.BEGINNER;

    private String illustration;

    private String notes;

    @ManyToMany(mappedBy = "exercices")
    private Collection<Seance> seances;

    // Getters and setters
}
