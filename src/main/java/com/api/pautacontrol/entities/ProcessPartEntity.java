package com.api.pautacontrol.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "process_part")
public class ProcessPartEntity extends AbstractEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private ProcessEntity processEntity;

    @Column
    private String lawyer;

    @Column
    private String aggravating;

    public ProcessEntity getProcessEntity() {
        return processEntity;
    }

    public void setProcessEntity(ProcessEntity processEntity) {
        this.processEntity = processEntity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLawyer() {
        return lawyer;
    }

    public void setLawyer(String lawyer) {
        this.lawyer = lawyer;
    }

    public String getAggravating() {
        return aggravating;
    }

    public void setAggravating(String aggravating) {
        this.aggravating = aggravating;
    }
}
