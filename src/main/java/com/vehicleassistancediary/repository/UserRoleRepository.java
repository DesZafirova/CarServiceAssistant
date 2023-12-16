package com.vehicleassistancediary.repository;

import com.vehicleassistancediary.model.entity.UserRoleEntity;
import com.vehicleassistancediary.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findByRole(UserRoleEnum role);
}
