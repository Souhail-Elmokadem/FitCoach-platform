package com.fitcoach.fitcoach.utils;


import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Seance;
import com.fitcoach.fitcoach.dtos.ClientDTO;
//import com.fitcoach.fitcoach.mappers.ClientMapper;
import com.fitcoach.fitcoach.mappers.*;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("com.fitcoach.fitcoach.mappers")
public class ConfigMapper {

    @Bean
    public ClientMapper clientMapper() {
        return new ClientMapperImpl();
    }

    @Bean
    public ProgrammeMapper programmeMapper() {
        return new ProgrameMapperImpl();
    }

    @Bean
    public SeanceMapper seanceMapper(){
        return new SeanceMapperImpl();
    }


    @Bean
    public ExerciceMapper exerciceMapper(){
        return new ExerciceMapperImpl();
    }

    @Bean
    public PaiementMapper paiementMapper(){
        return new PaiementMapperImpl();
    }


    @Bean
    public ChatMapper chatMapper(){
        return new ChatMapperImpl();
    }

    @Bean
    public Messagemapper messagemapper() { return new MessageMapperImpl(); }
}
