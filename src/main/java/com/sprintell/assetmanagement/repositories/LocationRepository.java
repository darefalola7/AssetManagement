package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByIdIn(List<Long> ids);

    Optional<Location> findById(Long id);

    @Query(value = "Select loc from Location loc WHERE lower( CONCAT(loc.name, loc.description) ) LIKE lower(CONCAT('%',:search,'%')) ")
    List<Location> findBySearchParameter(@Param("search") String search);

}
