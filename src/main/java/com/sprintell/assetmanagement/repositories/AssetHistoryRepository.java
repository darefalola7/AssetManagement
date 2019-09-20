package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.AssetHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetHistoryRepository extends JpaRepository<AssetHistory, Long> {
}
