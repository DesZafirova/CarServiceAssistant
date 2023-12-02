package com.vehicleassistancediary.model.entity.dto;

import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.enums.EngineTypeEnum;
import com.vehicleassistancediary.model.entity.enums.VehicleTypeEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CarDetailsDto {
    String id;

    @NotNull
    private String make;
    @NotNull
    private String model;

    @Min(1970)
    private Integer year;

    @NotNull
    private String registrationNumber;

    @NotNull
    @Length(min = 13, max = 17)
    private String vin;

    @NotEmpty
    String imageUrl;
    @Positive
    @NotNull
    private Integer kilometers;
    @NotNull
    private VehicleTypeEnum vehicleType;
    @NotNull
    private EngineTypeEnum fuelType;

    private Set<CarRepairSummaryDto> carRepairSummary;
    private UserEntity user;

    public CarDetailsDto(String id, String make, String model, Integer year, String registrationNumber, String vin, Integer kilometers, String imageUrl, VehicleTypeEnum vehicleType, EngineTypeEnum fuelType) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.kilometers = kilometers;
        this.imageUrl = imageUrl;
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
    }

    public String summary() {
        return make + " " + model + ", " + year;
    }
}