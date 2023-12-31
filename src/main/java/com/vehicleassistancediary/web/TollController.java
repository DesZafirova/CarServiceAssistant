package com.vehicleassistancediary.web;


import com.vehicleassistancediary.model.entity.TollResponse;
import com.vehicleassistancediary.model.entity.dto.TollDetailsDto;
import com.vehicleassistancediary.model.entity.dto.TollDto;
import com.vehicleassistancediary.service.impl.TollService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/toll")
public class TollController {

    private final TollService tollService;

    public TollController(TollService tollService) {
        this.tollService = tollService;
    }

    @PostMapping("/check")
    public TollResponse checkToll(@RequestBody TollDto request) {
        return tollService.checkToll(request.getRegistrationNumber());
    }
    @ModelAttribute
    public TollDto tollDto(){
        return new TollDto();
    }
}
