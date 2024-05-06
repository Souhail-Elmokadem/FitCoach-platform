package com.fitcoach.fitcoach.services;

import com.fitcoach.fitcoach.dao.Repository.PaiementRepository;
import com.fitcoach.fitcoach.dao.entity.Paiement;
import com.fitcoach.fitcoach.dtos.PaiementDTO;
import com.fitcoach.fitcoach.mappers.PaiementMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaiementServiceImpl implements PaiementManager{
    private PaiementRepository paiementRepository;
    private PaiementMapper paiementMapper;
    @Override
    public Collection<PaiementDTO> listPaiement() {
        return paiementRepository.findAll().stream().map(p->paiementMapper.map(p)).collect(Collectors.toList());
    }

    @Override
    public PaiementDTO addPaiment(PaiementDTO paiementDTO) {
        return paiementMapper.map(paiementRepository.save(paiementMapper.map(paiementDTO)));
    }

    @Override
    public PaiementDTO updatePaiement(Long id, PaiementDTO paiementDTO) {
        Paiement paiement = paiementRepository.findById(id).orElseThrow(()->new RuntimeException("Paiement Not Found"));
        paiementDTO.setId(id);
        return paiementMapper.map(paiementRepository.save(paiementMapper.map(paiementDTO)));
    }

    @Override
    public boolean deletePaiement(long id) {
        Paiement paiement = paiementRepository.findById(id).orElseThrow(()->new RuntimeException("Paiement Not Found"));
        paiementRepository.delete(paiement);
        return true;
    }
}
