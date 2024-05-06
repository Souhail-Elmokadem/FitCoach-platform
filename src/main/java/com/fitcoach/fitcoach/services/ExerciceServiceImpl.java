package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.Repository.ExerciceRepository;
import com.fitcoach.fitcoach.dao.entity.Exercice;
import com.fitcoach.fitcoach.dtos.ExerciceDTO;
import com.fitcoach.fitcoach.mappers.ExerciceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ExerciceServiceImpl implements ExerciceManager{
    private ExerciceMapper exerciceMapper;
    private ExerciceRepository exerciceRepository;
    @Override
    public Collection<ExerciceDTO> listExercice() {
        return  exerciceRepository.findAll().stream().map(e->exerciceMapper.map(e)).collect(Collectors.toList());
    }

    @Override
    public ExerciceDTO updateExercice(Long id,ExerciceDTO exerciceDTO) {
        Exercice exercice = exerciceRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        exercice.setId(id);
        exerciceDTO.setId(id);
        return exerciceMapper.map(exerciceRepository.save(exerciceMapper.map(exerciceDTO)));
    }
    @Override
    public ExerciceDTO addExercice(ExerciceDTO exerciceDTO) {
        return exerciceMapper.map(exerciceRepository.save(exerciceMapper.map(exerciceDTO)));
    }

    @Override
    public boolean deleteExercice(Long id) {
        Exercice exercice = exerciceRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        exerciceRepository.delete(exercice);
        return true;
    }
}
