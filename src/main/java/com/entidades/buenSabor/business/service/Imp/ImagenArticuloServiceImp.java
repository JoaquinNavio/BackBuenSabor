package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.business.service.ImagenArticuloService;
import com.entidades.buenSabor.domain.entities.Articulo;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.repositories.ArticuloRepository;
import com.entidades.buenSabor.repositories.ImagenArticuloRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ImagenArticuloServiceImp extends BaseServiceImp<ImagenArticulo,Long> implements ImagenArticuloService {


    private static final Logger log = LoggerFactory.getLogger(ImageServiceImp.class);
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ImagenArticuloRepository imagenArticuloRepository;
    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public ResponseEntity<List<Map<String, Object>>> getAllImages() {
        try {
            List<ImagenArticulo> images = imagenArticuloRepository.findAll();
            List<Map<String, Object>> imagenArticuloList = new ArrayList<>();


            for (ImagenArticulo image : images) {
                Map<String, Object> imageMap = new HashMap<>();
                imageMap.put("id", image.getId());
                imageMap.put("name", image.getName());
                imageMap.put("url", image.getUrl());
                imagenArticuloList.add(imageMap);
            }


            return ResponseEntity.ok(imagenArticuloList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


    @Override
    public List<ImagenArticulo> vincularImagenesArticulo(MultipartFile[] files, Long id) {
        List<ImagenArticulo> imagenesArticulo = new ArrayList<>();

        try {
            for (MultipartFile file : files) {

                /*
                if (file.getName().isEmpty()) {

                    return ResponseEntity.badRequest().build();
                }
                */
                ImagenArticulo imagenArticulo = new ImagenArticulo();
                imagenArticulo.setName(file.getOriginalFilename());
                imagenArticulo.setUrl(cloudinaryService.uploadFile(file));
                ArticuloManufacturado articuloManufacturado = new ArticuloManufacturado();
                articuloManufacturado.setId(id);
                imagenArticulo.setArticulo(articuloManufacturado);
                /*
                if (imagenArticulo.getUrl() == null) {
                    return ResponseEntity.badRequest().build();
                }
                */

                // Agregar la URL a la lista de URLs
                imagenesArticulo.add(imagenArticuloRepository.save(imagenArticulo));
            };
            return imagenesArticulo;

            // Convertir la lista de ids a un array de strings y devolver como JSON
            /*
            return new ResponseEntity<>("{\"status\":\"OK\", \"ids\":" + ids + "}", HttpStatus.OK);
            */

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar las imagenes de articulos");
            /*
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
             */
        }

    }
    @Override
    public ResponseEntity<String> deleteImage(String publicId, Long idBd) {
        try {
            imagenArticuloRepository.deleteById(idBd);
            return cloudinaryService.deleteImage(publicId, idBd);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
    }
}
