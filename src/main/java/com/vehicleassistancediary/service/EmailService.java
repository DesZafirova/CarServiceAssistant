package com.vehicleassistancediary.service;

public interface EmailService {
    void sendRegistrationEmail(String userEmail, String userNames, String activationCode);
 }
