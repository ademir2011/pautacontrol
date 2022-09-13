package com.api.pautacontrol.services;

import com.api.pautacontrol.entities.MinisterEntity;
import com.api.pautacontrol.entities.ProcessEntity;
import com.api.pautacontrol.repositories.MinisterRepository;
import com.api.pautacontrol.repositories.ProcessHistoryRepository;
import com.api.pautacontrol.repositories.ProcessPartRepository;
import com.api.pautacontrol.repositories.ProcessRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MinisterService {

    private final ProcessRepository processRepository;

    private final MinisterRepository ministerRepository;

    private final ProcessPartRepository processPartRepository;

    private final ProcessHistoryRepository processHistoryRepository;

    public MinisterService(ProcessRepository processRepository,
                          MinisterRepository ministerRepository,
                          ProcessPartRepository processPartRepository,
                          ProcessHistoryRepository processHistoryRepository){

        this.processRepository = processRepository;
        this.ministerRepository = ministerRepository;
        this.processPartRepository = processPartRepository;
        this.processHistoryRepository = processHistoryRepository;
    }

    public ProcessEntity addReporterToProcess(UUID id, MinisterEntity ministerEntity){

        var processEntity = processRepository.findById(id).get();

        processEntity.setReporter(ministerRepository.save(ministerEntity));

        return processRepository.save(processEntity);

    }

}
