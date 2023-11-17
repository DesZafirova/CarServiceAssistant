package com.vehicleassistancediary.web;

import com.vehicleassistancediary.service.CarRepairService;
import org.springframework.stereotype.Controller;

@Controller
public class ServiceRegisterController {
    private final CarRepairService carRepairService;

    public ServiceRegisterController(CarRepairService carRepairService) {
        this.carRepairService = carRepairService;
    }


}
