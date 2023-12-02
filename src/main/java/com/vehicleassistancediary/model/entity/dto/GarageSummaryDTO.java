package com.vehicleassistancediary.model.entity.dto;

import java.time.LocalDate;

public record GarageSummaryDTO (String id,
                                String make,
                                String model,
                                Integer year,
                                Integer kilometers,
                                String imageUrl

                                ){

    public String summary(){
        return model + " " + kilometers;
    }
}
