package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.mapper.ArticuloInsumoMapper;
import com.entidades.buenSabor.business.service.ArticuloInsumoService;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.entities.*;
import com.entidades.buenSabor.repositories.ArticuloInsumoRepository;
import com.entidades.buenSabor.repositories.CategoriaRepository;
import com.entidades.buenSabor.repositories.ImagenArticuloRepository;
import com.entidades.buenSabor.repositories.UnidadMedidaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArticuloInsumoServiceImp extends BaseServiceImp<ArticuloInsumo, Long> implements ArticuloInsumoService {

    @Autowired
    ArticuloInsumoRepository articuloInsumoRepository;

    @Autowired
    ImagenArticuloRepository imagenArticuloRepository;

    @Autowired
    UnidadMedidaRepository unidadMedidaRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ArticuloInsumoMapper articuloInsumoMapper;

    @Autowired
    CloudinaryService cloudinaryService;

    @Transactional
    public ArticuloInsumoDto createCompleto(ArticuloInsumoCreateDto insumoCreateDto){


        //ArticuloInsumo articulo = articuloInsumoMapper.toEntityCreate(insumoCreateDto);

        ArticuloInsumo articulo = new ArticuloInsumo();
        articulo.setDenominacion(insumoCreateDto.getDenominacion());
        articulo.setUnidadMedida(unidadMedidaRepository.getById(insumoCreateDto.getIdUnidadMedida()));
        articulo.setCategoria(categoriaRepository.getById(insumoCreateDto.getIdCategoria()));
        articulo.setStockMaximo(insumoCreateDto.getStockMaximo());
        articulo.setStockActual(insumoCreateDto.getStockActual());
        articulo.setPrecioCompra(insumoCreateDto.getPrecioCompra());
        articulo.setPrecioVenta(insumoCreateDto.getPrecioVenta());
        articulo.setEsParaElaborar(insumoCreateDto.getEsParaElaborar());

        ArticuloInsumo articuloSaved = articuloInsumoRepository.save(articulo);

        Set<ImagenArticulo> imagenesArticulo = new HashSet<>();
        try {
            for (MultipartFile file : insumoCreateDto.getFiles()) {

                ImagenArticulo imagenArticulo = new ImagenArticulo();
                imagenArticulo.setName(file.getOriginalFilename());
                imagenArticulo.setUrl(cloudinaryService.uploadFile(file));
                imagenArticulo.setArticulo(articuloSaved);

                // Agregar la URL a la lista de URLs
                imagenesArticulo.add(imagenArticuloRepository.save(imagenArticulo));
            };
            articuloSaved.setImagenes(imagenesArticulo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar las imagenes de articulos");
        }

        /*
        Set<ImagenArticulo> imagenes = insumoCreateDto.getImages().stream().map(imagenDto -> {
            ImagenArticulo imagen = new ImagenArticulo();
            imagen.setUrl(imagenDto.getUrl());
            imagen.setArticulo(articuloSaved);
            return imagen;
        }).collect(Collectors.toSet());

        Set<ImagenArticulo> imagenesSet = new HashSet<>(imagenArticuloRepository.saveAll(imagenes));
        articuloSaved.setImagenes(imagenesSet);
         */

        ArticuloInsumoDto articuloDto = articuloInsumoMapper.toDTO(articuloSaved);
        System.out.println("insumoDto");
        System.out.println(articuloDto.toString());

        return articuloDto;
    }
}
