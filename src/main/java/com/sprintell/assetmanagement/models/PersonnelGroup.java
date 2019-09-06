package com.sprintell.assetmanagement.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PersonnelGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personnelGroupId;
    private String description;
    private String name;

}