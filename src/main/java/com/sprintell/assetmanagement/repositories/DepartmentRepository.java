package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
