package com.api.pautacontrol.services;

import com.api.pautacontrol.entities.PautaEntity;
import com.api.pautacontrol.repositories.PautaRepository;
import com.api.pautacontrol.repositories.ProcessRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    private final ProcessRepository processRepository;

    public PautaService(PautaRepository pautaRepository, ProcessRepository processRepository) {
        this.pautaRepository = pautaRepository;
        this.processRepository = processRepository;
    }

    public Page<PautaEntity> findAll(Pageable pageable) {
        return pautaRepository.findAll(pageable);
    }

    public PautaEntity save(PautaEntity pautaEntity) throws Exception {

        if(pautaEntity.getId() == null && pautaRepository.existsBySectionDate(pautaEntity.getSectionDate())) {
            throw new RuntimeException("Já existe uma pauta cadastrada para esta data!");
        }

        if(pautaEntity.getId() == null) {
            PautaEntity pautaEntityTemp = new PautaEntity();
            pautaEntityTemp.setSectionDate(pautaEntity.getSectionDate());
            return pautaRepository.save(pautaEntityTemp);
        }

        if(pautaEntity.getId() != null){
            var pautaEntityTemp = pautaRepository.findById(pautaEntity.getId()).get();

            LocalDate today = LocalDate.now();
            LocalDate date = pautaEntityTemp.getSectionDate().toLocalDate();

            boolean isBefore = today.isAfter( LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth()) );

            if(isBefore) {
                pautaEntityTemp.setSectionDate(pautaEntity.getSectionDate());
                return pautaRepository.save(pautaEntityTemp);
            }

            boolean isEqual = today.isEqual( LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth()) );

            if(isEqual) {
                return pautaRepository.save(pautaEntity);
            } else {
                throw new RuntimeException("Data para cadastro de pauta é dia: "+pautaEntity.getSectionDate());
            }
        }

        return pautaRepository.save(pautaEntity);
    }

    public PautaEntity addProcessToPauta(UUID idProcess, UUID idPauta){
        var pautaEntity = pautaRepository.findById(idPauta).get();
        var processEntity = processRepository.findById(idProcess).get();

        pautaEntity.getProcess().add(processEntity);

        return pautaRepository.save(pautaEntity);
    }

    public Optional<PautaEntity> findById(UUID id) {
        return pautaRepository.findById(id);
    }

    public PautaEntity findBySectionDateContainingIgnoreCase(LocalDateTime localDateTime) {

        var listTemp = new ArrayList<PautaEntity>();
        pautaRepository.findAll().forEach( (pauta) -> {
            if(pauta.getSectionDate().equals(localDateTime)){
                listTemp.add(pauta);
            }
        });

        if(listTemp.size() == 0){
            throw new RuntimeException("Não foi encontrado nada");
        }

        return listTemp.get(0);
    }

    public List<PautaEntity> findByReporterContainingIgnoreCase(String name) {

        var listTemp = new ArrayList<PautaEntity>();
        pautaRepository.findAll().forEach( (pauta) -> {

            pauta.getProcess().stream().forEach((process) -> {
                if(process.getReporter().getName().contains(name)){
                    listTemp.add(pauta);
                }
            });
        });

        if(listTemp.size() == 0){
            throw new RuntimeException("Não foi encontrado nada");
        }

        return listTemp;
    }

    public List<PautaEntity> findByProcessNumberContainingIgnoreCase(String number) {

        var listTemp = new ArrayList<PautaEntity>();
        pautaRepository.findAll().forEach( (pauta) -> {

            pauta.getProcess().stream().forEach((process) -> {
                if(process.getNumber().contains(number)){
                    listTemp.add(pauta);
                }
            });
        });

        if(listTemp.size() == 0){
            throw new RuntimeException("Não foi encontrado nada");
        }

        return listTemp;
    }



    public void delete(PautaEntity pautaEntity) {
        pautaRepository.delete(pautaEntity);
    }


}
