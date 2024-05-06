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
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String description;
    private Date createdAt;

    private Date updateAt;
    private String objectifs;

    @ManyToOne
    private Programme programme;

    @ManyToMany
    private Collection<Exercice> exercices;



}
