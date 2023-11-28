package com.vehicleassistancediary.service.impl;

import com.vehicleassistancediary.model.entity.CarEntity;
import com.vehicleassistancediary.model.entity.TollResponse;
import com.vehicleassistancediary.model.entity.dto.TollDto;
import com.vehicleassistancediary.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TollService {
    private final String apiUrl = "https://api.bgtoll.bg/v1/check";
    private final CarService carService;
    private final ModelMapper modelMapper;

    public TollService(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }
    public String getCarByRegistrationNumber(){
        CarEntity carEntity = carService.findByRegistrationNumber(getCarByRegistrationNumber());
       return carEntity.getRegistrationNumber();
    }

    public TollResponse checkToll(String licensePlate) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("{\"licensePlate\": \"" + licensePlate, headers);
        ResponseEntity<TollResponse> response = restTemplate.postForEntity(apiUrl, request, TollResponse.class);
        return response.getBody();
    }
}