package com.vehicleassistancediary.model.binding;

import com.vehicleassistancediary.model.entity.UserRoleEntity;
import com.vehicleassistancediary.model.entity.enums.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class RegisterCarServiceBindingModel {
    @Length(min = 3, max = 15)
    @NotNull
    private String name;
    @Pattern(regexp = "^(((\\+|00)359[- ]?)|(0))([89][- ]?[789]([- ]?\\d){7})$")
    @NotEmpty
    private String phoneNumber;
    @Email
    @NotEmpty
    private String email;
    @NotNull
    @Length(min = 3, max = 15)
    private String password;
    @Length(min = 3, max = 15)
    private String confirmPassword;
    @Length(min = 15)
    private String address;
    private UserRoleEnum role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
}
