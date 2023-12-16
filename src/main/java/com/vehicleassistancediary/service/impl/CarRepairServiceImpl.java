package com.vehicleassistancediary.service.impl;
import com.vehicleassistancediary.model.entity.CarEntity;
import com.vehicleassistancediary.model.entity.CarRepair;
import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.dto.AddCarRepairDto;
import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CarRepairDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CarRepairSummaryDto;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import com.vehicleassistancediary.model.entity.enums.RepairStatusEnum;
import com.vehicleassistancediary.repository.CarRepairRepository;
import com.vehicleassistancediary.service.CarRepairService;

import com.vehicleassistancediary.service.CarService;
import com.vehicleassistancediary.service.UserService;
import com.vehicleassistancediary.service.exeption.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarRepairServiceImpl implements CarRepairService {

    private final CarRepairRepository carRepairRepository;
    private final ModelMapper modelMapper;
    private final CarService carService;

    private final UserService userService;



    public CarRepairServiceImpl(CarRepairRepository carRepairRepository, ModelMapper modelMapper, CarService carService, UserService userService) {
 this.carRepairRepository = carRepairRepository;
        this.modelMapper = modelMapper;
        this.carService = carService;

        this.userService = userService;
    }

    @Override
    public List<CarRepairDetailsDto> findByRepairEnum(CarRepairEnum carRepairEnum, UUID uuid) {
        return carRepairRepository.findByRepair(carRepairEnum, uuid)
                .stream()
                .map(carRepair ->  modelMapper.map(carRepair, CarRepairDetailsDto.class))
                .skip(1)
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
    @Transactional
    public UUID saveNewRepair(AddCarRepairDto addCarRepairDto, UserDetails user) {

        CarRepair carRepair = modelMapper.map(addCarRepairDto, CarRepair.class);
        carRepair.setUuid(UUID.randomUUID());
        carRepair.setMileage(addCarRepairDto.getCar().getKilometers());
        carRepair.setService(addCarRepairDto.getService());

        carRepair.setRepair(addCarRepairDto.getRepair());
        carRepair.setStatus(RepairStatusEnum.WAITING);
        carRepairRepository.save(carRepair);
        return carRepair.getUuid();
    }

//    @Override
//    public void addNewRepair(UserEntity userEntity, CarDetailsDto carDetailsDto, List<UserEntity> allServices) {
//        CarRepair carRepair = new CarRepair();
//        CarEntity carEntity = modelMapper.map(carDetailsDto, CarEntity.class);
//        carEntity.setId(Long.valueOf(carDetailsDto.getId()));
//
//        carRepair.setUuid(UUID.randomUUID());
//        carRepair.setCar(carEntity);
//        carRepairRepository.save(carRepair);
//    }


}

