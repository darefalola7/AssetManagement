package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
