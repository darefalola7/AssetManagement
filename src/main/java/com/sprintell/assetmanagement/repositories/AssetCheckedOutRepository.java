package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.AssetCheckedOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetCheckedOutRepository extends JpaRepository<AssetCheckedOut, Long> {
}
