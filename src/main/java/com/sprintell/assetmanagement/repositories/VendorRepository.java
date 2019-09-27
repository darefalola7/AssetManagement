package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    //List<Vendor> findByIdIn(List<Long> ids);

    Optional<Vendor> findById(Long id);

    @Query(value = "Select vendor from Vendor vendor WHERE lower( vendor.name ) LIKE lower(CONCAT('%',:search,'%')) ")
    List<Vendor> findBySearchParameter(@Param("search") String search);


}
