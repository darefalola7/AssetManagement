package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
