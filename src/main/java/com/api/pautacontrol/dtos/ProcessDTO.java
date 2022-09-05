package com.api.pautacontrol.dtos;

import com.api.pautacontrol.enums.JudgmentTypeEnum;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class ProcessDTO {

    private String number;

    private String classType;

    private MinisterEntityDTO reporterDTO;

    private JudgmentTypeEnum judgmentType;

    private List<ProcessPartDTO> processParts;

    private List<ProcessHistoryDTO> processHistories;

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

    public MinisterEntityDTO getReporterDTO() {
        return reporterDTO;
    }

    public void setReporterDTO(MinisterEntityDTO reporterDTO) {
        this.reporterDTO = reporterDTO;
    }

    public JudgmentTypeEnum getJudgmentType() {
        return judgmentType;
    }

    public void setJudgmentType(JudgmentTypeEnum judgmentType) {
        this.judgmentType = judgmentType;
    }

    public List<ProcessPartDTO> getProcessParts() {
        return processParts;
    }

    public void setProcessParts(List<ProcessPartDTO> processParts) {
        this.processParts = processParts;
    }

    public List<ProcessHistoryDTO> getProcessHistories() {
        return processHistories;
    }

    public void setProcessHistories(List<ProcessHistoryDTO> processHistories) {
        this.processHistories = processHistories;
    }
}
