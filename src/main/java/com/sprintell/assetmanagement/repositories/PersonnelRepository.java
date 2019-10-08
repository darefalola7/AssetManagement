package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    //List<Personnel> findByIdIn(List<Long> ids);

    @Query(value="SELECT personnel_id FROM Personnel WHERE personnel_id = (SELECT MAX(personnel_id) FROM Personnel)",
            nativeQuery = true)
    Long getMaxId();

    Optional<Personnel> findById(Long id);

    @Query(value = "Select persnel from Personnel persnel WHERE lower( CONCAT(persnel.firstname, persnel.lastname) ) LIKE lower(CONCAT('%',:search,'%')) ")
    List<Personnel> findBySearchParameter(@Param("search") String search);


}
