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
    private String assetDescription;
    private String barCode;
    private String situation;
    private String picture;

    private LocalDate regTime;

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name="asset_category",
            joinColumns=@JoinColumn(name="assetId"),
            inverseJoinColumns=@JoinColumn(name="categoryId")
    )
    private List<Category> categories;

    //General
    private Long serialNumber;
    private String assetStatus;
    private String assetCondition;
    private String notes;

    //Finance
    private String accountCode;
    private int PONumber;
    private double purchasePrice;
    private double currentValue;
    private double scrapValue;

    private LocalDate purchasedDate;
    private LocalDate inService;
    private LocalDate warrantyExpires;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="vendorId")
    private Vendor vendor;

    //child classes
    @OneToMany(mappedBy="asset",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH})
    private List<AssetHistory> assetHistories;

    @OneToMany(mappedBy="asset",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH})
    private List<AssetNote> assetNotes;

    @OneToMany(mappedBy="asset",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH})
    private List<Maintenance> maintenances;

    @OneToMany(mappedBy="asset",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH})
    private List<AssetCheckedOut> checkedOuts;


    //parent classes
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="locationId")
    private Location location;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="departmentId")
    private Department department;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="manufacturerId")
    private Manufacturer manufacturer;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="statusId")
    private Status status;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="modelId")
    private Model model;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="brandId")
    private Brand brand;

    public Asset() {
    }

    public Asset(String assetNumber, String assetDescription, String barCode, String situation, String picture,
                 Long serialNumber, String assetstatus, String assetcondition, String notes, String accountCode,
                 int PONumber, double purchasePrice, double currentValue, double scrapValue, LocalDate purchasedDate,
                 LocalDate inService, LocalDate warrantyExpires) {
        this.assetNumber = assetNumber;
        this.assetDescription = assetDescription;
        this.barCode = barCode;
        this.situation = situation;
        this.picture = picture;
        this.serialNumber = serialNumber;
        this.assetStatus = assetstatus;
        this.assetCondition = assetcondition;
        this.notes = notes;
        this.accountCode = accountCode;
        this.PONumber = PONumber;
        this.purchasePrice = purchasePrice;
        this.currentValue = currentValue;
        this.scrapValue = scrapValue;
        this.purchasedDate = purchasedDate;
        this.inService = inService;
        this.warrantyExpires = warrantyExpires;
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

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public LocalDate getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDate regTime) {
        this.regTime = regTime;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(String assetstatus) {
        this.assetStatus = assetstatus;
    }

    public String getAssetCondition() {
        return assetCondition;
    }

    public void setAssetCondition(String assetcondition) {
        this.assetCondition = assetcondition;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public int getPONumber() {
        return PONumber;
    }

    public void setPONumber(int PONumber) {
        this.PONumber = PONumber;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getScrapValue() {
        return scrapValue;
    }

    public void setScrapValue(double scrapValue) {
        this.scrapValue = scrapValue;
    }

    public LocalDate getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(LocalDate purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public LocalDate getInService() {
        return inService;
    }

    public void setInService(LocalDate inService) {
        this.inService = inService;
    }

    public LocalDate getWarrantyExpires() {
        return warrantyExpires;
    }

    public void setWarrantyExpires(LocalDate warrantyExpires) {
        this.warrantyExpires = warrantyExpires;
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

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public List<AssetCheckedOut> getCheckedOuts() {
        return checkedOuts;
    }

    public void setCheckedOuts(List<AssetCheckedOut> checkedOuts) {
        this.checkedOuts = checkedOuts;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
