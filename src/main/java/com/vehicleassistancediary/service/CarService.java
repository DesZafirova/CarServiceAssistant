package com.vehicleassistancediary.service;

import com.vehicleassistancediary.model.entity.CarEntity;
import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CreateCarDto;
import com.vehicleassistancediary.model.entity.dto.GarageSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public interface CarService {


    Page<GarageSummaryDTO> getAllCars(UserDetails user, Pageable pageable);

    UUID addNewCar(CreateCarDto createCarDto,  UserDetails userDetails) throws IOException;

    Optional<CarDetailsDto> getCarDetail(UUID carUuid);


    void deleteCar(UUID uuid);

    CarEntity findByRegistrationNumber(String carByRegistrationNumber);
}
