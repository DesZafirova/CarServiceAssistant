package com.vehicleassistancediary.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryImageService {
    String uploadImage(MultipartFile multipartFile) throws IOException;
}
