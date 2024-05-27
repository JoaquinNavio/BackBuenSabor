package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Map;


public interface ImageService extends BaseService<Image, Long> {


    ResponseEntity<List<Map<String, Object>>> getAllImages();
    ResponseEntity<String> uploadImages(MultipartFile[] files);
    ResponseEntity<String> deleteImage(String publicId, Long id);

}

