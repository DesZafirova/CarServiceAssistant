package com.vehicleassistancediary.service;

import com.vehicleassistancediary.model.binding.RegisterCarServiceBindingModel;
import com.vehicleassistancediary.model.entity.ServiceEntity;

import java.util.List;

public interface ServiceService {
    void registerService(RegisterCarServiceBindingModel registerCarServiceBindingModel);

    List<ServiceEntity> findAllServices();
}
