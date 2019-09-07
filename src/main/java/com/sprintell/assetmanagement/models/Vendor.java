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

    @OneToMany(cascade=CascadeType.ALL)
    private List<VendorAsset> vendorAssets;
    @OneToMany(cascade=CascadeType.ALL)
    private List<VendorNote> vendorNotes;

    public Vendor() {
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<VendorAsset> getVendorAssets() {
        return vendorAssets;
    }

    public void setVendorAssets(List<VendorAsset> vendorAssets) {
        this.vendorAssets = vendorAssets;
    }

    public List<VendorNote> getVendorNotes() {
        return vendorNotes;
    }

    public void setVendorNotes(List<VendorNote> vendorNotes) {
        this.vendorNotes = vendorNotes;
    }
}
