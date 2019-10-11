package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    Optional<Asset> findById(Long id);

    @Query(value = "Select asset from Asset asset WHERE " +
            "lower( asset.asset_description ) LIKE lower(CONCAT('%',:search,'%')) ")
    List<Asset> findBySearchParameter(@Param("search") String search);

}
