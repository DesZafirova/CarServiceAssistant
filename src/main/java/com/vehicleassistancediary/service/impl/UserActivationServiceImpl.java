package com.vehicleassistancediary.service.impl;

import com.vehicleassistancediary.model.entity.UserActivationCodeEntity;
import com.vehicleassistancediary.model.event.UserRegisteredEvent;
import com.vehicleassistancediary.repository.UserActivationCodeRepository;
import com.vehicleassistancediary.service.EmailService;
import com.vehicleassistancediary.service.UserActivationService;
import com.vehicleassistancediary.service.UserService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

@Service
public class UserActivationServiceImpl implements UserActivationService {
    private static final String ACTIVATION_CODE_SYMBOLS = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
    private static final int ACTIVATION_CODE_LENGTH = 21;
    private final EmailService emailService;
    private final UserService userService;
    private final UserActivationCodeRepository userActivationCodeRepository;

    public UserActivationServiceImpl(EmailService emailService, UserService userService, UserActivationCodeRepository userActivationCodeRepository) {
        this.emailService = emailService;
        this.userService = userService;
        this.userActivationCodeRepository = userActivationCodeRepository;
    }

    @EventListener(UserRegisteredEvent.class)
    @Override
    public void userRegistered(UserRegisteredEvent userRegisteredEvent) {
        String activationCode = createActivationCode(userRegisteredEvent.getUserEmail());
        emailService.sendRegistrationEmail(userRegisteredEvent.getUserEmail(), userRegisteredEvent.getUserNames(), activationCode);
    }

    @Override
    public void cleanUpObsoleteActivationLinks() {
        //todo
    }

    @Override
    public String createActivationCode(String userEmail) {
        String activationCode = generateActivationCode();

        UserActivationCodeEntity userActivationCodeEntity = new UserActivationCodeEntity();
        userActivationCodeEntity.setActivationCode(activationCode);
        userActivationCodeEntity.setCreated(Instant.now());
        userActivationCodeEntity.setUser(userService.findByEmail(userEmail));
        userActivationCodeRepository.save(userActivationCodeEntity);
        return activationCode;
    }

    private static String generateActivationCode() {
        StringBuilder activationCode = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < ACTIVATION_CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(ACTIVATION_CODE_SYMBOLS.length());
            activationCode.append(ACTIVATION_CODE_SYMBOLS.charAt(randomIndex));
        }
        return activationCode.toString();
    }


}
