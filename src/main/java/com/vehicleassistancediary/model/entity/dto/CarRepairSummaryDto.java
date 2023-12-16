package com.vehicleassistancediary.model.entity.dto;

import com.vehicleassistancediary.model.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class CarRepairSummaryDto {
    private String id;
    private String repair;
    private LocalDate repairDate;

    private Integer mileage;
    private String description;
    private UserEntity service;


    public String summary() {
        return repair;
    }
}
