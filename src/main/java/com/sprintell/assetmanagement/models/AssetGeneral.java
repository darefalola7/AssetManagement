package com.sprintell.assetmanagement.models;

import javax.persistence.Embeddable;

@Embeddable
public class AssetGeneral {

    private Long serialNumber;
    private String status;
    private String condition;
    private String notes;

    public AssetGeneral() {
    }

    public AssetGeneral(Long serialNumber, String status, String condition, String notes) {
        this.serialNumber = serialNumber;
        this.status = status;
        this.condition = condition;
        this.notes = notes;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
