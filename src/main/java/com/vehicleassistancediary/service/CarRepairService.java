package com.vehicleassistancediary.service;

import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.dto.AddCarRepairDto;
import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CarRepairDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CarRepairSummaryDto;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface CarRepairService {

    List<CarRepairDetailsDto> findByRepairEnum(CarRepairEnum carRepairEnum, UUID uuid);

    CarRepairSummaryDto findLastRepair(UUID uuid);

    List<CarRepairDetailsDto> findByCarUuid(UUID uuid);




    UUID saveNewRepair(AddCarRepairDto addCarRepairDto, UserDetails user);

//    void addNewRepair(UserEntity userEntity, CarDetailsDto carDetailsDto, List<UserEntity> allServices);


//    Optional<CarRepairSummaryDto> getCarRepairSummary(UUID uuid);

}
