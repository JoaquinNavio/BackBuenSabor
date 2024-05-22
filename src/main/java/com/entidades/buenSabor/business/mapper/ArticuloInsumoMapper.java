package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.CategoriaService;
import com.entidades.buenSabor.business.service.UnidadMedidaService;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CategoriaService.class, UnidadMedidaService.class})
public interface ArticuloInsumoMapper extends BaseMapper<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoCreateDto> {
    @Mapping(target = "unidadMedida", source = "idUnidadMedida", qualifiedByName = "getById")
    @Mapping(target = "categoria", source = "idCategoria", qualifiedByName = "getById")
    ArticuloInsumo toEntityCreate(ArticuloInsumoCreateDto source);

    @Override
    @Mapping(target = "unidadMedida", source = "idUnidadMedida", qualifiedByName = "getById")
    @Mapping(target = "categoria", source = "idCategoria", qualifiedByName = "getById")
    ArticuloInsumo toUpdate(@MappingTarget ArticuloInsumo entity, ArticuloInsumoCreateDto source);
}

