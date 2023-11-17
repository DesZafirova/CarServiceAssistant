package com.vehicleassistancediary.service.impl;


import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.service.CarService;
import com.vehicleassistancediary.service.exeption.ObjectNotFoundException;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OilHealthIndicator implements HealthIndicator {
    private static final int OIL_CHANGE_INTERVAL = 10000; // kilometers
    private static final int OIL_CHANGE_REMINDER = 1000; // kilometers
    private final CarService carService;

    public OilHealthIndicator(CarService carService) {
        this.carService = carService;
    }

    @Override
    public Health health() {
        int currentMileage = getMileageFromOdometer();
        int milesSinceLastOilChange = currentMileage - getLastOilChangeMileage();

        if (milesSinceLastOilChange >= OIL_CHANGE_INTERVAL) {
            setLastOilChangeMileage(currentMileage);
            return Health.up().build();
        } else if (milesSinceLastOilChange >= OIL_CHANGE_REMINDER) {
            return Health.down().withDetail("message", "Oil change due soon").build();
        } else {
            return Health.down().withDetail("message", "Oil change not due yet").build();
        }
    }

    private int getMileageFromOdometer() {
//        return carService.getCarDetail(uuid).map(CarDetailsDto::getKilometers).orElseThrow(() -> new ObjectNotFoundException("No such Vehicle"));
return 0;
    }

    private int getLastOilChangeMileage() {

        // todo code to get last oil change mileage
        return 0;
    }

    private void setLastOilChangeMileage(int mileage) {
        // todo code to set last oil change mileage
    }
}