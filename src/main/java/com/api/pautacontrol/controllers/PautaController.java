package com.api.pautacontrol.controllers;

import com.api.pautacontrol.dtos.*;
import com.api.pautacontrol.entities.PautaEntity;
import com.api.pautacontrol.services.PautaService;
import com.api.pautacontrol.utils.MessageProperties;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping("/pauta")
public class PautaController {

    final private PautaService pautaService;

    private final MessageProperties messageProperties;

    public PautaController(PautaService pautaService, MessageProperties messageProperties){
        this.pautaService = pautaService;
        this.messageProperties = messageProperties;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid PautaDTO pautaDTO){

        var pautaEntity = new PautaEntity();
        BeanUtils.copyProperties(pautaDTO, pautaEntity);

        try {
            pautaEntity = pautaService.save(pautaEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body( pautaEntity );
    }

    @GetMapping
    public ResponseEntity<Page<PautaEntity>> list(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable(value = "id") UUID id){

        var pauta = pautaService.findById(id);

        if(!pauta.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pauta não encontrada!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pauta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid PautaDTO pautaDTO){

        pautaDTO.setId(id);

        var pautaEntity = new PautaEntity();
        BeanUtils.copyProperties(pautaDTO, pautaEntity);

        try {
            pautaEntity = pautaService.save(pautaEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body( pautaEntity );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id){

        var pauta = pautaService.findById(id);

        if(!pauta.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pauta não encontrada!");
        }

        pautaService.delete(pauta.get());

        return ResponseEntity.status(HttpStatus.OK).body("Pauta Removida");
    }

    @PostMapping("/incluir_processo")
    public ResponseEntity<?> addProcessToPauta(@RequestBody @Valid ProcessToPautaDTO processToPautaDTO){

        PautaEntity pautaEntity = null;

        try {
            pautaEntity = pautaService.addProcessToPauta(processToPautaDTO.getIdProcess(), processToPautaDTO.getIdPauta());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(pautaEntity);
    }



    @GetMapping("/search_section_date")
    public ResponseEntity<?> searchDate(@RequestBody @Valid SearchSectionDateDTO searchSectionDateDTO){

        PautaEntity pautaEntity = null;

        try {
            pautaEntity = pautaService.findBySectionDateContainingIgnoreCase(searchSectionDateDTO.getLocalDateTime());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(pautaEntity);
    }

    @GetMapping("/search_process_parts")
    public ResponseEntity<?> searchProcessParts(@RequestBody @Valid SearchProcessPartsDTO searchProcessPartsDTO){

        List<PautaEntity> pautaEntities = null;

        try {
            pautaEntities = pautaService.findByProcessPartContainingIgnoreCase(searchProcessPartsDTO.getLawyer(), searchProcessPartsDTO.getAggravating());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(pautaEntities);
    }

    @GetMapping("/search_reporter")
    public ResponseEntity<?> searchReporter(@RequestBody @Valid SearchReporterDTO searchReporterDTO){

        List<PautaEntity> pautaEntities = null;

        try {
            pautaEntities = pautaService.findByReporterContainingIgnoreCase(searchReporterDTO.getName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(pautaEntities);
    }

}





