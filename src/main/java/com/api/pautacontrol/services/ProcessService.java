package com.api.pautacontrol.services;

import com.api.pautacontrol.entities.ProcessEntity;
import com.api.pautacontrol.repositories.ProcessRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProcessService {

    private final ProcessRepository processRepository;

    public ProcessService(ProcessRepository processRepository){
        this.processRepository = processRepository;
    }

    public Page<ProcessEntity> findAll(Pageable pageable) {
        return processRepository.findAll(pageable);
    }

    public ProcessEntity save(ProcessEntity processEntity) {
        return processRepository.save(processEntity);
    }

    public Optional<ProcessEntity> findById(UUID id) {
        return processRepository.findById(id);
    }

    public void delete(ProcessEntity processEntity) {
        processRepository.delete(processEntity);
    }

    public boolean existByNumber(String number) {
        return processRepository.existsByNumber(number);
    }
}
