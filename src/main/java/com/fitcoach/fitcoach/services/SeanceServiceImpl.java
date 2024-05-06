package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.Repository.SeanceRepository;
import com.fitcoach.fitcoach.dao.entity.Seance;
import com.fitcoach.fitcoach.dtos.SeanceDTO;
import com.fitcoach.fitcoach.mappers.SeanceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SeanceServiceImpl implements SeanceManager{
    private SeanceMapper seanceMapper;
    private SeanceRepository seanceRepository;

    @Override
    public Collection<SeanceDTO> ListSeances() {
        return seanceRepository.findAll().stream().map(s->seanceMapper.map(s)).collect(Collectors.toList());
    }

    @Override
    public SeanceDTO AddSeance(SeanceDTO seanceDTO) {
        return seanceMapper.map(seanceRepository.save(seanceMapper.map(seanceDTO)));
    }

    @Override
    public SeanceDTO updateSeance(Long id, SeanceDTO seanceDTO) {
        Seance seance = seanceMapper.map(seanceDTO);
        seance.setId(id);
        return seanceMapper.map(seanceRepository.save(seance));
    }

    @Override
    public boolean DeleteSeance(Long id) {
        Seance seance = seanceRepository.findById(id).orElseThrow(()->new RuntimeException());
        seanceRepository.delete(seance);
        return true;
    }
}
