package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.Repository.ChatRepository;
import com.fitcoach.fitcoach.dao.Repository.ClientRepository;
import com.fitcoach.fitcoach.dao.Repository.CoachRepository;
import com.fitcoach.fitcoach.dao.Repository.ProgrammeRepository;
import com.fitcoach.fitcoach.dao.entity.Chat;
import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Coach;
import com.fitcoach.fitcoach.dao.entity.Programme;
import com.fitcoach.fitcoach.dtos.ChatDTO;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import com.fitcoach.fitcoach.dtos.CoachDTO;

//import com.fitcoach.fitcoach.mappers.ClientMapper;
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
@AllArgsConstructor
public class ClientSeriviceImpl implements ClientManager {


    private CoachRepository coachRepository;
    private ClientRepository clientRepository;
    private ProgrammeRepository  programmeRepository;

    private CoachMapper coachMapper;
    private ClientMapper clientMapper;
    private CoachManager coachManager;

    private ChatRepository chatRepository;
    private  ChatManager chatManager;
    ProgrammeMapper programmeMapper;


    @Override
    public Page<ClientDTO> ListClients(String kw,int size,int page) {
        Page<ClientDTO> clients= clientRepository.findByEmailContainingOrFirstNameContainingOrLastNameContaining(kw,kw,kw, PageRequest.of(page,size))
        .map(clientMapper::map);
        return clients;
    }

    @Override
    public ClientDTO AddClient(MultipartFile avatar, ClientDTO clientDTO) throws IOException {

        String avatarPATH = Host.LOCAL + avatar.getOriginalFilename(); // saved path locally
        String avatarUrl = Host.HOSTNAME + avatar.getOriginalFilename();
        Files.write(Paths.get(avatarPATH),avatar.getBytes());

        Client client = clientMapper.map(clientDTO);
        client.setCreatedAt(new Date());
        client.setUpdateAt(new Date());
        client.setAvatar(avatarUrl);
        return clientMapper.map(clientRepository.save(client));
    }
    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Client client = clientMapper.map(clientDTO);
        client.setId(id);
        client.setUpdateAt(new Date());
        return clientMapper.map(clientRepository.save(client));
    }

    @Override
    public boolean DeleteClient(Long id) {
        try {
            Optional<Client> c = clientRepository.findById(id);
            clientRepository.delete(c.get());
            return  true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  false;
        }
    }


    @Override
    public Page<ClientDTO> ListClientsByCoach(String kw, int size, int page, String coachemail) {
        Coach coach = coachRepository.findByEmail(coachemail);
        if (coach == null) {
            // Handle the case where the coach is not found, e.g., throw an exception or return an empty page
            return Page.empty();
        }

        Page<Client> clientsPage = clientRepository.findByCoachAndKeyword(
                coach, kw, PageRequest.of(page, size));
        List<ClientDTO> clientDTOS = clientsPage.stream()
                .map(c -> {
                    ClientDTO clientDTO = clientMapper.map(c);
                    if (c.getProgramme() != null) {
                        clientDTO.setProgramme(programmeMapper.map(c.getProgramme()));
                    }
                    return clientDTO;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(clientDTOS, PageRequest.of(page, size), clientsPage.getTotalElements());
    }


    @Override
    public ClientDTO AddClientToCoach(String clientemail, String coachemail) {
        Client client = clientRepository.findByEmail(clientemail);
        Coach coach = coachRepository.findByEmail(coachemail);

        if (client.getProgramme() != null){
            Programme programme = client.getProgramme();
            programme.getClients().remove(client);
            programmeRepository.save(programme);
        }
        List<Chat> existingChats = chatRepository.findAllByClientAndCoach(client, coach);
        if (existingChats != null && !existingChats.isEmpty()) {
            chatRepository.deleteAll(existingChats);
        }

        client.setProgramme(null);

        client.setCoach(coach);
        clientRepository.save(client);

        //coach.setMembers(List.of(client));
        coachRepository.save(coach);


        Chat chat = new Chat();
        chat.setClient(client);
        chat.setCoach(coach);
        chatRepository.save(chat);
        return clientMapper.map(client);
    }
    @Override

    public ClientDTO getClient(String email){
       return clientMapper.map(clientRepository.findByEmail(email));
    }
}
