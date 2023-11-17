package com.vehicleassistancediary.model.entity;

import com.vehicleassistancediary.model.entity.BaseEntity;
import com.vehicleassistancediary.model.entity.CarEntity;
import com.vehicleassistancediary.model.entity.ServiceEntity;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "repairs")
public class CarRepair extends BaseEntity {
    @NotNull
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarRepairEnum repair;
    @PastOrPresent
    @Column(nullable = false)
    private LocalDate repairDate;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Future
    private LocalDate dueDate;
    @NotNull
    @Positive
    private Integer mileage;

    @ManyToOne
    private CarEntity car;
    @ManyToOne
    private ServiceEntity service;



    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public CarRepairEnum getCarRepairEnum() {
        return repair;
    }

    public void setCarRepair(CarRepairEnum repair) {
        this.repair = repair;
    }

    public LocalDate getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDate repairDate) {
        this.repairDate = repairDate;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }
}
