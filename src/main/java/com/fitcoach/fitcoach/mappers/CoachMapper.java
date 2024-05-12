package com.fitcoach.fitcoach.mappers;


import com.fitcoach.fitcoach.dao.entity.Coach;
import com.fitcoach.fitcoach.dtos.CoachDTO;
import com.fitcoach.fitcoach.enums.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CoachMapper {
    public CoachDTO coachToDTO(Coach coach){
        CoachDTO coachDTO = new CoachDTO();
        BeanUtils.copyProperties(coach,coachDTO);
        return coachDTO;
    }
    public Coach DTOToCoach(CoachDTO coachDTO){
        Coach coach = new Coach();
        BeanUtils.copyProperties(coachDTO,coach);
        return coach;
    }
}
