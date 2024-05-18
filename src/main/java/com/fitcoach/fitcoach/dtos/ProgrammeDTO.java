package com.fitcoach.fitcoach.dtos;

import com.fitcoach.fitcoach.dao.entity.Coach;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgrammeDTO {
    private Long id;

    private String nom;

    private String description;

    private String attachment;


    private Date createdAt;

    private Date updateAt;

    private int duree; // en semaines
    private int seance;

    private String objectifs;


    private CoachDTO coach;

    private Collection<ClientDTO> members;
}
