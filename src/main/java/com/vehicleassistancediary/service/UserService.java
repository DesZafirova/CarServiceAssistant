package com.vehicleassistancediary.service;

import com.vehicleassistancediary.model.binding.RegisterCarServiceBindingModel;
import com.vehicleassistancediary.model.binding.RegisterUserBindingModel;
import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.UserRoleEntity;
import com.vehicleassistancediary.model.entity.enums.UserRoleEnum;
import com.vehicleassistancediary.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void registerUser(RegisterUserBindingModel registerUserBindingModel);

    UserEntity findByEmail(String userEmail);

    void registerService(RegisterCarServiceBindingModel registerCarServiceBindingModel);

    List<UserEntity> findAllServices();

    UserEntity findService(String service);
}
