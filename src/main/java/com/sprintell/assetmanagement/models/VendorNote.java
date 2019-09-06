package com.sprintell.assetmanagement.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VendorNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int noteId;
     private String note;

}
