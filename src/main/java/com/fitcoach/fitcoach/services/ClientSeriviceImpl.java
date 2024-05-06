package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.Repository.ClientRepository;
import com.fitcoach.fitcoach.dao.Repository.CoachRepository;
import com.fitcoach.fitcoach.dao.Repository.ProgrammeRepository;
import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Coach;
import com.fitcoach.fitcoach.dao.entity.Programme;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import com.fitcoach.fitcoach.dtos.CoachDTO;

//import com.fitcoach.fitcoach.mappers.ClientMapper;
import com.fitcoach.fitcoach.mappers.ClientMapper;
import com.fitcoach.fitcoach.mappers.CoachMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
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


    @Override
    public Page<ClientDTO> ListClients(String kw,int size,int page) {
        Page<ClientDTO> clients= clientRepository.findByEmailContainingOrFirstNameContainingOrLastNameContaining(kw,kw,kw, PageRequest.of(page,size))
        .map(clientMapper::map);
        return clients;
    }

    @Override
    public ClientDTO AddClient(ClientDTO clientDTO) {
        Client client = clientMapper.map(clientDTO);
        client.setCreatedAt(new Date());
        client.setUpdateAt(new Date());
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
}
