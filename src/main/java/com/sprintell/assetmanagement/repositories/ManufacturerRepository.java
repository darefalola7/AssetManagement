package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    List<Manufacturer> findByIdIn(List<Long> ids);

    Optional<Manufacturer> findById(Long id);

    @Query(value = "Select manuf from Manufacturer manuf WHERE lower( CONCAT(manuf.name, manuf.description) ) LIKE lower(CONCAT('%',:search,'%')) ")
    List<Manufacturer> findBySearchParameter(@Param("search") String search);

}
