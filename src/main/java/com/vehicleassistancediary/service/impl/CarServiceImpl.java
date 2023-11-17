package com.vehicleassistancediary.service.impl;

import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.model.entity.CarEntity;
import com.vehicleassistancediary.model.entity.dto.CreateCarDto;
import com.vehicleassistancediary.model.entity.dto.GarageSummaryDTO;
import com.vehicleassistancediary.repository.CarRepository;
import com.vehicleassistancediary.service.CarService;
import com.vehicleassistancediary.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, UserService userService, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public Page<GarageSummaryDTO> getAllCars(Pageable pageable) {
        return carRepository.findAll(pageable).map(CarServiceImpl::mapAsSummary);
    }

    @Override
    public UUID addNewCar(CreateCarDto createCarDto) {
        CarEntity carEntity = modelMapper.map(createCarDto, CarEntity.class);
        carEntity.setUuid(UUID.randomUUID());
        carRepository.save(carEntity);
        return carEntity.getUuid();

    }

    private static GarageSummaryDTO mapAsSummary(CarEntity carEntity) {
        return new GarageSummaryDTO(
                carEntity.getUuid().toString(),
                carEntity.getMake(),
                carEntity.getModel(),
                carEntity.getYear(),
                carEntity.getKilometers()
        );
    }



    @Override
    public Optional<CarDetailsDto> getCarDetail(UUID carUuid) {
        return carRepository
                .findByUuid(carUuid).map(CarServiceImpl::mapAsDetails);

    }

    @Override
    @Transactional
    public void deleteCar(UUID uuid) {
        carRepository.deleteByUuid(uuid);
    }


    private static CarDetailsDto mapAsDetails(CarEntity carEntity) {
        return new CarDetailsDto(
                carEntity.getUuid().toString(),
                carEntity.getMake(),
                carEntity.getModel(),
                carEntity.getYear(),
                carEntity.getRegistrationNumber(),
                carEntity.getVin(),
                carEntity.getKilometers(),
                carEntity.getVehicleType(),
                carEntity.getEngineType()
        );
    }
}