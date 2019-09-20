package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
