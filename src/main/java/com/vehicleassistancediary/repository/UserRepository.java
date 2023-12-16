package com.vehicleassistancediary.repository;

import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.UserRoleEntity;
import com.vehicleassistancediary.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);


    @Query("SELECT u FROM UserEntity u JOIN u.roles r WHERE r.role = 'SERVICE' and r.role != 'ADMIN'")
    List<UserEntity> findAllByRoles();


    Optional<UserEntity> findUserEntityByName(String name);
}
