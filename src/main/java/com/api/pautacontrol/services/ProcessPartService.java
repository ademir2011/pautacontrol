package com.api.pautacontrol.services;

import com.api.pautacontrol.entities.ProcessEntity;
import com.api.pautacontrol.entities.ProcessPartEntity;
import com.api.pautacontrol.repositories.MinisterRepository;
import com.api.pautacontrol.repositories.ProcessHistoryRepository;
import com.api.pautacontrol.repositories.ProcessPartRepository;
import com.api.pautacontrol.repositories.ProcessRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProcessPartService {

    private final ProcessRepository processRepository;

    private final MinisterRepository ministerRepository;

    private final ProcessPartRepository processPartRepository;

    private final ProcessHistoryRepository processHistoryRepository;

    public ProcessPartService(ProcessRepository processRepository,
                                 MinisterRepository ministerRepository,
                                 ProcessPartRepository processPartRepository,
                                 ProcessHistoryRepository processHistoryRepository){

        this.processRepository = processRepository;
        this.ministerRepository = ministerRepository;
        this.processPartRepository = processPartRepository;
        this.processHistoryRepository = processHistoryRepository;
    }


    public ProcessEntity addPartToProcess(UUID id, ProcessPartEntity processPartEntity) throws Exception {

        if(processPartEntity.getLawyer() == null || processPartEntity.getLawyer().isEmpty()) {
            throw new RuntimeException("Nome do advogado nulo ou vazio!");
        }

        if(processPartEntity.getAggravating() == null || processPartEntity.getAggravating().isEmpty()) {
            throw new RuntimeException("Nome do agravante nulo ou vazio!");
        }

        if(id != null && !processRepository.findById(id).isPresent()){
            throw new RuntimeException("Não existe processo salvo com este ID!");
        }

        var processEntity = processRepository.findById(id).get();

        processEntity.getProcessParts().add( processPartRepository.save(processPartEntity) );

        return processRepository.save(processEntity);

    }

}
