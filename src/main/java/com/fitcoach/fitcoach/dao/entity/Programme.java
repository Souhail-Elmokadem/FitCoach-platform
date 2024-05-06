package com.fitcoach.fitcoach.dao.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Programme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String description;

    private String attachment;

    private Date createdAt;

    private Date updateAt;

    private int duree; // en semaines
    private int seance; // per semaine


    private String objectifs;

    @ManyToOne
    private Coach coach;

    @OneToMany(mappedBy = "programme")
    private Collection<Client> clients;

    @OneToMany(mappedBy = "programme")
    private Collection<Seance> seances;



}
