package com.api.pautacontrol.services;

import com.api.pautacontrol.repositories.PautaRepository;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }
}
