package com.example.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TrancheckEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  
    public void setTransactionId(String transactionId2) {
        throw new UnsupportedOperationException("Unimplemented method 'setTransactionId'");
    }
    public void setAmount(Double amount2) {
        throw new UnsupportedOperationException("Unimplemented method 'setAmount'");
    }
    public void setCardHolder(String cardHolder2) {
        throw new UnsupportedOperationException("Unimplemented method 'setCardHolder'");
    }
    public void setTimestamp(LocalDateTime now) {
        throw new UnsupportedOperationException("Unimplemented method 'setTimestamp'");
    }

    // Getters and setters
}
