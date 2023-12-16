package com.vehicleassistancediary.model.entity.dto;

import com.vehicleassistancediary.model.entity.CarEntity;
import com.vehicleassistancediary.model.entity.UserEntity;

import com.vehicleassistancediary.model.entity.enums.CarRepairEnum;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;



public class AddCarRepairDto {
   private String id;


   private UserEntity user;
   private CarEntity car;
   private CarRepairEnum repair;
   @DateTimeFormat(pattern = "mm/dd/yyyy")
   private LocalDate repairDate;
   private UserEntity service;
   private String description;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public UserEntity getUser() {
      return user;
   }

   public void setUser(UserEntity user) {
      this.user = user;
   }

   public CarEntity getCar() {
      return car;
   }

   public void setCar(CarEntity car) {
      this.car = car;
   }

   public CarRepairEnum getRepair() {
      return repair;
   }

   public void setRepair(CarRepairEnum repair) {
      this.repair = repair;
   }

   public LocalDate getRepairDate() {
      return repairDate;
   }

   public void setRepairDate(LocalDate repairDate) {
      this.repairDate = repairDate;
   }

   public UserEntity getService() {
      return service;
   }

   public void setService(UserEntity service) {
      this.service = service;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
