package com.vehicleassistancediary.web;

import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.dto.GarageSummaryDTO;
import com.vehicleassistancediary.service.CarService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
                      ) Pageable pageable, @AuthenticationPrincipal UserDetails user) {

        Page<GarageSummaryDTO> allCars = carService.getAllCars(user, pageable);
//        if(allCars == null){
//            return "redirect:/car/add";
//        }

        model.addAttribute("cars", allCars);

        return "garage";
    }


}
