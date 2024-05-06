package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dtos.ExerciceDTO;

import java.util.Collection;

public interface ExerciceManager {
    Collection<ExerciceDTO> listExercice();
    ExerciceDTO updateExercice(Long id,ExerciceDTO exerciceDTO);
    ExerciceDTO addExercice(ExerciceDTO exerciceDTO);

    boolean deleteExercice(Long id);
}
