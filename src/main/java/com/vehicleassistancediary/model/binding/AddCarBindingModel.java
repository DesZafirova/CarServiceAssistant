package com.vehicleassistancediary.model.binding;

import com.vehicleassistancediary.model.entity.enums.EngineTypeEnum;
import com.vehicleassistancediary.model.entity.enums.VehicleTypeEnum;
import com.vehicleassistancediary.model.entity.CarRepair;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class AddCarBindingModel {
    private String id;
    @Length(min = 3)
    private String make;
    @Length(min = 3)
    private String model;
    @NotNull
    private LocalDate year;
    @UniqueElements
    private String registrationNumber;
    @UniqueElements
    private String vin;

    @Positive
    private BigDecimal kilometers;
    @NotNull
    private VehicleTypeEnum vehicleType;
    @NotNull

    private EngineTypeEnum fuelType;
    @NotNull
    private Set<CarRepair> carRepair;

}
