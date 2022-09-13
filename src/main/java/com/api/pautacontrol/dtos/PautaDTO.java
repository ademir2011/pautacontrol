package com.api.pautacontrol.dtos;

import com.api.pautacontrol.entities.ProcessEntity;
import com.api.pautacontrol.enums.JudgmentTypeEnum;
import com.api.pautacontrol.enums.SystemEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class PautaDTO {

    private UUID id;

    private String adjudicatingBody;

    private String pauta;

    private Boolean addition;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime sectionDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime disclosureDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime publishDate;

    private SystemEnum systemEnum;

    private JudgmentTypeEnum judgmentTypeEnum;

    private List<ProcessDTO> processDTO;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAdjudicatingBody() {
        return adjudicatingBody;
    }

    public void setAdjudicatingBody(String adjudicatingBody) {
        this.adjudicatingBody = adjudicatingBody;
    }

    public String getPauta() {
        return pauta;
    }

    public void setPauta(String pauta) {
        this.pauta = pauta;
    }

    public Boolean getAddition() {
        return addition;
    }

    public void setAddition(Boolean addition) {
        this.addition = addition;
    }

    public LocalDateTime getSectionDate() {
        return sectionDate;
    }

    public void setSectionDate(LocalDateTime sectionDate) {
        this.sectionDate = sectionDate;
    }

    public LocalDateTime getDisclosureDate() {
        return disclosureDate;
    }

    public void setDisclosureDate(LocalDateTime disclosureDate) {
        this.disclosureDate = disclosureDate;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public SystemEnum getSystemEnum() {
        return systemEnum;
    }

    public void setSystemEnum(SystemEnum systemEnum) {
        this.systemEnum = systemEnum;
    }

    public JudgmentTypeEnum getJudgmentTypeEnum() {
        return judgmentTypeEnum;
    }

    public void setJudgmentTypeEnum(JudgmentTypeEnum judgmentTypeEnum) {
        this.judgmentTypeEnum = judgmentTypeEnum;
    }

    public List<ProcessDTO> getProcessDTO() {
        return processDTO;
    }

    public void setProcessDTO(List<ProcessDTO> processDTO) {
        this.processDTO = processDTO;
    }
}
