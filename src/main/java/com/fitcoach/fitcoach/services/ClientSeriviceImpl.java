package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.Repository.ClientRepository;
import com.fitcoach.fitcoach.dao.Repository.CoachRepository;
import com.fitcoach.fitcoach.dao.Repository.ProgrammeRepository;
import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Coach;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import com.fitcoach.fitcoach.dtos.CoachDTO;

//import com.fitcoach.fitcoach.mappers.ClientMapper;
import com.fitcoach.fitcoach.mappers.ClientMapper;
import com.fitcoach.fitcoach.mappers.CoachMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
        List<ClientDTO> clientDTOS = clientRepository
                .findByEmailContainingOrFirstNameContainingOrLastNameContaining(kw,kw,kw,PageRequest.of(page,size))
                .stream().filter(c -> {
                    if (c.getCoach()!=null){
                       return c.getCoach().equals(coach);
                    }
                    return false;
                }).map(clientMapper::map).collect(Collectors.toList());

        return new PageImpl<>(clientDTOS,PageRequest.of(page,size),clientDTOS.size());
    }

    @Override
    public ClientDTO AddClientToCoach(String clientemail, String coachemail) {
        Client client = clientRepository.findByEmail(clientemail);
        Coach coach = coachRepository.findByEmail(coachemail);

        client.setCoach(coach);
        clientRepository.save(client);

        //coach.setMembers(List.of(client));
        coachRepository.save(coach);

        return clientMapper.map(client);
    }
    @Override

    public ClientDTO getClient(String email){
       return clientMapper.map(clientRepository.findByEmail(email));
    }
}
