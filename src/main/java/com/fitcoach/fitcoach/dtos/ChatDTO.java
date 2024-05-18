package com.fitcoach.fitcoach.dtos;

import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Coach;
import com.fitcoach.fitcoach.dao.entity.Message;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
    private Long id;

    private ClientDTO client;


    private  CoachDTO coach;

    Collection<MessageDTO> messageDTOS;

}
