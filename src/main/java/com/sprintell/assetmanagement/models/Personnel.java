package com.sprintell.assetmanagement.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String personnelNumber;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String title;
    private String status;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="personnelGroupId")
    private PersonnelGroup personnelGroup;

    @OneToMany
    private List<PersonnelHistory> personnelHistories;
    @OneToMany
    private List<AssetCheckedOut> assetCheckedOut;



}
