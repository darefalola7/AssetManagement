package com.sprintell.assetmanagement.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PersonnelHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int historyId;

    private LocalDateTime timestamp;
    private String description;
    private String notes;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="personnelId")
    private Personnel personnel;

    public PersonnelHistory() {
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
