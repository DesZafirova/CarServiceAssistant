package com.vehicleassistancediary.service.impl;

import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.model.entity.CarEntity;
import com.vehicleassistancediary.model.entity.dto.CreateCarDto;
import com.vehicleassistancediary.model.entity.dto.GarageSummaryDTO;
import com.vehicleassistancediary.repository.CarRepository;
import com.vehicleassistancediary.service.CarService;
import com.vehicleassistancediary.service.CloudinaryImageService;
import com.vehicleassistancediary.service.UserService;
import com.vehicleassistancediary.service.exeption.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CloudinaryImageService cloudinaryImageService;

    public CarServiceImpl(CarRepository carRepository, UserService userService, ModelMapper modelMapper, CloudinaryImageService cloudinaryImageService) {
        this.carRepository = carRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.cloudinaryImageService = cloudinaryImageService;
    }



    @Override
    public Page<GarageSummaryDTO> getAllCars(UserDetails user, Pageable pageable) {
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        return carRepository.findAllByUser(userEntity, pageable)
                .map(CarServiceImpl::mapAsSummary);
    }



    @Override
    public UUID addNewCar(CreateCarDto createCarDto, UserDetails userDetails) throws IOException {
        MultipartFile img = createCarDto.getImageUrl();
        String imageUrl = cloudinaryImageService.uploadImage(img);
        UserEntity user = userService.findByEmail(userDetails.getUsername());
        CarEntity carEntity = modelMapper.map(createCarDto, CarEntity.class);
        carEntity.setUuid(UUID.randomUUID());
        carEntity.setImageUrl(imageUrl);
        carEntity.setUser(user);
        carRepository.save(carEntity);
        return carEntity.getUuid();

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

    @Override
    public CarEntity findByRegistrationNumber(String carByRegistrationNumber) {
        return carRepository.findCarByRegistrationNumber(carByRegistrationNumber).orElse(null);
    }

    @Override
    public CarEntity getCarByUuid(UUID uuid) {
        return carRepository.findByUuid(uuid).orElseThrow(() -> new ObjectNotFoundException(
                "Car with UUID " + uuid + " not found!"
        ));
    }

    @Override
    public List<CarEntity> getCarsByUser(UserEntity userEntity) {
        return carRepository.findAllByUser(userEntity).stream()
                .collect(Collectors.toList());



    }

    private static GarageSummaryDTO mapAsSummary(CarEntity carEntity) {
        return new GarageSummaryDTO(
                carEntity.getUuid().toString(),
                carEntity.getMake(),
                carEntity.getModel(),
                carEntity.getYear(),
                carEntity.getKilometers(),
                carEntity.getImageUrl()
        );
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
                carEntity.getImageUrl(),
                carEntity.getVehicleType(),
                carEntity.getEngineType()
        );
    }
}
