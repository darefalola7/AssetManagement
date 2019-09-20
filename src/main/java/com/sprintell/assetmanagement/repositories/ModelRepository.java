package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
