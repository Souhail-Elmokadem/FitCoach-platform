package com.fitcoach.fitcoach.mappers;


import com.fitcoach.fitcoach.dao.entity.Paiement;
import com.fitcoach.fitcoach.dtos.PaiementDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PaiementMapper  {
    PaiementDTO map(Paiement paiement);
    Paiement map(PaiementDTO paiementDTO);
}
