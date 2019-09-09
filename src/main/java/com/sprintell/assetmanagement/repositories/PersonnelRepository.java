package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.Personnel;
import org.springframework.data.repository.CrudRepository;

public interface PersonnelRepository extends CrudRepository<Personnel, Long> {
}
