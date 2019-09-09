package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Asset;
import org.springframework.data.repository.CrudRepository;

public interface AssetRepository extends CrudRepository<Asset, Long> {
}
