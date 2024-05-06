package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.Repository.ProgrammeRepository;
import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Coach;
import com.fitcoach.fitcoach.dao.entity.Programme;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import com.fitcoach.fitcoach.dtos.CoachDTO;
import com.fitcoach.fitcoach.dtos.ProgrammeDTO;
import com.fitcoach.fitcoach.mappers.CoachMapper;
import com.fitcoach.fitcoach.mappers.ProgrammeMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Host
@AllArgsConstructor
public class ProgrammeServiceimpl implements ProgrammeManger{

    ProgrammeRepository programmeRepository;
    CoachManager coachManager;
    CoachMapper coachMapper;
   private ProgrammeMapper programmeMapper;

   public String uploadfile(MultipartFile file) throws IOException {
       String filePATH = Host.LOCAL + file.getOriginalFilename(); // saved path locally
       String fileUrl = Host.HOSTNAME + file.getOriginalFilename();
       Files.write(Paths.get(filePATH),file.getBytes());
       return fileUrl;
   }
    @Override
    public Page<ProgrammeDTO> listProgrammes(String kw,int size,int page) {
        Page<ProgrammeDTO> programmeDTOS = programmeRepository.findByNomContaining(kw, PageRequest.of(page,size))
                .map(p -> {
                    ProgrammeDTO prog =programmeMapper.map(p);
                    CoachDTO coachDTO = coachMapper.coachToDTO(p.getCoach());
                    prog.setCoach(coachDTO);
                    return prog;
                });
        return programmeDTOS;
    }

    @Override
    public ProgrammeDTO AddProgramme(MultipartFile attachment,ProgrammeDTO programmeDTO,String coachemail) throws IOException {
        Coach coach = coachMapper.DTOToCoach(coachManager.getCoach(coachemail));
       Programme p = programmeMapper.map(programmeDTO);
        p.setAttachment(uploadfile(attachment));
        p.setCoach(coach);
        coach.setProgrammes(List.of(p));
        coachManager.AddCoach(coachMapper.coachToDTO(coach));

         ProgrammeDTO programmeDTO1 = programmeMapper.map(programmeRepository.save(p));
         programmeDTO1.setCoach(coachMapper.coachToDTO(coach));
         return programmeDTO1;
    }

    @Override
    public ProgrammeDTO updateProgramme(Long id, ProgrammeDTO programmeDTO) {
        Programme p = programmeMapper.map(programmeDTO);
        p.setId(id);
        return programmeMapper.map(programmeRepository.save(p));
    }

    @Override
    public boolean DeleteProgramme(Long id) {
        Programme p = programmeRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Not Found Programme"));
        programmeRepository.delete(p);
        return true;
    }
}
