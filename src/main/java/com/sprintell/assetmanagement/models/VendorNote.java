package com.sprintell.assetmanagement.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VendorNote {

    @Id
    private int noteId;
    private String note;

}
