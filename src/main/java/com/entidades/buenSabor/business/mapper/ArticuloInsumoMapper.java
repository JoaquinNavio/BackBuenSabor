package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.CategoriaService;
import com.entidades.buenSabor.business.service.UnidadMedidaService;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.dto.Insumo.ImagenArticuloCreateDto;
import com.entidades.buenSabor.domain.dto.Insumo.ImagenArticuloDto;
import com.entidades.buenSabor.domain.entities.Articulo;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CategoriaService.class, UnidadMedidaService.class})
public interface ArticuloInsumoMapper extends BaseMapper<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoCreateDto> {
    @Mapping(target = "unidadMedida", source = "idUnidadMedida", qualifiedByName = "getById")
    @Mapping(target = "categoria", source = "idCategoria", qualifiedByName = "getById")
    //@Mapping(target = "imagenes", source = "files", qualifiedByName = "mapImages")
    ArticuloInsumo toEntityCreate(ArticuloInsumoCreateDto source);

    @Override
    @Mapping(target = "unidadMedida", source = "idUnidadMedida", qualifiedByName = "getById")
    @Mapping(target = "categoria", source = "idCategoria", qualifiedByName = "getById")
    ArticuloInsumo toUpdate(@MappingTarget ArticuloInsumo entity, ArticuloInsumoCreateDto source);


    /*
    @Named("mapImages")
    default Set<ImagenArticulo> mapImages(Set<ImagenArticuloCreateDto> imageDtos) {
        if (imageDtos == null) {
            return null;
        }
        return imageDtos.stream()
                .map(this::mapImage)
                .collect(Collectors.toSet());
    }

    default ImagenArticulo mapImage(ImagenArticuloCreateDto imageDto) {
        if (imageDto == null) {
            return null;
        }
        ImagenArticulo image = new ImagenArticulo();
        image.setUrl(imageDto.getUrl());
        image.setArticulo(new ArticuloInsumo());
        return image;
    }

     */

    @Override
    @Mapping(target = "unidadMedida", source = "unidadMedida")
    @Mapping(target = "categoria", source = "categoria")
    @Mapping(target = "imagenes", source = "imagenes", qualifiedByName = "mapImagesToDto")
    ArticuloInsumoDto toDTO(ArticuloInsumo entity);


    @Named("mapImagesToDto")
    default Set<ImagenArticuloDto> mapImagesToDto(Set<ImagenArticulo> images) {
        if (images == null) {
            return null;
        }
        return images.stream()
                .map(this::mapImageToDto)
                .collect(Collectors.toSet());
    }

    default ImagenArticuloDto mapImageToDto(ImagenArticulo image) {
        if (image == null) {
            return null;
        }
        ImagenArticuloDto imageDto = new ImagenArticuloDto();
        imageDto.setId(image.getId());
        imageDto.setUrl(image.getUrl());
        // Set idArticulo if needed
        return imageDto;
    }
}

