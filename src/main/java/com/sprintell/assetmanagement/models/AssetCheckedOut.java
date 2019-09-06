package com.sprintell.assetmanagement.models;

import javax.persistence.*;

@Entity
public class AssetCheckedOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetCheckedOutId;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="assetId")
    private Asset asset;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="personnelNumber")
    private Personnel personnel;

    private String description;
    private String dueDate;


}
