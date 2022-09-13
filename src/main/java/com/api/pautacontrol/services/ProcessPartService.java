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


    public ProcessEntity addPartToProcess(UUID id, ProcessPartEntity processPartEntity){

        var processEntity = processRepository.findById(id).get();

        processEntity.getProcessParts().add( processPartRepository.save(processPartEntity) );

        return processRepository.save(processEntity);

    }

}
