package com.vehicleassistancediary.model.entity.dto;

import com.vehicleassistancediary.model.entity.enums.EngineTypeEnum;
import com.vehicleassistancediary.model.entity.enums.VehicleTypeEnum;
import com.vehicleassistancediary.model.validations.YearNotInTheFuture;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public class CreateCarDto {
    @NotEmpty
    @Size(min = 3, max = 25, message = "Make length must be between 3 and 25 characters")
    private String make;
    @NotNull
    private String model;
    @NotNull(message = "Year must be provided!")
    @Min(1950)
    @YearNotInTheFuture(message = "The year should not be in the future!")
    private Integer year;
    @NotNull
    @Positive
    private Integer kilometers;
    @NotEmpty
    @Size(min = 8, max = 8)
    private String registrationNumber;
    @NotEmpty
    @Size(min = 13, max = 17)
    private String vin;


    @NotNull
    private MultipartFile imageUrl;

    @NotNull
    private VehicleTypeEnum vehicleType;
    @NotNull
    private EngineTypeEnum engineType;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public VehicleTypeEnum getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleTypeEnum vehicleType) {
        this.vehicleType = vehicleType;
    }

    public EngineTypeEnum getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineTypeEnum engineType) {
        this.engineType = engineType;
    }

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
    }
}
