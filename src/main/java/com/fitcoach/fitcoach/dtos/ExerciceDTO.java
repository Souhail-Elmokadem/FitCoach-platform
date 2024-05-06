package com.fitcoach.fitcoach.dtos;

import com.fitcoach.fitcoach.enums.NiveauDifficulte;
import com.fitcoach.fitcoach.enums.TypeExercice;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciceDTO {
    private Long id;

    private String nom;

    private String description;

    private String groupeMusculaire;


    private TypeExercice type;


    private NiveauDifficulte niveau;

    private String illustration;

    private String notes;
}
