package com.api.pautacontrol.dtos;

import java.util.UUID;

public class ProcessToPautaDTO {

    private UUID idProcess;
    private UUID idPauta;

    public UUID getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(UUID idProcess) {
        this.idProcess = idProcess;
    }

    public UUID getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(UUID idPauta) {
        this.idPauta = idPauta;
    }
}
