package com.fitcoach.fitcoach.dtos;

import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.enums.StatusPaiement;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaiementDTO {
    private Long id;


    private Client client;



    private double montant;

    private String datePaiement;

    private StatusPaiement statusPaiement;
}
