package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.Repository.PersonRepository;
import com.fitcoach.fitcoach.dao.entity.Person;
import com.fitcoach.fitcoach.dtos.PersonDTO;
import com.fitcoach.fitcoach.mappers.PersonMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonManager{


    private PersonMapper personMapper;
    private PersonRepository personRepository;

    public String uploadfile(MultipartFile file) throws IOException {
        String filePATH = Host.LOCAL + file.getOriginalFilename(); // saved path locally
        String fileUrl = Host.HOSTNAME + file.getOriginalFilename();
        Files.write(Paths.get(filePATH),file.getBytes());
        return fileUrl;
    }
    @Override
    public PersonDTO getPersonWithMail(String email) {
        return personMapper.map(personRepository.findByEmail(email));
    }


    @Override
    public PersonDTO updatePerson(MultipartFile avatar, String email, String firstName, String lastName, String profile) throws IOException {
        Person person = personRepository.findByEmail(email);
        person.setEmail(email);
        person.setProfile(profile);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAvatar(uploadfile(avatar));
        Person personSaved = personRepository.save(person);
        return personMapper.map(personSaved);
    }

    @Override
    public PersonDTO updatePerson(String email, String firstName, String lastName, String profile) throws IOException {
        Person person = personRepository.findByEmail(email);
        person.setEmail(email);
        person.setProfile(profile);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        Person personSaved = personRepository.save(person);
        return personMapper.map(personSaved);
    }
}
