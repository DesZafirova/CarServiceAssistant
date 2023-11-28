package com.vehicleassistancediary.model.entity.dto;

import com.vehicleassistancediary.model.entity.ServiceEntity;
import com.vehicleassistancediary.service.CarRepairService;
import com.vehicleassistancediary.service.CarService;
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
    private ServiceEntity service;


    public String summary() {
        return repair;
    }
}
