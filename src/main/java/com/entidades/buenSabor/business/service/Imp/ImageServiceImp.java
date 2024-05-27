package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.business.service.ImageService;
import com.entidades.buenSabor.domain.entities.Empresa;
import com.entidades.buenSabor.domain.entities.Image;
import com.entidades.buenSabor.repositories.ImageRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;




import java.util.*;


@Service
public class ImageServiceImp extends BaseServiceImp<Image,Long> implements ImageService {


    private static final Logger log = LoggerFactory.getLogger(ImageServiceImp.class);
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ResponseEntity<List<Map<String, Object>>> getAllImages() {
        try {
            List<Image> images = imageRepository.findAll();
            List<Map<String, Object>> imageList = new ArrayList<>();


            for (Image image : images) {
                Map<String, Object> imageMap = new HashMap<>();
                imageMap.put("id", image.getId());
                imageMap.put("name", image.getName());
                imageMap.put("url", image.getUrl());
                imageList.add(imageMap);
            }


            return ResponseEntity.ok(imageList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


    @Override
    public ResponseEntity<String> uploadImages(MultipartFile[] files) {
        List<String> ids = new ArrayList<>();


        try {
            for (MultipartFile file : files) {


                if (file.getName().isEmpty()) {
                    return ResponseEntity.badRequest().build();
                }


                Image image = new Image();
                image.setName(file.getOriginalFilename());
                image.setUrl(cloudinaryService.uploadFile(file));
                if (image.getUrl() == null) {
                    return ResponseEntity.badRequest().build();
                }


                // Agregar la URL a la lista de URLs
                imageRepository.save(image);
                ids.add(image.getId().toString());
            };


            // Convertir la lista de ids a un array de strings y devolver como JSON
            return new ResponseEntity<>("{\"status\":\"OK\", \"ids\":" + ids + "}", HttpStatus.OK);


        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }


    }
    @Override
    public ResponseEntity<String> deleteImage(String publicId, Long idBd) {
        try {
            imageRepository.deleteById(idBd);
            return cloudinaryService.deleteImage(publicId, idBd);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
    }
}

