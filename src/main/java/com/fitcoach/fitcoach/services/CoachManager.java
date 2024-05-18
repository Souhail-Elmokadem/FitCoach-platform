package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dtos.CoachDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

public interface CoachManager {
    Page<CoachDTO> ListCoach(String kw, int page, int size);
    CoachDTO AddCoach(CoachDTO coachDTO);

    CoachDTO getCoach(String email);

    CoachDTO getCoach(Long coachid);

    CoachDTO AddCoach(MultipartFile avatar, CoachDTO coachDTO) throws IOException;

    CoachDTO updateCoach(Long id, CoachDTO coachDTO);
    boolean DeleteCoach(Long id);


    CoachDTO getCoachByClient(String clientemail);
}
