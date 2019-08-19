package com.sprintell.assetmanagement.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonnelHistory {
    @Id
    private int historyId;
    private String timestamp;
    private String description;
    private String notes;


}
