package com.api.pautacontrol.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "process_history")
public class ProcessHistoryEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
