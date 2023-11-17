package com.vehicleassistancediary.service.schedulers;

import com.vehicleassistancediary.service.UserActivationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ActivationCleanUpScheduler {
    private final UserActivationService userActivationService;

    public ActivationCleanUpScheduler(UserActivationService userActivationService) {
        this.userActivationService = userActivationService;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void cleanUp(){
        userActivationService.cleanUpObsoleteActivationLinks();
    }

}
