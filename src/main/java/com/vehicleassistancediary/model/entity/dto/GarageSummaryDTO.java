package com.vehicleassistancediary.model.entity.dto;

import java.time.LocalDate;

public record GarageSummaryDTO (String id,
                                String make,
                                String model,
                                Integer year,
                                Integer kilometers
                                ){

    public String summary(){
        return model + " " + kilometers;
    }
}
