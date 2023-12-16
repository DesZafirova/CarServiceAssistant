package com.vehicleassistancediary.model.entity.dto;


import java.time.LocalDateTime;

public record TollDetailsDto (String registrationNumber, LocalDateTime endDateAndValidityTime,
                              String status) {
}
