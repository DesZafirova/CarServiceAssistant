package com.vehicleassistancediary.web;

import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.model.entity.dto.GarageSummaryDTO;
import com.vehicleassistancediary.service.CarService;
import com.vehicleassistancediary.service.exeption.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/garage")
public class GarageController {
    private final CarService carService;

    public GarageController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/all")
    public String all(Model model,
                      @PageableDefault(
                              size = 3,
                              sort = "uuid"
                      ) Pageable pageable) {

        Page<GarageSummaryDTO> allCars = carService.getAllCars(pageable);

        model.addAttribute("cars", allCars);

        return "garage";
    }


}
