package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Seance;
import com.fitcoach.fitcoach.dtos.SeanceDTO;
import org.mapstruct.Mapper;

@Mapper
public interface SeanceMapper {
    public SeanceDTO map(Seance seance);
    public Seance map(SeanceDTO seanceDTO);
}
