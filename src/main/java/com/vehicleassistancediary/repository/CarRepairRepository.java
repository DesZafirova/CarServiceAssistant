package com.vehicleassistancediary.repository;

import com.vehicleassistancediary.model.entity.CarRepair;
import com.vehicleassistancediary.model.entity.dto.CarDetailsDto;
import com.vehicleassistancediary.model.entity.dto.CarRepairSummaryDto;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface CarRepairRepository extends JpaRepository<CarRepair, Long> {
    @Query("SELECT r from CarRepair r where r.repair = ?1 and r.car.uuid = ?2 order by r.repairDate desc")
    List<CarRepair> findByRepair(CarRepairEnum carRepairEnum, UUID uuid);

    @Query("Select r from CarRepair r where r.car.uuid = ?1 order by r.repairDate desc limit 1")
    Optional<CarRepair> findLastRepair(UUID uuidCar);

    List<CarRepair> findByCar_Uuid(@NotNull UUID car_uuid);

//    CarRepair findByCar_UuidAndRepairDateDesc(UUID uuid);

//    Optional<CarRepair> findByCar_IdAndCarRepairEnum(Long car_id, CarRepairEnum carRepairEnum);
}
