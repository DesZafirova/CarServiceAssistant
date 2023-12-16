package com.vehicleassistancediary.web;

import com.vehicleassistancediary.model.binding.AddRepairBindingModel;
import com.vehicleassistancediary.model.entity.CarEntity;
import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.dto.AddCarRepairDto;
import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CarRepairSummaryDto;
import com.vehicleassistancediary.model.entity.dto.CreateCarDto;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import com.vehicleassistancediary.model.entity.enums.EngineTypeEnum;
import com.vehicleassistancediary.model.entity.enums.VehicleTypeEnum;
import com.vehicleassistancediary.service.*;
import com.vehicleassistancediary.service.exeption.ObjectNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
public class CarController {
    private final CarService carService;
    private final CloudinaryImageService cloudinaryImageService;
    private final CarRepairService carRepairService;
    private final UserService userService;
    private final ModelMapper modelMapper;


    public CarController(CarService carService, CloudinaryImageService cloudinaryImageService, CarRepairService carRepairService, UserService userService, ModelMapper modelMapper) {
        this.carService = carService;
        this.cloudinaryImageService = cloudinaryImageService;
        this.carRepairService = carRepairService;

        this.userService = userService;

        this.modelMapper = modelMapper;
    }

    @ModelAttribute("engines")
    public EngineTypeEnum[] engines() {
        return EngineTypeEnum.values();
    }

    @ModelAttribute("types")
    public VehicleTypeEnum[] types() {
        return VehicleTypeEnum.values();
    }


    @GetMapping("car/add")
    public String add(Model model) {
        //todo
        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }
        model.addAttribute("createCarDto", new CreateCarDto());
        return "car-add";
    }


    @PostMapping("/car/add")
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

    @GetMapping(value = {"car/{uuid}", "/car/repair"})
    public String details(@PathVariable("uuid") UUID uuid,Model model, @AuthenticationPrincipal UserDetails user) {

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
        List<UserEntity> allServices = userService.findAllServices();
        model.addAttribute("allServices", allServices);

        return "details";
    }

    @PostMapping("/car/repair")
    public String saveRepair( @Valid AddCarRepairDto addCarRepairDto,@ModelAttribute("carsByUser") List<CarEntity> cars,
                              @AuthenticationPrincipal UserDetails user,@ModelAttribute("allServices")List<UserEntity> services,
                              BindingResult bindingResult, Model model, RedirectAttributes re) {

        if (bindingResult.hasErrors()) {
            re.addFlashAttribute("addCarRepairDto", addCarRepairDto);
            re.addFlashAttribute("com.springframework.validation.BindingResult.addCarRepairDto", bindingResult);
            return "redirect:/repair/add";
        }
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        model.addAttribute("userEntity", userService.findByEmail(user.getUsername()));
        List<CarEntity> carsByUser = carService.getCarsByUser(userEntity);
        model.addAttribute("carsByUser", carsByUser);

        model.addAttribute("allServices", userService.findAllServices());
        addCarRepairDto.setUser(userEntity);

        if (carsByUser.size() == 0) {
            model.addAttribute("carsNotFound", "Please add a new car.");
            return "redirect:/car/add";
        }

        UUID newRepairUuid = carRepairService.saveNewRepair(addCarRepairDto, user);

        // Return a URL or any response as needed
        return "redirect:/repair/" + newRepairUuid;

    }



    @DeleteMapping("/{uuid}")
    public String delete(@PathVariable("uuid") UUID uuid) {

        carService.deleteCar(uuid);

        return "redirect:/garage/all";
    }
    @ModelAttribute
    public AddCarRepairDto addCarRepairDto(){
        return new AddCarRepairDto();
    }

    @ModelAttribute
    public CarDetailsDto carDetailsDto() {
        return new CarDetailsDto();
    }

}
