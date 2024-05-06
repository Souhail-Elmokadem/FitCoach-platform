package com.fitcoach.fitcoach.dao.entity;

import com.fitcoach.fitcoach.enums.StatusPaiement;
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
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;



    private double montant;

    private String datePaiement;

    @Enumerated(EnumType.STRING)
    private StatusPaiement statusPaiement;
}
