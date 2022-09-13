package com.api.pautacontrol.services;

import com.api.pautacontrol.entities.MinisterEntity;
import com.api.pautacontrol.entities.ProcessEntity;
import com.api.pautacontrol.entities.ProcessHistoryEntity;
import com.api.pautacontrol.entities.ProcessPartEntity;
import com.api.pautacontrol.repositories.MinisterRepository;
import com.api.pautacontrol.repositories.ProcessHistoryRepository;
import com.api.pautacontrol.repositories.ProcessPartRepository;
import com.api.pautacontrol.repositories.ProcessRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProcessService {

    private final ProcessRepository processRepository;

    private final MinisterRepository ministerRepository;

    private final ProcessPartRepository processPartRepository;

    private final ProcessHistoryRepository processHistoryRepository;

    public ProcessService(ProcessRepository processRepository,
                          MinisterRepository ministerRepository,
                          ProcessPartRepository processPartRepository,
                          ProcessHistoryRepository processHistoryRepository){

        this.processRepository = processRepository;
        this.ministerRepository = ministerRepository;
        this.processPartRepository = processPartRepository;
        this.processHistoryRepository = processHistoryRepository;
    }

    public Page<ProcessEntity> findAll(Pageable pageable) {
        return processRepository.findAll(pageable);
    }

    public ProcessEntity save(ProcessEntity processEntity) throws Exception {

        if( processEntity.getNumber() == null || processEntity.getNumber().isEmpty()){
            throw new RuntimeException("O processo deve conter um número!");
        }

        if( processEntity.getId() != null && !processRepository.findById(processEntity.getId()).isPresent()){
            throw new RuntimeException("Não existe processo salvo com este ID!");
        }

        if( processEntity.getId() == null && processEntity.getNumber() != null && processRepository.existsByNumber(processEntity.getNumber())){
            throw new RuntimeException("Já existe um processo com essa numeração!");
        }

        if(processEntity.getId() != null){
            var processEntityTemp = processRepository.findById(processEntity.getId()).get();
            processEntityTemp.setNumber(processEntity.getNumber());
            processEntityTemp.setClassType(processEntity.getClassType());
            processEntityTemp.setJudgmentType(processEntity.getJudgmentType());
            processEntityTemp.setUpdated_at( LocalDateTime.now() );

            return processRepository.save(processEntityTemp);
        }

        return processRepository.save(processEntity);
    }

    public Optional<ProcessEntity> findById(UUID id) {

        if(id == null) {
            throw new RuntimeException("ID do processo inválido!");
        }

        return processRepository.findById(id);
    }

    public void delete(UUID id) throws Exception {

        if( id != null && !processRepository.findById(id).isPresent()){
            throw new RuntimeException("Não existe processo salvo com este ID!");
        }

        processRepository.delete(processRepository.findById(id).get());
    }

}
