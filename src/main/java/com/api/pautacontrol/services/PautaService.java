package com.api.pautacontrol.services;

import com.api.pautacontrol.entities.PautaEntity;
import com.api.pautacontrol.entities.ProcessEntity;
import com.api.pautacontrol.repositories.PautaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Page<PautaEntity> findAll(Pageable pageable) {
        return pautaRepository.findAll(pageable);
    }

    public PautaEntity save(PautaEntity pautaEntity) {
        return pautaRepository.save(pautaEntity);
    }

    public Optional<PautaEntity> findById(UUID id) {
        return pautaRepository.findById(id);
    }

    public void delete(PautaEntity pautaEntity) {
        pautaRepository.delete(pautaEntity);
    }
}
