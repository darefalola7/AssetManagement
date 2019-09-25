package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    List<Status> findByIdIn(List<Long> ids);

    Optional<Status> findById(Long id);

    @Query(value = "Select status from Status status WHERE lower( CONCAT(status.name, status.description) ) LIKE lower(CONCAT('%',:search,'%')) ")
    List<Status> findBySearchParameter(@Param("search") String search);


}
