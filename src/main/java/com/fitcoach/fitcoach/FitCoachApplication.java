package com.fitcoach.fitcoach;



import com.fitcoach.fitcoach.dao.Repository.PersonRepository;
import com.fitcoach.fitcoach.dao.entity.Person;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import com.fitcoach.fitcoach.dtos.CoachDTO;
import com.fitcoach.fitcoach.dtos.ProgrammeDTO;
import com.fitcoach.fitcoach.enums.Role;
import com.fitcoach.fitcoach.mappers.ClientMapper;
import com.fitcoach.fitcoach.services.ClientManager;
import com.fitcoach.fitcoach.services.CoachManager;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@SpringBootApplication
@AllArgsConstructor
public class FitCoachApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FitCoachApplication.class, args);
    }



    private CoachManager coachManager;
    private ClientManager clientManager;

    private ClientMapper clientMapper;

    private PersonRepository personRepository;
    @Override
    public void run(String... args) throws Exception {
         //CoachDTO coachDTO = new CoachDTO(null,"souhail","el mokadem","ahmed@google",null,new Date(),new Date());
         //ClientDTO clientDTO = new ClientDTO(null,"client 1","clientlast","",new Date(),new Date(),null,null);
//        Client client = clientMapper.map(clientDTO);
        //System.out.println(client.toString());
         //clientManager.DeleteClient(Long.valueOf(502));
         //adminManager.updateCoach(clientDTO);
        //Person p = new Person(null,"souhail","elm","souhail@login.ma","",new Date(),new Date(),"1234", Role.ADMIN));
        //personRepository.save(p);
       // CoachDTO c = new CoachDTO(null,"Mohammed","Salhi","");
        //coachManager.AddCoach(coachDTO);


//         clientDTO.setAvatar("kkk com");
//         clientManager.updateClient(Long.valueOf(303),clientDTO);
//        Stream.of("souhail","ahmed","mossa").forEach(e -> {
//            CoachDTO coachDTO = new CoachDTO(null,e,e,"",new Date(),new Date());
//            adminManager.AddCoach(coachDTO);
//        });
//        System.out.println(("hello"));
//        adminManager.ListCoach().stream().forEach(c->{
//            System.out.println(c);
//        });

    }
}
