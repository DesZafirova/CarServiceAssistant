package com.vehicleassistancediary.service;

import com.vehicleassistancediary.model.entity.UserRoleEntity;
import com.vehicleassistancediary.model.entity.enums.UserRoleEnum;

public interface UserRoleService {
    UserRoleEntity findByUserRoleEnum(UserRoleEnum role);
}
