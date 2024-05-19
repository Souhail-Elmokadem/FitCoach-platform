package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.Repository.ClientRepository;
import com.fitcoach.fitcoach.dao.Repository.CoachRepository;
import com.fitcoach.fitcoach.dao.Repository.ProgrammeRepository;
import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Coach;
import com.fitcoach.fitcoach.dao.entity.Programme;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import com.fitcoach.fitcoach.dtos.CoachDTO;
import com.fitcoach.fitcoach.dtos.ProgrammeDTO;
import com.fitcoach.fitcoach.mappers.ClientMapper;
import com.fitcoach.fitcoach.mappers.CoachMapper;
import com.fitcoach.fitcoach.mappers.ProgrammeMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Host
@AllArgsConstructor
public class ProgrammeServiceimpl implements ProgrammeManger{

    ProgrammeRepository programmeRepository;
    CoachManager coachManager;
    CoachMapper coachMapper;
    ClientManager clientManager;
    ClientMapper clientMapper;
    ClientRepository clientRepository;
    CoachRepository coachRepository;
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
    public ProgrammeDTO AddProgramme(MultipartFile attachment,ProgrammeDTO programmeDTO,String coachemail,Collection<ClientDTO> clients) throws IOException {
        Coach coach = coachRepository.findByEmail(coachemail);
        Programme p = programmeMapper.map(programmeDTO);

        p.setAttachment(uploadfile(attachment));
        p.setCoach(coach);
        p.setClients(clients.stream().map(clientMapper::map).collect(Collectors.toList()));

//        coach.setProgrammes(List.of(p));
//
//
//        //save coach
//         coachRepository.save(coach);
         //save program
         ProgrammeDTO programmeDTO1 = programmeMapper.map(programmeRepository.save(p));

       //save clients
        clients.stream().map(clientMapper::map).forEach(c -> {
            Client cl = clientRepository.findByEmail(c.getEmail());
            cl.setProgramme(p);
            clientRepository.save(cl);
        });

         programmeDTO1.setCoach(coachMapper.coachToDTO(coach));
         return programmeDTO1;
    }

    @Override
    public ProgrammeDTO updateProgramme(Long id, ProgrammeDTO programmeDTO, Collection<ClientDTO> clientDTOS) {
        Programme programme = programmeRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
        clientDTOS.stream().forEach(cl->{
            Client client = clientRepository.findByEmail(cl.getEmail());
            client.setProgramme(programme);
            clientRepository.save(client);
        });
        programme.setId(id);
        programme.setClients(clientDTOS.stream().map(clientMapper::map).collect(Collectors.toList()));
        programme.setNom(programmeDTO.getNom());
        programme.setDescription(programmeDTO.getDescription());
        programme.setUpdateAt(new Date());
        programme.setObjectifs(programmeDTO.getObjectifs());
        programme.setDuree(programmeDTO.getDuree());
        programme.setSeance(programmeDTO.getSeance());


        return programmeMapper.map(programmeRepository.save(programme));
    }



    @Override
    public boolean DeleteProgramme(Long id) {
        Programme p = programmeRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Not Found Programme"));
        programmeRepository.delete(p);
        return true;
    }

    @Override
    public Page<ProgrammeDTO> listProgrammesCoach(String kw, int size, int page, String coachemail) {
        Coach coach = coachRepository.findByEmail(coachemail);
        List<ProgrammeDTO> programmeDTOS = programmeRepository
                .findByNomContaining(kw, PageRequest.of(page,size)).stream().filter(p -> {
                    if (p.getCoach()!=null){
                        return p.getCoach().equals(coach);
                    }
                    return false;
                })
                .map(p -> {
                    ProgrammeDTO prog =programmeMapper.map(p);
                    CoachDTO coachDTO = coachMapper.coachToDTO(p.getCoach());
                    Collection<Client> clients = p.getClients();
                    prog.setCoach(coachDTO);
                    prog.setMembers(clients.stream().map(clientMapper::map).collect(Collectors.toList()));
                    return prog;
                }).collect(Collectors.toList());
        return new PageImpl<>(programmeDTOS,PageRequest.of(page,size),programmeDTOS.size());
    }

    @Override
    public ProgrammeDTO programClient(String clientemail) {
        Programme programme = clientRepository.findByEmail(clientemail).getProgramme();
        ProgrammeDTO programmeDTO = programmeMapper.map(programme);
        programmeDTO.setCoach(coachMapper.coachToDTO(programme.getCoach()));
        return programmeDTO;
    }

    @Override
    public ProgrammeDTO getProgramById(Long id) {
       Programme programme = programmeRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found Program"));
        ProgrammeDTO programmeDTO =  programmeMapper.map(programme);
        programmeDTO.setCoach(coachMapper.coachToDTO(programme.getCoach()));
        programmeDTO.setMembers(programme.getClients().stream().map(clientMapper::map).collect(Collectors.toList()));
        return programmeDTO;
    }

    @Override
    public Boolean deleteProgram(Long id) {
       Programme programme = programmeRepository.findById(id).orElseThrow(()->new RuntimeException("Not found Program"));
       programme.getClients().stream().forEach(c->{
           c.setProgramme(null);
           clientRepository.save(c);
       });
       programme.getCoach().setProgrammes(null);
       try {
           programmeRepository.deleteById(id);
           return true;
       }catch (Exception e){
           System.out.println(e.getMessage());
           return false;
       }


    }
}
