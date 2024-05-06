package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper
public interface ClientMapper {
    ClientDTO map(Client client);
    Client map(ClientDTO clientDTO);


}