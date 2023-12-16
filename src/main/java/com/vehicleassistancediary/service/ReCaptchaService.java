package com.vehicleassistancediary.service;

import com.vehicleassistancediary.model.entity.dto.ReCaptchaResponseDto;

import java.util.Optional;

public interface ReCaptchaService {
     Optional<ReCaptchaResponseDto> verify(String token);
}
