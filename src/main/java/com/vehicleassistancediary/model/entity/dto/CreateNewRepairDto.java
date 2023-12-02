package com.vehicleassistancediary.model.entity.dto;

import com.vehicleassistancediary.model.entity.ServiceEntity;
import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class CreateNewRepairDto {
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String make;
    @NotNull
    private String registrationNumber;
    private CarRepairEnum repair;
    private LocalDate repairDate;
    private List<ServiceEntity> services;

    public CreateNewRepairDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public List<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }

    public CarRepairEnum getRepair() {
        return repair;
    }

    public void setRepair(CarRepairEnum repair) {
        this.repair = repair;
    }

    public LocalDate getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDate repairDate) {
        this.repairDate = repairDate;
    }
}
