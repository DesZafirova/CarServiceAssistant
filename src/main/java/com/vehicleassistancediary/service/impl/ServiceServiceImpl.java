package com.vehicleassistancediary.service.impl;

import com.vehicleassistancediary.model.binding.RegisterCarServiceBindingModel;
import com.vehicleassistancediary.model.entity.ServiceEntity;
import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.UserRoleEntity;
import com.vehicleassistancediary.model.entity.enums.UserRoleEnum;
import com.vehicleassistancediary.repository.ServiceRepository;
import com.vehicleassistancediary.service.ServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.serviceRepository = serviceRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerService(RegisterCarServiceBindingModel registerCarServiceBindingModel) {
        ServiceEntity service = modelMapper.map(registerCarServiceBindingModel, ServiceEntity.class);
        service.setPassword(passwordEncoder.encode(registerCarServiceBindingModel.getPassword()));
        //todo set Enum by Admin
        serviceRepository.save(service);
    }
}
