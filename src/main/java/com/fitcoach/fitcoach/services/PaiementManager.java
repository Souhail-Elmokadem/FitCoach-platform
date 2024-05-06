package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.entity.Paiement;
import com.fitcoach.fitcoach.dtos.PaiementDTO;

import java.util.Collection;

public interface PaiementManager {
    Collection<PaiementDTO> listPaiement();
    PaiementDTO addPaiment(PaiementDTO paiementDTO);
    PaiementDTO updatePaiement(Long id,PaiementDTO paiementDTO);
    boolean deletePaiement(long id);

}
