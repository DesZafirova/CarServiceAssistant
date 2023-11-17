package com.vehicleassistancediary.service;

import com.vehicleassistancediary.model.entity.dto.CarRepairSummaryDto;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarRepairService {

    List<CarRepairSummaryDto> findByRepairEnum(CarRepairEnum carRepairEnum);

//    Optional<CarRepairSummaryDto> getCarRepairSummary(UUID uuid);

}
