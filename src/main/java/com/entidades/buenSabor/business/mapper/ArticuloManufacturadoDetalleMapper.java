package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.ArticuloInsumoService;
import com.entidades.buenSabor.business.service.ArticuloManufacturadoService;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArticuloManufacturadoService.class,ArticuloInsumoService.class})
public interface ArticuloManufacturadoDetalleMapper extends BaseMapper<ArticuloManufacturadoDetalle, ArticuloManufacturadoDetalleDto,ArticuloManufacturadoDetalleCreateDto,ArticuloManufacturadoDetalleCreateDto>{

    @Mapping(target = "articuloInsumo", source = "idArticuloInsumo", qualifiedByName = "getById")
    //@Mapping(target = "articuloManufacturado", source = "idArticuloManufacturado", qualifiedByName = "getById")
    ArticuloManufacturadoDetalle toEntityCreate(ArticuloManufacturadoDetalleCreateDto source);
}
