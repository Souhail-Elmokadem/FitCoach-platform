package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.entity.Person;
import com.fitcoach.fitcoach.dtos.PersonDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PersonManager {
    PersonDTO getPersonWithMail(String email);

    PersonDTO updatePerson(MultipartFile avatar, String email, String firstName, String lastName, String profile) throws IOException;
    PersonDTO updatePerson( String email, String firstName, String lastName, String profile) throws IOException;

}
