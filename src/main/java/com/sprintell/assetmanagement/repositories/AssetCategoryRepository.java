package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface AssetCategoryRepository extends CrudRepository<Category, Long> {
}
