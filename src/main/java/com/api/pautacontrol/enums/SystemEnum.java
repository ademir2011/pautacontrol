package com.api.pautacontrol.enums;

public enum SystemEnum {

    TST("TST"),
    PJE("PJE");

    private String description;

    SystemEnum(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
