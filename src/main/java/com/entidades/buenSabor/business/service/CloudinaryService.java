package com.entidades.buenSabor.business.service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


import java.util.UUID;

public interface CloudinaryService {
    public default String uploadFile(MultipartFile file) {
        return null;
    }
    public ResponseEntity<String> deleteImage(String publicId, Long id);
}
