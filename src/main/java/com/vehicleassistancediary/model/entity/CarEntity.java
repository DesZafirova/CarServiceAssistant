package com.vehicleassistancediary.model.entity;


import com.vehicleassistancediary.model.entity.enums.EngineTypeEnum;
import com.vehicleassistancediary.model.entity.enums.VehicleTypeEnum;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;


import java.sql.Types;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class CarEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @NotNull
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @Column(nullable = false)
    private String make;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Integer year;
    @Column(nullable = false, unique = true)
    private String registrationNumber;
    @Column(nullable = false, unique = true)
    private String vin;
    @Column(nullable = false)
    private Integer kilometers;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleTypeEnum vehicleType;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EngineTypeEnum engineType;
    @ManyToOne
    private UserEntity user;

    @NotNull
    private String imageUrl;



}
