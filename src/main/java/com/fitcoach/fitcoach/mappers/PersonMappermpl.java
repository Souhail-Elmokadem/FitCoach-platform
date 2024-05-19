package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Person;
import com.fitcoach.fitcoach.dtos.PersonDTO;
import org.springframework.beans.BeanUtils;

public class PersonMappermpl implements PersonMapper{
    @Override
    public PersonDTO map(Person person) {
        PersonDTO personDTO = new PersonDTO();
        BeanUtils.copyProperties(person,personDTO);
        return personDTO;
    }

    @Override
    public Person map(PersonDTO personDTO) {
        Person person = new Person();
        BeanUtils.copyProperties(personDTO,person);
        return person;
    }
}
