package com.vehicleassistancediary.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;




@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String address;
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
    private String password;
    private boolean active;


    public Long getId() {
        return id;
    }

    public BaseEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BaseEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BaseEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BaseEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public BaseEntity setActive(boolean active) {
        this.active = active;
        return this;
    }
}
