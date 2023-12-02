package com.vehicleassistancediary.web;

import com.vehicleassistancediary.model.entity.CarRepair;
import com.vehicleassistancediary.model.entity.dto.CarRepairDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CreateNewRepairDto;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import com.vehicleassistancediary.service.CarRepairService;
import com.vehicleassistancediary.service.impl.OilHealthIndicator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/repair")
public class RepairController {
    private final CarRepairService carRepairService;
    private final OilHealthIndicator oilHealthIndicator;


    public RepairController(CarRepairService carRepairService, OilHealthIndicator oilHealthIndicator) {
        this.carRepairService = carRepairService;
        this.oilHealthIndicator = oilHealthIndicator;
    }


    @GetMapping("/{uuid}")
    public String repair(Model model, @PathVariable("uuid")  UUID uuid){
        List<CarRepairDetailsDto> findRepairByCarUuid = carRepairService.findByCarUuid(uuid);

        findRepairByCarUuid.stream()
                .map(CarRepairDetailsDto::getRepair)
                .forEach(repairType -> {
                    switch (repairType) {
                        case OilChange:
                            List<CarRepairDetailsDto> oilChangeLog = carRepairService.findByRepairEnum(CarRepairEnum.OilChange, uuid);
                            model.addAttribute("oilChange", oilChangeLog);
                            CarRepairDetailsDto lastOilRepair = oilChangeLog.get(0);
                            model.addAttribute("lastOilRepair", lastOilRepair);

                            break;
                        case TireReplacement:
                            List<CarRepairDetailsDto> tireReplacementLog = carRepairService.findByRepairEnum(CarRepairEnum.TireReplacement, uuid);
                            model.addAttribute("tireReplacementLog", tireReplacementLog);
                            break;
                        case AntifreezeAndCoolingSystem:
                            List<CarRepairDetailsDto> antifreezeAndCoolingSystemLog = carRepairService.findByRepairEnum(CarRepairEnum.AntifreezeAndCoolingSystem, uuid);
                            model.addAttribute("antifreezeAndCoolingSystemLog", antifreezeAndCoolingSystemLog);
                            break;
                        default:
                            //todo handle unknown repair type
                            break;
                    }
                });
        carRepairService.addNewRepair(uuid);


        return "repair";
    }


}
