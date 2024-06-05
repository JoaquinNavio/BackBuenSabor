package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.mapper.ArticuloInsumoMapper;
import com.entidades.buenSabor.business.service.ArticuloInsumoService;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import com.entidades.buenSabor.domain.entities.Image;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.repositories.ArticuloInsumoRepository;
import com.entidades.buenSabor.repositories.CategoriaRepository;
import com.entidades.buenSabor.repositories.ImagenArticuloRepository;
import com.entidades.buenSabor.repositories.UnidadMedidaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Transactional
    public ArticuloInsumoDto createCompleto(ArticuloInsumoCreateDto insumoCreateDto){
        ArticuloInsumo articulo = articuloInsumoMapper.toEntityCreate(insumoCreateDto);

        ArticuloInsumo articuloSaved = articuloInsumoRepository.save(articulo);


        Set<ImagenArticulo> imagenes = insumoCreateDto.getImages().stream().map(imagenDto -> {
            ImagenArticulo imagen = new ImagenArticulo();
            imagen.setUrl(imagenDto.getUrl());
            imagen.setArticulo(articuloSaved);
            return imagen;
        }).collect(Collectors.toSet());

        Set<ImagenArticulo> imagenesSet = new HashSet<>(imagenArticuloRepository.saveAll(imagenes));
        articuloSaved.setImagenes(imagenesSet);


        ArticuloInsumoDto articuloDto = articuloInsumoMapper.toDTO(articuloSaved);
        System.out.println("insumoDto");
        System.out.println(articuloDto.toString());

        return articuloDto;
    }
}
