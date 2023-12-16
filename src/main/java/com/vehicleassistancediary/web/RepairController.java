package com.vehicleassistancediary.web;

import com.vehicleassistancediary.model.entity.CarEntity;
import com.vehicleassistancediary.model.entity.CarRepair;
import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.dto.AddCarRepairDto;
import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CarRepairDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CreateCarDto;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import com.vehicleassistancediary.service.CarRepairService;
import com.vehicleassistancediary.service.CarService;
import com.vehicleassistancediary.service.UserService;
import com.vehicleassistancediary.service.impl.OilHealthIndicator;
import jakarta.servlet.http.HttpServletRequest;
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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/repair")
public class RepairController {
    private final CarRepairService carRepairService;
    private final OilHealthIndicator oilHealthIndicator;
    private final UserService userService;
    private final CarService carService;
    private final ModelMapper modelMapper;


    public RepairController(CarRepairService carRepairService, OilHealthIndicator oilHealthIndicator, UserService userService, CarService carService, ModelMapper modelMapper) {
        this.carRepairService = carRepairService;
        this.oilHealthIndicator = oilHealthIndicator;
        this.userService = userService;
        this.carService = carService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("/{uuid}")
    public String repair(Model model, @PathVariable("uuid")  UUID uuid, UserDetails userDetails){
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




        return "repair";
    }
    @GetMapping("/add")
    public String addRepair(Model model){
        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }
        model.addAttribute("addCarRepairDto", new AddCarRepairDto());
        return "add-repair";

    }
//    @PostMapping("/add")
//    public String saveRepair( @Valid AddCarRepairDto addCarRepairDto,@ModelAttribute("carsByUser") List<CarEntity> cars,
//                             @AuthenticationPrincipal UserDetails user,@ModelAttribute("allServices")List<UserEntity> services,
//                             BindingResult bindingResult, Model model, RedirectAttributes re) {
//
//        if (bindingResult.hasErrors()) {
//            re.addFlashAttribute("addCarRepairDto", addCarRepairDto);
//            re.addFlashAttribute("com.springframework.validation.BindingResult.addCarRepairDto", bindingResult);
//            return "redirect:/repair/add";
//        }
//        UserEntity userEntity = userService.findByEmail(user.getUsername());
//        model.addAttribute("userEntity", userService.findByEmail(user.getUsername()));
//        List<CarEntity> carsByUser = carService.getCarsByUser(userEntity);
//        model.addAttribute("carsByUser", carsByUser);
//
//        model.addAttribute("allServices", userService.findAllServices());
//        addCarRepairDto.setUser(userEntity);
//
//        if(carsByUser.size() == 0){
//            model.addAttribute("carsNotFound", "Please add a new car.");
//            return "redirect:/car/add";
//        }
//
//        UUID newRepairUuid = carRepairService.saveNewRepair(addCarRepairDto, user);
//
//        // Return a URL or any response as needed
//        return "redirect:/repair/waiting" + newRepairUuid;
//    }
//
    @GetMapping("/waiting-repairs")
    public String waitingRepairs(){
        return "repair";
    }


    @ModelAttribute
    public AddCarRepairDto addCarRepairDto(){
        return new AddCarRepairDto();
    }
}
