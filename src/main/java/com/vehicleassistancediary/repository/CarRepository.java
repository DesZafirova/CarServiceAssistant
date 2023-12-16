package com.vehicleassistancediary.repository;

import com.vehicleassistancediary.model.entity.CarEntity;
import com.vehicleassistancediary.model.entity.UserEntity;
import com.vehicleassistancediary.model.entity.dto.GarageSummaryDTO;
import com.vehicleassistancediary.model.entity.enums.EngineTypeEnum;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    Optional<CarEntity> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    Optional<CarEntity> findCarByRegistrationNumber(String carByRegistrationNumber);

    Page<CarEntity> findAllByUserOrderByMake(UserEntity user, Pageable pageable);

    Page<CarEntity> findAllByUser(UserEntity user, Pageable pageable);

    List<CarEntity> findAllByUser(UserEntity userEntity);
}
