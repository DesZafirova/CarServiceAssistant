package com.vehicleassistancediary.model.entity;

import com.vehicleassistancediary.model.entity.enums.UserRoleEnum;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
public class ServiceEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(columnDefinition = "TEXT")
    private String address;
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
    private String password;
    @OneToMany(mappedBy = "service")
    private Set<CarRepair> carRepair;

    private UserRoleEnum role;


}
