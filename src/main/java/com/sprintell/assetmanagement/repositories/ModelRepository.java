package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {

    Optional<Model> findById(Long id);

    @Query(value = "Select model from Model model WHERE lower( CONCAT(model.name, model.description) ) LIKE lower(CONCAT('%',:search,'%')) ")
    List<Model> findBySearchParameter(@Param("search") String search);

}
