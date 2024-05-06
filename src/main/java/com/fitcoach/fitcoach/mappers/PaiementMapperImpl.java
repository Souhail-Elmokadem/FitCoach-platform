package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Paiement;
import com.fitcoach.fitcoach.dtos.PaiementDTO;
import org.springframework.beans.BeanUtils;

public class PaiementMapperImpl implements PaiementMapper{
    @Override
    public PaiementDTO map(Paiement paiement) {
        PaiementDTO paiementDTO = new PaiementDTO();
        BeanUtils.copyProperties(paiement,paiementDTO);
        return paiementDTO;
    }

    @Override
    public Paiement map(PaiementDTO paiementDTO) {
        Paiement paiement = new Paiement();
        BeanUtils.copyProperties(paiementDTO,paiement);
        return paiement;
    }
}
