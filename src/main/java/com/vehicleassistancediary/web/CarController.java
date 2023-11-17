package com.vehicleassistancediary.web;

import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CarRepairSummaryDto;
import com.vehicleassistancediary.model.entity.dto.CreateCarDto;
import com.vehicleassistancediary.model.entity.enums.EngineTypeEnum;
import com.vehicleassistancediary.model.entity.enums.VehicleTypeEnum;
import com.vehicleassistancediary.service.CarRepairService;
import com.vehicleassistancediary.service.CarService;
import com.vehicleassistancediary.service.exeption.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    private final CarRepairService carRepairService;

    public CarController(CarService carService, CarRepairService carRepairService) {
        this.carService = carService;
        this.carRepairService = carRepairService;
    }

    @ModelAttribute("engines")
    public EngineTypeEnum[] engines() {
        return EngineTypeEnum.values();
    }

    @ModelAttribute("types")
    public VehicleTypeEnum[] types() {
        return VehicleTypeEnum.values();
    }


    @GetMapping("/add")
        public String add(Model model){
            if(!model.containsAttribute("isFound")){
                model.addAttribute("isFound", true);
            }
            model.addAttribute("createCarDto", new CreateCarDto());
            return "car-add";
        }


    @PostMapping("/add")
    public String add(@Valid CreateCarDto createCarDto,
                      BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("createCarDto", createCarDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createCarDto", bindingResult);
            return "redirect:/car/add";
        }
        //todo
        UUID newCarUUID = carService.addNewCar(createCarDto);
        model.addAttribute("createCarDto", createCarDto);
        return "redirect:/car/" + newCarUUID;

    }
    @GetMapping("/{uuid}")
    public String details(@PathVariable("uuid") UUID uuid, Model model) {

        CarDetailsDto carDetailsDto = carService
                .getCarDetail(uuid)
                .orElseThrow(() -> new ObjectNotFoundException("Car with uuid " + uuid + " not found!"));

        model.addAttribute("car", carDetailsDto);
//        CarRepairSummaryDto carRepairSummaryDto = carRepairService.getCarRepairSummary(uuid)
//                .orElseThrow(() -> new ObjectNotFoundException("Repair with uuid " + uuid + " not found!"));
//        model.addAttribute("repairs", carRepairSummaryDto);


        return "details" ;
    }
    @DeleteMapping("/{uuid}")
    public String delete(@PathVariable("uuid") UUID uuid) {

        carService.deleteCar(uuid);

        return "redirect:/garage/all";
    }
    @ModelAttribute
    public CarDetailsDto carDetailsDto(){
        return new CarDetailsDto();
    }



}
