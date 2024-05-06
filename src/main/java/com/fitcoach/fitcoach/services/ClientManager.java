package com.fitcoach.fitcoach.services;


import com.fitcoach.fitcoach.dtos.ClientDTO;
import com.fitcoach.fitcoach.dtos.CoachDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;


public interface ClientManager {





    //Client
    Page<ClientDTO> ListClients(String kw, int size,int page);
    ClientDTO AddClient(ClientDTO clientDTO);

    ClientDTO updateClient(Long id, ClientDTO clientDTO);

    boolean DeleteClient(Long id);



}
