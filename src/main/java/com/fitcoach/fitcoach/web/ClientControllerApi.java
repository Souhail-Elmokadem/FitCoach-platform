package com.fitcoach.fitcoach.web;


import com.fitcoach.fitcoach.dtos.ApiResponse;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import com.fitcoach.fitcoach.services.ClientManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientControllerApi {

    private ClientManager clientManager;


//    @PreAuthorize("hasAuthority('SCOPE_COACH') and hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/list")
    public ApiResponse<ClientDTO> list(@RequestParam(name = "Search", defaultValue = "") String kw,
                            @RequestParam(name = "size", defaultValue = "2") int size,
                            @RequestParam(name = "page", defaultValue = "0") int page){
        Page<ClientDTO> clientDTOs = clientManager.ListClients(kw,size,page);
        return new ApiResponse<>(clientDTOs.getContent(),(int)clientDTOs.getTotalElements());
    }

    @PostMapping("/create")
    public ClientDTO addClient(@RequestBody ClientDTO clientDTO){
        return clientManager.AddClient(clientDTO);
    }

    @PutMapping("/update")
    public ClientDTO updateClient(@RequestBody ClientDTO clientDTO){
        return clientManager.updateClient(clientDTO.getId(),clientDTO);
    }
    @DeleteMapping("/delete")
    public boolean deleteClient(@RequestBody ClientDTO clientDTO){
        return clientManager.DeleteClient(clientDTO.getId());
    }



}
