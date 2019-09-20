package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
