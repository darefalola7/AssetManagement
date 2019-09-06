package com.sprintell.assetmanagement.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String vendorId;

    private String vendorName;
    private String email;
    private String phone;
    private String contactName;
    private String website;
    private String addressOne;
    private String addressTwo;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    @OneToMany
    private List<VendorAsset> vendorAssets;
    @OneToMany
    private List<VendorNote> vendorNotes;

}
