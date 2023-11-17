package com.vehicleassistancediary.service;

import com.vehicleassistancediary.model.entity.CarEntity;
import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CreateCarDto;
import com.vehicleassistancediary.model.entity.dto.GarageSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface CarService {


    Page<GarageSummaryDTO> getAllCars(Pageable pageable);

    UUID addNewCar(CreateCarDto createCarDto);

    Optional<CarDetailsDto> getCarDetail(UUID carUuid);


    void deleteCar(UUID uuid);

}
