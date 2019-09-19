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

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name="asset_category",
            joinColumns=@JoinColumn(name="assetId"),
            inverseJoinColumns=@JoinColumn(name="categoryId")
    )
    private List<Category> categories;

    //General
    @Embedded
    AssetGeneral assetGeneral;

    //Finance
    @Embedded
    AssetFinance assetFinance;

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



   }
