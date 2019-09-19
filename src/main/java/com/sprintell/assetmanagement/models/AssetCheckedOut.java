package com.sprintell.assetmanagement.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AssetCheckedOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetCheckedOutId;


    private LocalDateTime checkOutTime;
    private LocalDate dueDate;
    private Double fine;
    private LocalDate regTime;
    private LocalDate reserveTime;
    private LocalDateTime returnTime;
    private String status;
    private String description;

    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="assetId")
    private Asset asset;

    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="personnelNumber")
    private Personnel personnel;




    public AssetCheckedOut() {
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

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
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
