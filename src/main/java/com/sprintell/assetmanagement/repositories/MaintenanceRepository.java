package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
}
