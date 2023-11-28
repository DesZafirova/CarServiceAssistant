package com.vehicleassistancediary.model.entity.dto;

import com.vehicleassistancediary.model.entity.CarEntity;
import com.vehicleassistancediary.model.entity.ServiceEntity;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

public class CarRepairDetailsDto {
    private UUID uuid;


    private CarRepairEnum repair;

    private LocalDate repairDate;

    private String description;

    private LocalDate dueDate;

    private Integer mileage;


    private ServiceEntity service;






}
