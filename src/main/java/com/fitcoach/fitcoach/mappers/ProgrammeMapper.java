package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Programme;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import com.fitcoach.fitcoach.dtos.ProgrammeDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProgrammeMapper {

    public ProgrammeDTO map(Programme programme);
    public Programme map(ProgrammeDTO programmeDTO);
}
