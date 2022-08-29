package com.api.pautacontrol.controllers;

import com.api.pautacontrol.dtos.ProcessDTO;
import com.api.pautacontrol.entities.MinisterEntity;
import com.api.pautacontrol.entities.ProcessEntity;
import com.api.pautacontrol.services.ProcessService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping("/process")
public class ProcessController {

    private final ProcessService processService;

    public ProcessController(ProcessService processService){
        this.processService = processService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ProcessDTO processDTO){

        var process = new ProcessEntity();
        BeanUtils.copyProperties(processDTO, process);

        return ResponseEntity.status(HttpStatus.OK).body( processService.save(process) );
    }

    @GetMapping
    public ResponseEntity<Page<ProcessEntity>> listProcess(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(processService.findAll(pageable));
    }

}
