package com.example.service;

import com.example.model.TrancheckEntry;
import com.example.repository.TrancheckRepository;
import org.springframework.stereotype.Service;

@Service
public class TrancheckService {

    private final TrancheckRepository repository;

    public TrancheckService(TrancheckRepository repository) {
        this.repository = repository;
    }

    public void save(TrancheckEntry entry) {
        repository.save(entry);
    }
}
