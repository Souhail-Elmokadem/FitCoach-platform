package com.fitcoach.fitcoach.web;


import com.fitcoach.fitcoach.dtos.ApiResponse;
import com.fitcoach.fitcoach.dtos.CoachDTO;
import com.fitcoach.fitcoach.services.CoachManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/coach")
public class CoachControllerApi {

    private CoachManager coachManager;
    @GetMapping("/list")
    public ApiResponse<CoachDTO> list(@RequestParam(name = "Search", defaultValue = "") String kw,
                                      @RequestParam(name = "size", defaultValue = "2") int size,
                                      @RequestParam(name = "page", defaultValue = "0") int page){
        Page<CoachDTO> coachDTOPages = coachManager.ListCoach(kw,page,size);

        return new ApiResponse<>(coachDTOPages.getContent(),(int)coachDTOPages.getTotalElements());
    }
    @PostMapping("/create")
    public CoachDTO addCoach(@RequestParam("avatar") MultipartFile avatar,
                             @RequestParam("email") String email,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName
                             ) throws IOException {

        CoachDTO coachDTO = new CoachDTO(null,firstName,lastName,email,null,new Date(),new Date());
           return coachManager.AddCoach(avatar,coachDTO);
//        return coachDTO;
    }

}
