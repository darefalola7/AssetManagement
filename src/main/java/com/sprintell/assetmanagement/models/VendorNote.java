package com.sprintell.assetmanagement.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class VendorNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long noteId;

    @Size(max=1000)
     private String note;

    public VendorNote() {
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
