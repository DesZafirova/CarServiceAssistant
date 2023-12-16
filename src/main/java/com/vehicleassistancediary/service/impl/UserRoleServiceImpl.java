package com.vehicleassistancediary.service.impl;

import com.vehicleassistancediary.model.entity.UserRoleEntity;
import com.vehicleassistancediary.model.entity.enums.UserRoleEnum;
import com.vehicleassistancediary.repository.UserRoleRepository;
import com.vehicleassistancediary.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRoleEntity findByUserRoleEnum(UserRoleEnum role) {
        return userRoleRepository.findByRole(role).orElse(null);
    }
}
