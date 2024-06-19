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

    @Mapping(source = "idUnidadMedida", target = "unidadMedida", qualifiedByName = "getById")
    @Mapping(source = "idCategoria", target = "categoria", qualifiedByName = "getById")
    @Mapping(source = "sucursal_id", target = "sucursal.id")
    ArticuloInsumo toEntityCreate(ArticuloInsumoCreateDto source);

    @Override
    @Mapping(source = "idUnidadMedida", target = "unidadMedida", qualifiedByName = "getById")
    @Mapping(source = "idCategoria", target = "categoria", qualifiedByName = "getById")
    @Mapping(source = "sucursal_id", target = "sucursal.id")
    ArticuloInsumo toUpdate(@MappingTarget ArticuloInsumo entity, ArticuloInsumoCreateDto source);

    @Override
    @Mapping(source = "unidadMedida", target = "unidadMedida")
    @Mapping(source = "categoria", target = "categoria")
    @Mapping(source = "imagenes", target = "imagenes", qualifiedByName = "mapImagesToDto")
    @Mapping(source = "sucursal.id", target = "sucursal_id")
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
