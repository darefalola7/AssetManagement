package com.sprintell.assetmanagement.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Personnel {
    @Id
    private String personnelNumber;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String title;

    @OneToOne
    private PersonnelGroup personnelGroup;

    private String status;

    @OneToMany
    private List<PersonnelHistory> personnelHistories;
    @OneToMany
    private List<AssetCheckedOut> assetCheckedOut;



}
