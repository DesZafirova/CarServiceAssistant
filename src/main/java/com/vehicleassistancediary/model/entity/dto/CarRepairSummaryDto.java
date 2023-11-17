package com.vehicleassistancediary.model.entity.dto;

import com.vehicleassistancediary.service.CarRepairService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class CarRepairSummaryDto {
    private Long id;
    private String repair;
    private LocalDate repairDate;

    private Integer mileage;
    private String description;


    public String summary() {
        return repair;
    }
}
