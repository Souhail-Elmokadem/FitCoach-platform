package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Programme;
import com.fitcoach.fitcoach.dtos.ProgrammeDTO;
import org.springframework.beans.BeanUtils;

import java.util.stream.Collectors;

public class ProgrameMapperImpl implements ProgrammeMapper{
    CoachMapper coachMapper;
    ClientMapper clientMapper;
    @Override
    public ProgrammeDTO map(Programme programme) {
        ProgrammeDTO p = new ProgrammeDTO();

        BeanUtils.copyProperties(programme,p);


        return p;
    }

    @Override
    public Programme map(ProgrammeDTO programmeDTO) {
        Programme p = new Programme();
        BeanUtils.copyProperties(programmeDTO,p);

        return p;
    }
}
