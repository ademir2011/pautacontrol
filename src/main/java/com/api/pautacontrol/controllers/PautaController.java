package com.api.pautacontrol.controllers;

import com.api.pautacontrol.dtos.PautaDTO;
import com.api.pautacontrol.entities.PautaEntity;
import com.api.pautacontrol.services.PautaService;
import com.api.pautacontrol.utils.MessageProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

        return ResponseEntity.status(HttpStatus.OK).body( pautaService.save(pautaEntity) );
    }

    @GetMapping
    public ResponseEntity<Page<PautaEntity>> list(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable(value = "id") UUID id){

        var pauta = pautaService.findById(id);

        if(!pauta.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Process Not Found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pauta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid PautaDTO pautaDTO){

        var pauta = new PautaEntity();
        BeanUtils.copyProperties(pautaDTO, pauta);

        return ResponseEntity.status(HttpStatus.OK).body( pautaService.save(pauta) );

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
}
