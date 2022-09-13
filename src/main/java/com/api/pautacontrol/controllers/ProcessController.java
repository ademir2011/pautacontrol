package com.api.pautacontrol.controllers;

import com.api.pautacontrol.dtos.MinisterDTO;
import com.api.pautacontrol.dtos.ProcessDTO;
import com.api.pautacontrol.dtos.ProcessHistoryDTO;
import com.api.pautacontrol.dtos.ProcessPartDTO;
import com.api.pautacontrol.entities.MinisterEntity;
import com.api.pautacontrol.entities.ProcessEntity;
import com.api.pautacontrol.entities.ProcessHistoryEntity;
import com.api.pautacontrol.entities.ProcessPartEntity;
import com.api.pautacontrol.services.MinisterService;
import com.api.pautacontrol.services.ProcessHistoryService;
import com.api.pautacontrol.services.ProcessPartService;
import com.api.pautacontrol.services.ProcessService;
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
@RequestMapping("/process")
public class ProcessController {

    private final MessageProperties messageProperties;

    private final ProcessService processService;

    private final ProcessPartService processPartService;
    private final ProcessHistoryService processHistoryService;
    private final MinisterService ministerService;


    public ProcessController(
        ProcessService processService,
        MessageProperties messageProperties,
        ProcessPartService processPartService,
        ProcessHistoryService processHistoryService,
        MinisterService ministerService
    ){
        this.processService = processService;
        this.messageProperties = messageProperties;
        this.processPartService = processPartService;
        this.processHistoryService = processHistoryService;
        this.ministerService = ministerService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ProcessDTO processDTO){

        if(processService.existByNumber(processDTO.getNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um processo com essa numeração!");
        }

        var process = new ProcessEntity();
        BeanUtils.copyProperties(processDTO, process);

        return ResponseEntity.status(HttpStatus.OK).body( processService.save(process) );
    }

    @GetMapping
    public ResponseEntity<Page<ProcessEntity>> list(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(processService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable(value = "id") UUID id){

        var process = processService.findById(id);

        if(!process.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Process Not Found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(process);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProcessDTO processDTO){

        processDTO.setId(id);

        var process = new ProcessEntity();
        BeanUtils.copyProperties(processDTO, process);

        return ResponseEntity.status(HttpStatus.OK).body( processService.save(process) );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id){

        var process = processService.findById(id);

        if(!process.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Process Not Found");
        }

        processService.delete(process.get());

        return ResponseEntity.status(HttpStatus.OK).body("Proccess Removed");
    }

    @PostMapping("/adicionar_relator/{id}")
    public ResponseEntity<?> addReporter(@PathVariable(value = "id") UUID id, @RequestBody @Valid MinisterDTO ministerEntityDTO){

        var ministerEntity = new MinisterEntity();
        BeanUtils.copyProperties(ministerEntityDTO, ministerEntity);

        return ResponseEntity.status(HttpStatus.OK).body(ministerService.addReporterToProcess(id, ministerEntity));
    }

    @PostMapping("/adicionar_parte/{id}")
    public ResponseEntity<?> addPart(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProcessPartDTO processPartDTO){

        var processPartEntity = new ProcessPartEntity();
        BeanUtils.copyProperties(processPartDTO, processPartEntity);

        return ResponseEntity.status(HttpStatus.OK).body(processPartService.addPartToProcess(id, processPartEntity));
    }

    @PostMapping("/adicionar_history/{id}")
    public ResponseEntity<?> addHistory(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProcessHistoryDTO processHistoryDTO){

        var processHistoryEntity = new ProcessHistoryEntity();
        BeanUtils.copyProperties(processHistoryDTO, processHistoryEntity);

        return ResponseEntity.status(HttpStatus.OK).body(processHistoryService.addHistoryToProcess(id, processHistoryEntity));
    }

}
