package com.pintter.businessdomain.artworkservice.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ICloudinaryService {
    String uploadFile(MultipartFile file) throws IOException;
}
