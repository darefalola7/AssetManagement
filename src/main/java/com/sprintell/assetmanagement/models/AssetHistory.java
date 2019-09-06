package com.sprintell.assetmanagement.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class AssetHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetHistoryId;

    private LocalDateTime timestamp;
    private String description;
    private String notes;
}
