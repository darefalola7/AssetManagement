package com.sprintell.assetmanagement.repositories;

import com.sprintell.assetmanagement.models.AssetNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetNoteRepository extends JpaRepository<AssetNote, Long> {
}
