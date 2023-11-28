package com.vehicleassistancediary.service.impl;
import com.vehicleassistancediary.model.entity.CarRepair;
import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CarRepairDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CarRepairSummaryDto;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import com.vehicleassistancediary.repository.CarRepairRepository;
import com.vehicleassistancediary.service.CarRepairService;

import com.vehicleassistancediary.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarRepairServiceImpl implements CarRepairService {

    private final CarRepairRepository carRepairRepository;
    private final ModelMapper modelMapper;
    private final CarService carService;


    public CarRepairServiceImpl(CarRepairRepository carRepairRepository, ModelMapper modelMapper, CarService carService) {
 this.carRepairRepository = carRepairRepository;
        this.modelMapper = modelMapper;
        this.carService = carService;
    }

    @Override
    public List<CarRepairDetailsDto> findByRepairEnum(CarRepairEnum carRepairEnum, UUID uuid) {
        return carRepairRepository.findByRepair(carRepairEnum, uuid)
                .stream()
                .map(carRepair ->  modelMapper.map(carRepair, CarRepairDetailsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CarRepairSummaryDto findLastRepair(UUID uuid) {
       return carRepairRepository.findLastRepair(uuid)
        .map(carRepair -> modelMapper.map(carRepair, CarRepairSummaryDto.class))
                .orElse(null);

    }

    @Override
    public List<CarRepairDetailsDto> findByCarUuid(UUID uuid) {
        return carRepairRepository.findByCar_Uuid(uuid).stream()
                .map(carRepair -> modelMapper.map(carRepair, CarRepairDetailsDto.class))
                .collect(Collectors.toList());
    }

//    @Override
//    public Optional<CarRepairSummaryDto> getCarRepairSummary(UUID uuid) {
//        return carRepairRepository.findByUuidAndRepair()
//    }
//    public static CarRepairSummaryDto mapAsSummary(CarRepair carRepair){
//        return new CarRepairSummaryDto().ge
//    }


}

