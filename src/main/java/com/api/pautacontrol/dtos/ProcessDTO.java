package com.api.pautacontrol.dtos;

import com.api.pautacontrol.enums.JudgmentTypeEnum;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

public class ProcessDTO {

    private UUID id;
    private String number;

    private String classType;

    private MinisterDTO ministerDTO;

    private JudgmentTypeEnum judgmentType;

    private List<ProcessPartDTO> processPartsDTO;

    private List<ProcessHistoryDTO> processHistoriesDTO;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public MinisterDTO getMinisterDTO() {
        return ministerDTO;
    }

    public void setMinisterDTO(MinisterDTO ministerDTO) {
        this.ministerDTO = ministerDTO;
    }

    public List<ProcessPartDTO> getProcessPartsDTO() {
        return processPartsDTO;
    }

    public void setProcessPartsDTO(List<ProcessPartDTO> processPartsDTO) {
        this.processPartsDTO = processPartsDTO;
    }

    public List<ProcessHistoryDTO> getProcessHistoriesDTO() {
        return processHistoriesDTO;
    }

    public void setProcessHistoriesDTO(List<ProcessHistoryDTO> processHistoriesDTO) {
        this.processHistoriesDTO = processHistoriesDTO;
    }

    public JudgmentTypeEnum getJudgmentType() {
        return judgmentType;
    }

    public void setJudgmentType(JudgmentTypeEnum judgmentType) {
        this.judgmentType = judgmentType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


}
