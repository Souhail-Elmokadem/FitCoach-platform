package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dtos.CoachDTO;
import com.fitcoach.fitcoach.dtos.SeanceDTO;

import java.util.Collection;

public interface SeanceManager {
    Collection<SeanceDTO> ListSeances();
    SeanceDTO AddSeance(SeanceDTO seanceDTO);

    SeanceDTO updateSeance(Long id,SeanceDTO seanceDTO);
    boolean DeleteSeance(Long id);
}
