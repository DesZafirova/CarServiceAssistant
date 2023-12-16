package com.vehicleassistancediary.model.entity.dto;

import java.time.LocalDateTime;

public class TollDto {
    String registrationNumber;

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}
