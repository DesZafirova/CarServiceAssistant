package com.vehicleassistancediary.model.entity.dto;


import java.time.LocalDateTime;

public record TollDetailsDto (String licencePlate, LocalDateTime endDateAndValidityTime,
                              String status) {
}
