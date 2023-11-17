package com.vehicleassistancediary.service;

import com.vehicleassistancediary.model.event.UserRegisteredEvent;

public interface UserActivationService  {
void userRegistered(UserRegisteredEvent userRegisteredEvent);
void cleanUpObsoleteActivationLinks();
String createActivationCode(String userEmail);
}
