package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Person;
import com.fitcoach.fitcoach.dtos.PersonDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {
    public PersonDTO map(Person person);

    public Person map(PersonDTO person);

}
