package com.api.pautacontrol.controllers;

import com.api.pautacontrol.services.PautaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping("/pauta")
public class PautaController {

    final private PautaService pautaService;

    public PautaController(PautaService pautaService){
        this.pautaService = pautaService;
    }

}
