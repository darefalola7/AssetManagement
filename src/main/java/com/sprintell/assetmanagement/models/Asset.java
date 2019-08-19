package com.sprintell.assetmanagement.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Asset {
    private String assetId;
    private String description;
    @OneToOne
    private AssetCategory assetCategory;
    private String manufacturer;
    private String model;
    //General
    private Long serialNumber;
    private String status;
    private String condition;
    //Finance
    private String accountCode;
    private int PONumber;
    @OneToOne
    private Vendor vendor;
    private double purchasePrice;
    private double currentValue;
    private double scrapValue;
    //Dates to be changed to date objects
    private String purchasedDate;
    private String inService;
    private String warrantyExpires;

    //child classes
    @OneToMany
    private List<AssetHistory> assetHistories;
    @OneToMany
    private List<AssetNote> assetNotes;
    @OneToMany
    private List<AssetService> assetServices;

}
