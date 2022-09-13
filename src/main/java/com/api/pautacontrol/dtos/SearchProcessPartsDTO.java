package com.api.pautacontrol.dtos;

public class SearchProcessPartsDTO {

    private String lawyer;
    private String aggravating;

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