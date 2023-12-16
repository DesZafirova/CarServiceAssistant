package com.vehicleassistancediary.service.impl;

import com.vehicleassistancediary.config.ReCaptchaConfig;
import com.vehicleassistancediary.model.entity.dto.ReCaptchaResponseDto;
import com.vehicleassistancediary.service.ReCaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class ReCaptchaServiceImpl implements ReCaptchaService {
private final WebClient webClient;
    private final ReCaptchaConfig reCaptchaConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReCaptchaServiceImpl.class);
    public ReCaptchaServiceImpl(WebClient webClient, ReCaptchaConfig reCaptchaConfig) {
        this.webClient = webClient;
        this.reCaptchaConfig = reCaptchaConfig;
    }

    @Override
    public Optional<ReCaptchaResponseDto> verify(String token) {
       return Optional.ofNullable(webClient
                .post()
                .uri(this::buildRecaptchaURI)
                       .body(BodyInserters
                               .fromFormData("secret", reCaptchaConfig.getSite())
                               .with("response", token))
                .retrieve()
                .bodyToMono(ReCaptchaResponseDto.class)
                .doOnError(t -> LOGGER.error("Error fetching google response...", t))
                .onErrorComplete()
                .block());
    }
    private URI buildRecaptchaURI(UriBuilder uriBuilder){
        return uriBuilder
                .scheme("https")
                .host("www.google.com")
                .path("recaptcha/api/siteverify")
                .build();
    }
}
