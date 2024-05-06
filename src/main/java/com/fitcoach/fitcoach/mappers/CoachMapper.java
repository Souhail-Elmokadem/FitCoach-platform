package com.fitcoach.fitcoach.mappers;


import com.fitcoach.fitcoach.dao.entity.Coach;
import com.fitcoach.fitcoach.dtos.CoachDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
