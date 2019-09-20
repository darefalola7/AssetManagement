package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
