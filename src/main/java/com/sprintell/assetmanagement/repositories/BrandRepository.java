package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
