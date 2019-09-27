package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    //List<Brand> findByIdIn(List<Long> ids);

    Optional<Brand> findById(Long id);

    @Query(value = "Select brand from Brand brand WHERE lower( CONCAT(brand.name, brand.description) ) LIKE lower(CONCAT('%',:search,'%')) ")
    List<Brand> findBySearchParameter(@Param("search") String search);

}
