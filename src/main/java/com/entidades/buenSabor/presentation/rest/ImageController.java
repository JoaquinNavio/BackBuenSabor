package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.service.ImageService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;




import java.util.List;
import java.util.Map;
import java.util.UUID;


@RequestMapping("/images")
@RestController
@CrossOrigin("*")
public class ImageController {


    @Autowired
    private ImageService imageService;

    @PostMapping("/uploads")
    public ResponseEntity<String> uploadImages(
            @RequestParam(value = "uploads", required = true) MultipartFile[] files) {
        try {
            return imageService.uploadImages(files);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("/deleteImg")
    public ResponseEntity<String> deleteById(
            @RequestParam(value = "publicId", required = true) String publicId,
            @RequestParam(value = "id", required = true) Long id) {
        try {
            return imageService.deleteImage(publicId, id);
        } catch (IllegalArgumentException e) {
            // UUID.fromString lanzará una IllegalArgumentException si la cadena no es un UUID válido
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid UUID format");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred");
        }
    }
    //TRAE TODAS LAS IMAGENES EN LA TABLA IMAGENES
    @GetMapping("/getImages")
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        try {
            return  imageService.getAllImages();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    };

}

