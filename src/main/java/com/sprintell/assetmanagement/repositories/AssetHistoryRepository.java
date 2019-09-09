package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.AssetHistory;
import org.springframework.data.repository.CrudRepository;

public interface AssetHistoryRepository extends CrudRepository<AssetHistory, Long> {
}
