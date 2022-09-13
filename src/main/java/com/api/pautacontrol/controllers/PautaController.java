package com.api.pautacontrol.controllers;

import com.api.pautacontrol.dtos.PautaDTO;
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

import javax.validation.Valid;
import java.time.LocalDateTime;
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pauta Not Found");
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("pauta Not Found");
        }

        pautaService.delete(pauta.get());

        return ResponseEntity.status(HttpStatus.OK).body("pauta Removed");
    }

    @PostMapping("/incluir_processo/{idProcess}/{idPauta}")
    public ResponseEntity<?> addProcessToPauta(@PathVariable(value = "idProcess") UUID idProcess,
                                               @PathVariable(value = "idPauta") UUID idPauta){


        return ResponseEntity.status(HttpStatus.OK).body(pautaService.addProcessToPauta(idProcess, idPauta));
    }

    @GetMapping("/search_section_date")
    public ResponseEntity<?> searchDate(@RequestBody @Valid SearchSectionDateDTO searchSectionDateDTO){
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.findBySectionDateContainingIgnoreCase(searchSectionDateDTO.getLocalDateTime()));
    }

    @GetMapping("/search_process_number")
    public ResponseEntity<?> searchDate(@RequestBody @Valid SearchProcessNumberDTO searchProcessNumberDTO){
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.findByProcessNumberContainingIgnoreCase(searchProcessNumberDTO.getNumber()));
    }

    @GetMapping("/search_reporter")
    public ResponseEntity<?> searchDate(@RequestBody @Valid SearchReporterDTO searchReporterDTO){
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.findByReporterContainingIgnoreCase(searchReporterDTO.getName()));
    }

}

class SearchSectionDateDTO {
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime localDateTime;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}

class SearchReporterDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class SearchProcessNumberDTO {
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}