package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
