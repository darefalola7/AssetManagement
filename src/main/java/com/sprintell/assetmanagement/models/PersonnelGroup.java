package com.sprintell.assetmanagement.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PersonnelGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personnelGroupId;
    private String description;
    private String name;

    public PersonnelGroup() {
    }

    public Long getPersonnelGroupId() {
        return personnelGroupId;
    }

    public void setPersonnelGroupId(Long personnelGroupId) {
        this.personnelGroupId = personnelGroupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
