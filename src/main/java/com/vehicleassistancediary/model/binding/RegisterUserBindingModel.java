package com.vehicleassistancediary.model.binding;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class RegisterUserBindingModel {
    @Length(min = 3, max = 15)
    private String firstName;
    @Length(min = 3, max = 15)

    private String lastName;
    @Email
    private String email;

    @Length(min = 3, max = 15)

    private String password;
    @Length(min = 3, max = 15)
    private String confirmPassword;
    @Length(min = 15)
    private String address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public String fullName(){
        return firstName + " " + lastName;
    }
}
