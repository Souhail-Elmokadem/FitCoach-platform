package com.fitcoach.fitcoach.web;


import com.fitcoach.fitcoach.dao.entity.Coach;
import com.fitcoach.fitcoach.dtos.ApiResponse;
import com.fitcoach.fitcoach.dtos.ChatDTO;
import com.fitcoach.fitcoach.dtos.ClientDTO;
import com.fitcoach.fitcoach.dtos.CoachDTO;
import com.fitcoach.fitcoach.enums.Role;
import com.fitcoach.fitcoach.services.ChatManager;
import com.fitcoach.fitcoach.services.ClientManager;
import com.fitcoach.fitcoach.services.CoachManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientControllerApi {

    private ClientManager clientManager;

    private CoachManager coachManager;
    private ChatManager chatManager;

//    @PreAuthorize("hasAuthority('SCOPE_COACH') and hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/list")
    public ApiResponse<ClientDTO> list(@RequestParam(name = "Search", defaultValue = "") String kw,
                            @RequestParam(name = "size", defaultValue = "10") int size,
                            @RequestParam(name = "page", defaultValue = "0") int page){
        Page<ClientDTO> clientDTOs = clientManager.ListClients(kw,size,page);
        return new ApiResponse<>(clientDTOs.getContent(),(int)clientDTOs.getTotalElements());
    }
    @GetMapping("/listClientByCoach")
    public ApiResponse<ClientDTO> listClient(@RequestParam(name = "Search",required = false, defaultValue = "") String kw,
                                       @RequestParam(name = "size", defaultValue = "10") int size,
                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                       @RequestParam(name = "coachemail" ) String coachemail ){
        Page<ClientDTO> clientDTOs = clientManager.ListClientsByCoach(kw,size,page,coachemail);
        return new ApiResponse<>(clientDTOs.getContent(),(int)clientDTOs.getTotalElements());
    }






    @PostMapping("/create")
    public ClientDTO addClient(@RequestParam("avatar") MultipartFile avatar,
                               @RequestParam("email") String email,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName) throws IOException {
        ClientDTO clientDTO = new ClientDTO(null,firstName,lastName,email,null,new Date(), Role.USER,new Date(),null,null);
        return clientManager.AddClient(avatar,clientDTO);
    }

   // register client to a coach
    @PostMapping("/enroll")
    public ClientDTO enroll(@RequestParam("clientemail") String clientemail,@RequestParam("coachemail") String coachemail){

        //ClientDTO clientDTO = clientManager.getClient(clientemail);
        //CoachDTO coachDTO = coachManager.getCoach(coachemail);

        return clientManager.AddClientToCoach(clientemail,coachemail);
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
