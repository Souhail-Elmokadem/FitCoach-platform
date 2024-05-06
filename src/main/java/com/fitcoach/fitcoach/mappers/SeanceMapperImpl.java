package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Seance;
import com.fitcoach.fitcoach.dtos.SeanceDTO;
import org.springframework.beans.BeanUtils;

public class SeanceMapperImpl implements SeanceMapper{
    @Override
    public SeanceDTO map(Seance seance) {
        SeanceDTO seanceDTO = new SeanceDTO();
        BeanUtils.copyProperties(seance,seanceDTO);
        return seanceDTO;
    }

    @Override
    public Seance map(SeanceDTO seanceDTO) {
        Seance seance = new Seance();
        BeanUtils.copyProperties(seanceDTO,seance);
        return seance;
    }
}
