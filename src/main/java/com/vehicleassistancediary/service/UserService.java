package com.vehicleassistancediary.service;

import com.vehicleassistancediary.model.binding.RegisterUserBindingModel;
import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.service.UserServiceModel;

public interface UserService {
    void registerUser(RegisterUserBindingModel registerUserBindingModel);

    UserEntity findByEmail(String userEmail);

}
