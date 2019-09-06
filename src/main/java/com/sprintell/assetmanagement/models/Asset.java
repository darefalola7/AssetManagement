package com.sprintell.assetmanagement.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetId;

    private String assetNumber;
    private String description;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="categoryId")
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
    private double purchasePrice;
    private double currentValue;
    private double scrapValue;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="vendorId")
    private Vendor vendor;

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
