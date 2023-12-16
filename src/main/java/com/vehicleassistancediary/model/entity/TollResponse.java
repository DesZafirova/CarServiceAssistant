package com.vehicleassistancediary.model.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Entity
@Table(name = "tolls")
public class TollResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    private String identificationNumber;
    private String vehicleClass;
    private String emissionClass;
    private LocalDateTime startDateAndValidityTime;
    @DateTimeFormat(pattern = "dd.MM.yyyy hh.mm.ss")
    private LocalDateTime endDateAndValidityTime;
    private String amount;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getEmissionClass() {
        return emissionClass;
    }

    public void setEmissionClass(String emissionClass) {
        this.emissionClass = emissionClass;
    }

    public LocalDateTime getStartDateAndValidityTime() {
        return startDateAndValidityTime;
    }

    public void setStartDateAndValidityTime(LocalDateTime startDateAndValidityTime) {
        this.startDateAndValidityTime = startDateAndValidityTime;
    }

    public LocalDateTime getEndDateAndValidityTime() {
        return endDateAndValidityTime;
    }

    public void setEndDateAndValidityTime(LocalDateTime endDateAndValidityTime) {
        this.endDateAndValidityTime = endDateAndValidityTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
