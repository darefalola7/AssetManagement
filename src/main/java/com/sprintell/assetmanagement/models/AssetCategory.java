package com.sprintell.assetmanagement.models;

import javax.persistence.Entity;

@Entity
public class AssetCategory {

    private int category_id;
    private String name;
    private String description;

    public AssetCategory() {
    }

    public AssetCategory(int id, String name, String description) {
        this.category_id = id;
        this.name = name;
        this.description = description;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
