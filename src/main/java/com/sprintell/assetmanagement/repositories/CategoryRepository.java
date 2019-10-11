package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);

    @Query(value = "Select category from Category category WHERE " +
            "lower( CONCAT(category.name, category.description) ) LIKE lower(CONCAT('%',:search,'%')) ")
    List<Category> findBySearchParameter(@Param("search") String search);

}
