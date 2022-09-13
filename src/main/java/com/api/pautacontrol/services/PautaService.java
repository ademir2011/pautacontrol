package com.api.pautacontrol.services;

import com.api.pautacontrol.entities.PautaEntity;
import com.api.pautacontrol.repositories.PautaRepository;
import com.api.pautacontrol.repositories.ProcessRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

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

        if(pautaEntity.getSectionDate() == null || pautaEntity.getSectionDate().toString().isEmpty()){
            throw new RuntimeException("A data de sessão está nula ou vazia!");
        }

        if(pautaEntity.getId() == null) {

            LocalDate pautaDate = pautaEntity.getSectionDate().toLocalDate();

            pautaRepository.findAll().stream().forEach((pauta) -> {
                if (pauta.getSectionDate().toLocalDate().isEqual( LocalDate.of(pautaDate.getYear(), pautaDate.getMonth(), pautaDate.getDayOfMonth()) ) ){
                    throw new RuntimeException("Já existe uma pauta cadastrada para esta data!");
                }
            });

            PautaEntity pautaEntityTemp = new PautaEntity();
            pautaEntityTemp.setSectionDate(pautaEntity.getSectionDate());
            return pautaRepository.save(pautaEntityTemp);
        }

        if(pautaEntity.getId() != null){

            var pautaEntityTemp = pautaRepository.findById(pautaEntity.getId()).get();

            LocalDate today = LocalDate.now();
            LocalDate date = pautaEntityTemp.getSectionDate().toLocalDate();

            boolean isBefore = today.isBefore( LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth()) );

            if(isBefore) {
                throw new RuntimeException(
                        "Aguarde até a data: "
                        + pautaEntityTemp.getSectionDate().getDayOfMonth()
                        + "/"
                        + pautaEntityTemp.getSectionDate().getMonth().getValue()
                        + "/"
                        + pautaEntityTemp.getSectionDate().getYear()
                        + " para fazer modificações!"
                );
            } else {
                pautaEntity.setSectionDate(pautaEntityTemp.getSectionDate());
                return pautaRepository.save(pautaEntity);
            }

        }

        return pautaRepository.save(pautaEntity);
    }

    public PautaEntity addProcessToPauta(UUID idProcess, UUID idPauta) throws Exception {

        var pautaEntity = pautaRepository.findById(idPauta);

        if(!pautaEntity.isPresent()) {
            throw new RuntimeException("Pauta não encontrada!");
        }

        var processEntity = processRepository.findById(idProcess);

        if(!processEntity.isPresent()) {
            throw new RuntimeException("Processo não encontrada!");
        }

        LocalDate today = LocalDate.now();
        LocalDate date = pautaEntity.get().getSectionDate().toLocalDate();

        boolean isToday = today.isEqual( LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth()) );

        if(isToday) {
            pautaEntity.get().getProcess().add(processEntity.get());
        } else {
            throw new RuntimeException("Aguarde até a data: "
                    + pautaEntity.get().getSectionDate().getDayOfMonth()
                    + "/"
                    + pautaEntity.get().getSectionDate().getMonth().getValue()
                    + "/"
                    + pautaEntity.get().getSectionDate().getYear()
                    + " para fazer modificações!");
        }

        return pautaRepository.save(pautaEntity.get());
    }

    public Optional<PautaEntity> findById(UUID id) {
        return pautaRepository.findById(id);
    }

    public PautaEntity findBySectionDateContainingIgnoreCase(LocalDateTime localDateTime) throws Exception {

        var listTemp = new ArrayList<PautaEntity>();
        pautaRepository.findAll().forEach( (pauta) -> {
            if( pauta.getSectionDate().getYear() == localDateTime.getYear() &&
                pauta.getSectionDate().getMonth() == localDateTime.getMonth() &&
                pauta.getSectionDate().getDayOfMonth() == localDateTime.getDayOfMonth()){
                listTemp.add(pauta);
            }
        });

        if(listTemp.size() == 0){
            throw new RuntimeException("Não foi encontrado pauta para a data informada!");
        }

        return listTemp.get(0);
    }

    public List<PautaEntity> findByReporterContainingIgnoreCase(String name) throws Exception {

        if(name == null || name.isEmpty()){
            throw new RuntimeException("Nome do ministro(a) não informado!");
        }

        var listTemp = new ArrayList<PautaEntity>();
        pautaRepository.findAll().forEach( (pauta) -> {

            pauta.getProcess().stream().forEach((process) -> {
                if(process.getReporter().getName().contains(name)){
                    listTemp.add(pauta);
                }
            });
        });

        if(listTemp.size() == 0){
            throw new RuntimeException("Não foi encontrado pauta para o campo informado!");
        }

        return listTemp;
    }

    public List<PautaEntity> findByProcessPartContainingIgnoreCase(String lawyer, String aggravating) throws Exception {

        if(lawyer.isEmpty() && aggravating.isEmpty()) {
            throw new RuntimeException("Informe ao menos o nome do advogado ou agravante para realizar a busca!");
        }

        var listTemp = new ArrayList<PautaEntity>();
        pautaRepository.findAll().forEach( (pauta) -> {

            pauta.getProcess().stream().forEach((process) -> {

                process.getProcessParts().forEach( (parts) -> {
                    if( !aggravating.isEmpty() && parts.getAggravating().contains(aggravating) ) {
                        listTemp.add(pauta);
                    } else if ( !lawyer.isEmpty() && parts.getLawyer().contains(lawyer) ) {
                        listTemp.add(pauta);
                    }
                });

            });
        });

        if(listTemp.size() == 0){
            throw new RuntimeException("Não foi encontrado pautas para as partes informadas!");
        }

        return listTemp;
    }

    public void delete(PautaEntity pautaEntity) {
        pautaRepository.delete(pautaEntity);
    }

}
