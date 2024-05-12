package com.fitcoach.fitcoach.services;


import com.fitcoach.fitcoach.dao.entity.Coach;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import com.fitcoach.fitcoach.dtos.CoachDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;


public interface ClientManager {





    //Client
    Page<ClientDTO> ListClients(String kw, int size,int page);

    ClientDTO AddClient(MultipartFile avatar, ClientDTO clientDTO) throws IOException;

    ClientDTO updateClient(Long id, ClientDTO clientDTO);

    boolean DeleteClient(Long id);



    Page<ClientDTO> ListClientsByCoach(String kw, int size, int page, String coachemail);

    ClientDTO AddClientToCoach(String clientemail, String coachemail);

    ClientDTO getClient(String email);
}
