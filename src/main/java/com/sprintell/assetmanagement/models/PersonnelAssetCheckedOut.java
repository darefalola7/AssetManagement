package com.sprintell.assetmanagement.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class PersonnelAssetCheckedOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetCheckedOutId;

    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="assetId")
    private Asset asset;

    private String description;
    private LocalDate dueDate;

    public PersonnelAssetCheckedOut() {
    }

    public Long getAssetCheckedOutId() {
        return assetCheckedOutId;
    }

    public void setAssetCheckedOutId(Long assetCheckedOutId) {
        this.assetCheckedOutId = assetCheckedOutId;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
