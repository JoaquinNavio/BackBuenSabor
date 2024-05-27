package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.ArticuloManufacturadoDetalleService;
import com.entidades.buenSabor.business.service.CategoriaService;
import com.entidades.buenSabor.business.service.ImageService;
import com.entidades.buenSabor.business.service.UnidadMedidaService;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {CategoriaService.class,UnidadMedidaService.class, ImageService.class})
public interface ArticuloManufacturadoMapper extends BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto,ArticuloManufacturadoCreateDto> {
    @Mapping(target = "unidadMedida", source = "idUnidadMedida",qualifiedByName = "getById")
    @Mapping(target = "categoria", source = "idCategoria",qualifiedByName = "getById")
    @Mapping(target = "image", source = "idImage", qualifiedByName = "getById")
    ArticuloManufacturado toEntityCreate(ArticuloManufacturadoCreateDto source);

    @Override
    @Mapping(target = "unidadMedida", source = "idUnidadMedida", qualifiedByName = "getById")
    @Mapping(target = "categoria", source = "idCategoria", qualifiedByName = "getById")
    @Mapping(target = "image", source = "idImage", qualifiedByName = "getById")
    ArticuloManufacturado toUpdate(@MappingTarget ArticuloManufacturado entity, ArticuloManufacturadoCreateDto source);

}
