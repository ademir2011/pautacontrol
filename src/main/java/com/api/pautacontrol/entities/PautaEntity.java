package com.api.pautacontrol.entities;

import com.api.pautacontrol.enums.JudgmentTypeEnum;
import com.api.pautacontrol.enums.SystemEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pauta")
public class PautaEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String adjudicatingBody;

    @Column
    private String pauta;

    @Column
    private Boolean addition;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime sectionDate;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime disclosureDate;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime publishDate;

    @Enumerated
    private SystemEnum systemEnum;

    @Enumerated
    private JudgmentTypeEnum judgmentTypeEnum;

    @OneToMany
    @JoinColumn(name = "pauta_id")
    private List<ProcessEntity> process;

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

    public List<ProcessEntity> getProcess() {
        return process;
    }

    public void setProcess(List<ProcessEntity> process) {
        this.process = process;
    }
}
