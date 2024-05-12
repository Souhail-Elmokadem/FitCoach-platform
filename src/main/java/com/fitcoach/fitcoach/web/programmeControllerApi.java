package com.fitcoach.fitcoach.web;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitcoach.fitcoach.dao.Repository.ProgrammeRepository;
import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dtos.ApiResponse;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import com.fitcoach.fitcoach.dtos.ProgrammeDTO;
import com.fitcoach.fitcoach.services.ProgrammeManger;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/program")
public class programmeControllerApi {

    private ProgrammeManger programmeManger;
    @GetMapping("/list")
    public ApiResponse<ProgrammeDTO> list(@RequestParam(name = "Search", defaultValue = "") String kw,
                                          @RequestParam(name = "size", defaultValue = "6") int size,
                                          @RequestParam(name = "page", defaultValue = "0") int page
                                          ){

        Page<ProgrammeDTO> programmeDTOS = programmeManger.listProgrammes(kw,size,page);
        return  new ApiResponse<>(programmeDTOS.getContent(),(int) programmeDTOS.getTotalElements());
    }
    @GetMapping("/listprogrambycoach")
    public ApiResponse<ProgrammeDTO> listprogramcoach(
                                          @RequestParam(name = "Search", defaultValue = "") String kw,
                                          @RequestParam(name = "size", defaultValue = "6") int size,
                                          @RequestParam(name = "page", defaultValue = "0") int page,
                                          @RequestParam(name = "coachemail") String coachemail
    ){

        Page<ProgrammeDTO> programmeDTOS = programmeManger.listProgrammesCoach(kw,size,page,coachemail);
        return  new ApiResponse<>(programmeDTOS.getContent(),(int) programmeDTOS.getTotalElements());
    }
    @PostMapping("/create")
    public ProgrammeDTO addProgram(@RequestParam("attachment") MultipartFile attachment,
                                   @RequestParam("duree") int duree,
                                   @RequestParam("nom") String nom,
                                   @RequestParam("seance") int seance,
                                   @RequestParam("description") String description,
                                   @RequestParam("objectifs") String objectifs,
                                   @RequestParam("coache") String coachemail,
                                   @RequestParam("clients") String clientsJson
                                   )throws IOException {
//System.out.println(attachment.getOriginalFilename() + " ",+duree +" "+nom+" "+seance+" "+description+" ");
        ObjectMapper objectMapper = new ObjectMapper();
        Collection<ClientDTO> clients = objectMapper.readValue(clientsJson, new TypeReference<Collection<ClientDTO>>() {});

        ProgrammeDTO programmeDTO = new ProgrammeDTO(null,nom,description,null,new Date(),new Date(),duree,seance,objectifs,null);

       return programmeManger.AddProgramme(attachment,programmeDTO,coachemail,clients);
//        return  new ProgrammeDTO();

    }

}
