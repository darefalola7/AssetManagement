package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.AssetCheckedOut;
import org.springframework.data.repository.CrudRepository;

public interface AssetCheckedOutRepository extends CrudRepository<AssetCheckedOut, Long> {
}
