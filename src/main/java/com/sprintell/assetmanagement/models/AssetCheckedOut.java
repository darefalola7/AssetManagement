package com.sprintell.assetmanagement.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class AssetCheckedOut {

    @OneToOne
    private Asset asset;

    @OneToOne
    private Personnel personnel;

    private String description;
    private String dueDate;


}
