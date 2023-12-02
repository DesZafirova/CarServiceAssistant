package com.vehicleassistancediary.service.impl;
import com.vehicleassistancediary.model.entity.CarRepair;
import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.dto.CarRepairDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CarRepairSummaryDto;
import com.vehicleassistancediary.model.entity.dto.CreateNewRepairDto;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import com.vehicleassistancediary.repository.CarRepairRepository;
import com.vehicleassistancediary.service.CarRepairService;

import com.vehicleassistancediary.service.CarService;
import com.vehicleassistancediary.service.ServiceService;
import com.vehicleassistancediary.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarRepairServiceImpl implements CarRepairService {

    private final CarRepairRepository carRepairRepository;
    private final ModelMapper modelMapper;
    private final CarService carService;
    private final ServiceService serviceService;
    private final UserService userService;



    public CarRepairServiceImpl(CarRepairRepository carRepairRepository, ModelMapper modelMapper, CarService carService, ServiceService serviceService, UserService userService) {
 this.carRepairRepository = carRepairRepository;
        this.modelMapper = modelMapper;
        this.carService = carService;

        this.serviceService = serviceService;
        this.userService = userService;
    }

    @Override
    public List<CarRepairDetailsDto> findByRepairEnum(CarRepairEnum carRepairEnum, UUID uuid) {
        return carRepairRepository.findByRepair(carRepairEnum, uuid)
                .stream()
                .map(carRepair ->  modelMapper.map(carRepair, CarRepairDetailsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CarRepairSummaryDto findLastRepair(UUID uuidCar) {
       return carRepairRepository.findLastRepair(uuidCar)
        .map(carRepair -> modelMapper.map(carRepair, CarRepairSummaryDto.class))
                .orElse(null);

    }

    @Override
    public List<CarRepairDetailsDto> findByCarUuid(UUID uuid) {
        return carRepairRepository.findByCar_Uuid(uuid).stream()
                .map(carRepair -> modelMapper.map(carRepair, CarRepairDetailsDto.class))
                .collect(Collectors.toList());
    }



    @Override
    public UUID addNewRepair(UUID uuid) {
        return null;
    }


}

