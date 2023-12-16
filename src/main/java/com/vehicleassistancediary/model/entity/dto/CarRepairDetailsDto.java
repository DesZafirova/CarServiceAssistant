package com.vehicleassistancediary.model.entity.dto;

import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
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

    private String repairDate;

    private String description;

    private LocalDate dueDate;

    private Integer mileage;


    private UserEntity service;






}
