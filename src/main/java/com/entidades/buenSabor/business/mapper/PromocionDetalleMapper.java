package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.Promocion.PromocionDetalleCreateDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDetalleDto;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PromocionDetalleMapper extends BaseMapper<PromocionDetalle, PromocionDetalleDto, PromocionDetalleCreateDto, PromocionDetalleCreateDto> {


    @Mapping(target = "articuloId", source = "articulo.id")
    @Mapping(target = "promocionId", source = "promocion.id")
    @Mapping(target = "detalle", source = "cantidad") // Mapear 'cantidad' a 'detalle'
    PromocionDetalleDto toDto(PromocionDetalle entity);

    @Named("toPromocionDetalleDto")
    @Mapping(target = "articuloId", source = "articulo.id")
    @Mapping(target = "promocionId", source = "promocion.id")
    @Mapping(target = "detalle", source = "cantidad") // Mapear 'cantidad' a 'detalle'
    PromocionDetalleDto toDTO(PromocionDetalle source);
}
