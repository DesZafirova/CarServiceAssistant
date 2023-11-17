package com.vehicleassistancediary.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
}
