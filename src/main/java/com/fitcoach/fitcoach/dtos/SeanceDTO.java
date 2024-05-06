package com.fitcoach.fitcoach.dtos;

import com.fitcoach.fitcoach.dao.entity.Programme;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeanceDTO {
    private Long id;

    private String nom;

    private String description;
    private Date createdAt;

    private Date updateAt;
    private String objectifs;

    private Programme programme;
}
