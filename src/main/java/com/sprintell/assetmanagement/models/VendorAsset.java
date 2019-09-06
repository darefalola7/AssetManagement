package com.sprintell.assetmanagement.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class VendorAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="assetId")
    private Asset asset;

    private String description;
    private LocalDate dateCreated;
    private LocalDate dateUpdated;

}
