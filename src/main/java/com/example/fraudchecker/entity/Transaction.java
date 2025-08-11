package com.example.fraudchecker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;
    private String location;
    private String device;
    private String merchant;
    private int riskScore;
    private String riskLevel;

    // Constructors
    public Transaction() {}

    // Getters and setters
    public Long getId() { return id; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getDevice() { return device; }
    public void setDevice(String device) { this.device = device; }
    public String getMerchant() { return merchant; }
    public void setMerchant(String merchant) { this.merchant = merchant; }
    public int getRiskScore() { return riskScore; }
    public void setRiskScore(int riskScore) { this.riskScore = riskScore; }
    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }
}
