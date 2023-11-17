package com.vehicleassistancediary.repository;

import com.vehicleassistancediary.model.entity.CarRepair;
import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepairRepository extends JpaRepository<CarRepair, Long> {
        @Query("SELECT r from CarRepair r where r.repair = ?1")
    List<CarRepair> findByRepair( CarRepairEnum carRepairEnum);
}
