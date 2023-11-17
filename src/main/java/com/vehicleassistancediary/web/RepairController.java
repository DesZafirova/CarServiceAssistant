package com.vehicleassistancediary.web;

import com.vehicleassistancediary.model.entity.dto.CarRepairSummaryDto;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import com.vehicleassistancediary.service.CarRepairService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class RepairController {
    private final CarRepairService carRepairService;

    public RepairController(CarRepairService carRepairService) {
        this.carRepairService = carRepairService;
    }


    @GetMapping("/repair")
    public String repair(Model model){

        List<CarRepairSummaryDto> oilChangeLog = carRepairService.findByRepairEnum(CarRepairEnum.OilChange);
        model.addAttribute("oilChange", oilChangeLog);
        List<CarRepairSummaryDto> TireReplacementLog = carRepairService.findByRepairEnum(CarRepairEnum.TireReplacement);
        model.addAttribute("tireReplacementLog", TireReplacementLog);
        List<CarRepairSummaryDto> antifreezeAndCoolingSystemLog = carRepairService.findByRepairEnum(CarRepairEnum.AntifreezeAndCoolingSystem);
        model.addAttribute("antifreezeAndCoolingSystemLog", antifreezeAndCoolingSystemLog);

        return "repair";
    }


}
