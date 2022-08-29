package com.api.pautacontrol.enums;

public enum JudgmentTypeEnum {

    HIBRIDA("HÍBRIDA"),
    VIRTUAL("VIRTUAL"),
    PRESENCIAL("PRESENCIAL");

    private String description;

    JudgmentTypeEnum(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
