package com.fitcoach.fitcoach.web;


import com.fitcoach.fitcoach.dao.entity.Person;
import com.fitcoach.fitcoach.dtos.PersonDTO;
import com.fitcoach.fitcoach.mappers.PersonMapper;
import com.fitcoach.fitcoach.services.PersonManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController

@RequestMapping("/person")
@AllArgsConstructor
public class PersonControllerApi {


    PersonManager personManager;

    @GetMapping
    public PersonDTO getPersonWithEmail(@RequestParam("email") String email){
            return personManager.getPersonWithMail(email);
    }

    @PutMapping
    public ResponseEntity<PersonDTO> editPerson(@RequestParam(value = "avatar",required = false) MultipartFile avatar,
                                                @RequestParam("email") String email,
                                                @RequestParam("firstName") String firstName,
                                                @RequestParam("lastName") String lastName,
                                                @RequestParam("profile") String profile
                                                ) throws IOException {
        if(avatar!=null){
            PersonDTO updatedPerson = personManager.updatePerson(avatar, email, firstName, lastName,profile);
            return ResponseEntity.ok(updatedPerson);
        }else {

            PersonDTO updatedPerson = personManager.updatePerson( email, firstName, lastName,profile);
            return ResponseEntity.ok(updatedPerson);
        }

    }

}
