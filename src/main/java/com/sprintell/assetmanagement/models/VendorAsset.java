package com.sprintell.assetmanagement.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class VendorAsset {

    @OneToOne
    private Asset asset;
    private String description;
    private String dateCreated;
    private String dateUpdated;

}
