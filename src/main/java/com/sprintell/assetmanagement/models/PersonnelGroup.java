package com.sprintell.assetmanagement.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonnelGroup {
    @Id
    private int personnelGroupId;
    private String description;
    private String name;

}
