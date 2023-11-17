package com.vehicleassistancediary.service.impl;
import com.vehicleassistancediary.model.entity.CarRepair;
import com.vehicleassistancediary.model.entity.dto.CarRepairSummaryDto;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import com.vehicleassistancediary.repository.CarRepairRepository;
import com.vehicleassistancediary.service.CarRepairService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarRepairServiceImpl implements CarRepairService {

    private final CarRepairRepository carRepairRepository;
    private final ModelMapper modelMapper;


    public CarRepairServiceImpl(CarRepairRepository carRepairRepository, ModelMapper modelMapper) {
 this.carRepairRepository = carRepairRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CarRepairSummaryDto> findByRepairEnum(CarRepairEnum carRepairEnum) {
        return carRepairRepository.findByRepair(carRepairEnum)
                .stream()
                .map(carRepair ->  modelMapper.map(carRepair, CarRepairSummaryDto.class)

                )
                .collect(Collectors.toList());
    }
//    public static CarRepairSummaryDto mapAsSummary(CarRepair carRepair){
//        return new CarRepairSummaryDto().ge
//    }


}

