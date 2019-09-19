package com.sprintell.assetmanagement.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetId;

    private String assetNumber;
    private String assetDescription;

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
    private List<Location> locations;

    @OneToMany(mappedBy="asset",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH})
    private List<Department> departments;

    @OneToMany(mappedBy="asset",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH})
    private List<Manufacturer> manufacturers;

    @OneToMany(mappedBy="asset",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH})
    private List<Status> statuses;

    @OneToMany(mappedBy="asset",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH})
    private List<Model> models;

    @OneToMany(mappedBy="asset",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH})
    private List<Brand> brands;

    @OneToMany(mappedBy="asset",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH})
    private List<Maintenance> maintenances;

    public Asset() {
    }



   }
