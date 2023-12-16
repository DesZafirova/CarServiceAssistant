package com.vehicleassistancediary.model.entity;

import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import com.vehicleassistancediary.model.entity.enums.RepairStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "repairs")
public class CarRepair  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @NotNull
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarRepairEnum repair;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate repairDate;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Future
    private LocalDate dueDate;
    @NotNull
    @Positive
    private Integer mileage;

    @ManyToOne(fetch = FetchType.EAGER)
    private CarEntity car;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity service;
    @Enumerated(EnumType.STRING)
    @NotNull
    private RepairStatusEnum status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarRepairEnum getRepair() {
        return repair;
    }

    public void setRepair(CarRepairEnum repair) {
        this.repair = repair;
    }

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

    public UserEntity getService() {
        return service;
    }

    public void setService(UserEntity service) {
        this.service = service;
    }

    public RepairStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RepairStatusEnum status) {
        this.status = status;
    }
    public void setRepairDateFromString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.repairDate = LocalDate.parse(dateString, formatter);
    }
}
