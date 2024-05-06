package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Exercice;
import com.fitcoach.fitcoach.dtos.ExerciceDTO;
import org.springframework.beans.BeanUtils;

public class ExerciceMapperImpl implements ExerciceMapper{
    @Override
    public ExerciceDTO map(Exercice exercice) {
        ExerciceDTO exerciceDTO = new ExerciceDTO();
        BeanUtils.copyProperties(exercice,exerciceDTO);
        return exerciceDTO;
    }

    @Override
    public Exercice map(ExerciceDTO exerciceDTO) {
        Exercice exercice = new Exercice();
        BeanUtils.copyProperties(exerciceDTO,exercice);
        return exercice;
    }
}
