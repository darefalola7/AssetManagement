package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

//    List<Department> findByIdIn(List<Long> ids);

    Optional<Department> findById(Long id);

    @Query(value = "Select department from Department department WHERE lower( CONCAT(department.name, department.description) ) LIKE lower(CONCAT('%',:search,'%')) ")
    List<Department> findBySearchParameter(@Param("search") String search);

}
