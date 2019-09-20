package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
}
