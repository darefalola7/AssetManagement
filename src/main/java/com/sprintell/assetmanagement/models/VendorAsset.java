package com.sprintell.assetmanagement.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class VendorAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="assetId")
    private Asset asset;

    private String vendorDescription;
    private LocalDate dateCreated;
    private LocalDate dateUpdated;

    public VendorAsset() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getVendorDescription() {
        return vendorDescription;
    }

    public void setVendorDescription(String description) {
        this.vendorDescription = description;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
