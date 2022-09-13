package com.api.pautacontrol.services;

import com.api.pautacontrol.entities.ProcessEntity;
import com.api.pautacontrol.entities.ProcessHistoryEntity;
import com.api.pautacontrol.repositories.MinisterRepository;
import com.api.pautacontrol.repositories.ProcessHistoryRepository;
import com.api.pautacontrol.repositories.ProcessPartRepository;
import com.api.pautacontrol.repositories.ProcessRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProcessHistoryService {

    private final ProcessRepository processRepository;

    private final MinisterRepository ministerRepository;

    private final ProcessPartRepository processPartRepository;

    private final ProcessHistoryRepository processHistoryRepository;

    public ProcessHistoryService(ProcessRepository processRepository,
                           MinisterRepository ministerRepository,
                           ProcessPartRepository processPartRepository,
                           ProcessHistoryRepository processHistoryRepository){

        this.processRepository = processRepository;
        this.ministerRepository = ministerRepository;
        this.processPartRepository = processPartRepository;
        this.processHistoryRepository = processHistoryRepository;
    }

    public ProcessEntity addHistoryToProcess(UUID id, ProcessHistoryEntity processHistoryEntity) throws Exception {

        if(processHistoryEntity.getDescription() == null || processHistoryEntity.getDescription().isEmpty()) {
            throw new RuntimeException("Valor da movimentação nula ou vazia!");
        }

        if(id != null && !processRepository.findById(id).isPresent()){
            throw new RuntimeException("Não existe processo salvo com este ID!");
        }

        var processEntity = processRepository.findById(id).get();

        processEntity.getProcessHistories().add( processHistoryRepository.save(processHistoryEntity) );

        return processRepository.save(processEntity);

    }

}
