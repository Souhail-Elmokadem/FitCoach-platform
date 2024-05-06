package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClientMapperImpl implements ClientMapper{
    @Override
    public ClientDTO map(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client,clientDTO);
        return clientDTO;
    }

    @Override
    public Client map(ClientDTO clientDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO,client);
        return client;

    }
}
