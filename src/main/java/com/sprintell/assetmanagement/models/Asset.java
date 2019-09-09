package com.sprintell.assetmanagement.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetId;

    private String assetNumber;
    private String description;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="categoryId")
    private AssetCategory assetCategory;
    private String manufacturer;
    private String model;

    //General
    @Embedded
    AssetGeneral assetGeneral;

    //Finance
    @Embedded
    AssetFinance assetFinance;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="vendorId")
    private Vendor vendor;

    //child classes
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="assetId")
    private List<AssetHistory> assetHistories;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="assetId")
    private List<AssetNote> assetNotes;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="assetId")

    private List<AssetService> assetServices;

    public Asset() {
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AssetCategory getAssetCategory() {
        return assetCategory;
    }

    public void setAssetCategory(AssetCategory assetCategory) {
        this.assetCategory = assetCategory;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public AssetGeneral getAssetGeneral() {
        return assetGeneral;
    }

    public void setAssetGeneral(AssetGeneral assetGeneral) {
        this.assetGeneral = assetGeneral;
    }

    public AssetFinance getAssetFinance() {
        return assetFinance;
    }

    public void setAssetFinance(AssetFinance assetFinance) {
        this.assetFinance = assetFinance;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public List<AssetHistory> getAssetHistories() {
        return assetHistories;
    }

    public void setAssetHistories(List<AssetHistory> assetHistories) {
        this.assetHistories = assetHistories;
    }

    public List<AssetNote> getAssetNotes() {
        return assetNotes;
    }

    public void setAssetNotes(List<AssetNote> assetNotes) {
        this.assetNotes = assetNotes;
    }

    public List<AssetService> getAssetServices() {
        return assetServices;
    }

    public void setAssetServices(List<AssetService> assetServices) {
        this.assetServices = assetServices;
    }
}
