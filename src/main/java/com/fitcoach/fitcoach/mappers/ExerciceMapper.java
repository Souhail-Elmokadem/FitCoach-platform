package com.fitcoach.fitcoach.mappers;


import com.fitcoach.fitcoach.dao.entity.Exercice;
import com.fitcoach.fitcoach.dtos.ExerciceDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ExerciceMapper {
    ExerciceDTO map(Exercice exercice);
    Exercice map(ExerciceDTO exerciceDTO);
}
