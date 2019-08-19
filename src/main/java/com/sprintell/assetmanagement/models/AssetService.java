package com.sprintell.assetmanagement.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AssetService {
    @Id
    private int serviceId;
    private String description;
    private String serviceDate;
    private double partCost;
    private double laborCost;
    private String dueDate;

}
