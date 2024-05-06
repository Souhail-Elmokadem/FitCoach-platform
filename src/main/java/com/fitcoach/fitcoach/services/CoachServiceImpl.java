package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.Repository.ClientRepository;
import com.fitcoach.fitcoach.dao.Repository.CoachRepository;
import com.fitcoach.fitcoach.dao.Repository.ProgrammeRepository;
import com.fitcoach.fitcoach.dao.entity.Coach;
import com.fitcoach.fitcoach.dao.entity.Programme;
import com.fitcoach.fitcoach.dtos.CoachDTO;
import com.fitcoach.fitcoach.mappers.CoachMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
@Host
public class CoachServiceImpl implements CoachManager{
    private CoachRepository coachRepository;
    private ClientRepository clientRepository;
    private ProgrammeRepository programmeRepository;

    private CoachMapper coachMapper;
    @Override
    public Page<CoachDTO> ListCoach(String kw, int page, int size) {

        Page<Coach> coachs = coachRepository.findByEmailContainingOrFirstNameContainingOrLastNameContaining(kw,kw,kw, PageRequest.of(page,size));
        Page<CoachDTO> coachDTOS = coachs.map(coachMapper::coachToDTO);
        return coachDTOS;
    }

    @Override
    public CoachDTO AddCoach(CoachDTO coachDTO) {
        Coach coach = coachMapper.DTOToCoach(coachDTO);
        return coachMapper.coachToDTO(coachRepository.save(coach));
    }

    @Override
    public CoachDTO getCoach(String email) {
        return coachMapper.coachToDTO(coachRepository.findByEmail(email));
    }

    @Override
    public CoachDTO AddCoach(MultipartFile avatar, CoachDTO coachDTO) throws IOException {
        Coach coach = coachMapper.DTOToCoach(coachDTO);

        String avatarPATH = Host.LOCAL + avatar.getOriginalFilename(); // saved path locally
        String avatarUrl = Host.HOSTNAME + avatar.getOriginalFilename();
        Files.write(Paths.get(avatarPATH),avatar.getBytes());

        coach.setAvatar(avatarUrl);
        return coachMapper.coachToDTO(coachRepository.save(coach));
    }

    @Override
    public CoachDTO updateCoach(Long id, CoachDTO coachDTO) {
        Coach coach = coachMapper.DTOToCoach(coachDTO);
        coach.setId(id);
        return coachMapper.coachToDTO(coachRepository.save(coach));
    }

    @Override
    public boolean DeleteCoach(Long id) {
        try {
            Optional<Coach> c = coachRepository.findById(id);
            Collection<Programme> programmes = programmeRepository.findAllByCoach(c.get());
            programmes.stream().forEach(p->programmeRepository.delete(p));
            coachRepository.delete(c.get());
            return  true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  false;
        }

    }
}
