package com.sprintell.assetmanagement.models;

import javax.persistence.Embeddable;

@Embeddable
public class AssetGeneral {

    private Long serialNumber;
    private String assetstatus;
    private String assetcondition;
    private String notes;

    public AssetGeneral() {
    }

    public AssetGeneral(Long serialNumber, String status, String condition, String notes) {
        this.serialNumber = serialNumber;
        this.assetstatus = status;
        this.assetcondition = condition;
        this.notes = notes;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAssetStatus() {
        return assetstatus;
    }

    public void setAssetStatus(String status) {
        this.assetstatus = status;
    }

    public String getAssetCondition() {
        return assetcondition;
    }

    public void setAssetCondition(String condition) {
        this.assetcondition = condition;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
