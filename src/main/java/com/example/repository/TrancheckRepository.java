package com.example.repository;

import com.example.model.TrancheckEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrancheckRepository extends JpaRepository<TrancheckEntry, Long> {
}
