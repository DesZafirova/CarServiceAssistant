package com.vehicleassistancediary.web;

import com.vehicleassistancediary.model.entity.ServiceEntity;
import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CarRepairSummaryDto;
import com.vehicleassistancediary.model.entity.dto.CreateCarDto;
import com.vehicleassistancediary.model.entity.enums.EngineTypeEnum;
import com.vehicleassistancediary.model.entity.enums.VehicleTypeEnum;
import com.vehicleassistancediary.service.*;
import com.vehicleassistancediary.service.exeption.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    private final CloudinaryImageService cloudinaryImageService;
    private final CarRepairService carRepairService;
    private final ServiceService serviceService;
    private final UserService userService;


    public CarController(CarService carService, CloudinaryImageService cloudinaryImageService, CarRepairService carRepairService, ServiceService serviceService, UserService userService) {
        this.carService = carService;
        this.cloudinaryImageService = cloudinaryImageService;
        this.carRepairService = carRepairService;
        this.serviceService = serviceService;
        this.userService = userService;

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
    public String add(Model model) {
        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }
        model.addAttribute("createCarDto", new CreateCarDto());
        return "car-add";
    }


    @PostMapping("/add")
    public String add(@Valid CreateCarDto createCarDto, @AuthenticationPrincipal UserDetails user,
                      BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createCarDto", createCarDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createCarDto", bindingResult);
            return "redirect:/car/add";
        }
        UUID newCarUUID = carService.addNewCar(createCarDto, user);

        model.addAttribute("createCarDto", createCarDto);
        return "redirect:/car/" + newCarUUID;

    }

    @GetMapping("/{uuid}")
    public String details(@PathVariable("uuid") UUID uuid, Model model, @AuthenticationPrincipal UserDetails user) {

        CarDetailsDto carDetailsDto = carService
                .getCarDetail(uuid)
                .orElseThrow(() -> new ObjectNotFoundException("Car with uuid " + uuid + " not found!"));

        model.addAttribute("car", carDetailsDto);

        CarRepairSummaryDto carRepairSummaryDto = carRepairService.findLastRepair(uuid);
        if (carRepairSummaryDto != null) {
            model.addAttribute("lastRepair", carRepairSummaryDto);
        }
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        model.addAttribute("user", userEntity);
        List<ServiceEntity> allServices = serviceService.findAllServices();
        model.addAttribute("allServices", allServices);

        return "details";
    }

    @DeleteMapping("/{uuid}")
    public String delete(@PathVariable("uuid") UUID uuid) {

        carService.deleteCar(uuid);

        return "redirect:/garage/all";
    }

    @ModelAttribute
    public CarDetailsDto carDetailsDto() {
        return new CarDetailsDto();
    }

}
