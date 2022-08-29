package com.api.pautacontrol.entities;

import com.api.pautacontrol.enums.JudgmentTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "process")
public class ProcessEntity extends AbstractEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String number;

    @Column
    private String classType;

    @OneToOne
    @JoinColumn(name = "reporter_id")
    private MinisterEntity reporter;

    @Enumerated
    private JudgmentTypeEnum judgmentType;

    @OneToMany
    @JoinColumn(name = "process_part_id")
    private List<ProcessPartEntity> processParts;

    @OneToMany
    @JoinColumn(name = "process_history_id")
    private List<ProcessHistoryEntity> processHistories;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public MinisterEntity getReporter() {
        return reporter;
    }

    public void setReporter(MinisterEntity reporter) {
        this.reporter = reporter;
    }

    public JudgmentTypeEnum getJudgmentType() {
        return judgmentType;
    }

    public void setJudgmentType(JudgmentTypeEnum judgmentType) {
        this.judgmentType = judgmentType;
    }

    public List<ProcessPartEntity> getProcessParts() {
        return processParts;
    }

    public void setProcessParts(List<ProcessPartEntity> processParts) {
        this.processParts = processParts;
    }

    public List<ProcessHistoryEntity> getProcessHistories() {
        return processHistories;
    }

    public void setProcessHistories(List<ProcessHistoryEntity> processHistories) {
        this.processHistories = processHistories;
    }


}
