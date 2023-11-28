package com.vehicleassistancediary.service;

import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CarRepairDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CarRepairSummaryDto;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarRepairService {

    List<CarRepairDetailsDto> findByRepairEnum(CarRepairEnum carRepairEnum, UUID uuid);

    CarRepairSummaryDto findLastRepair(UUID uuid);

    List<CarRepairDetailsDto> findByCarUuid(UUID uuid);

//    Optional<CarRepairSummaryDto> getCarRepairSummary(UUID uuid);

//    Optional<CarRepairSummaryDto> getCarRepairSummary(UUID uuid);

}
