package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ImagenArticuloService extends BaseService<ImagenArticulo, Long> {


    ResponseEntity<List<Map<String, Object>>> getAllImages();
    List<ImagenArticulo> vincularImagenesArticulo(MultipartFile[] files, Long id);
    ResponseEntity<String> deleteImage(String publicId, Long id);
}
