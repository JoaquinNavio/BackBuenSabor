package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.mapper.Base.BaseMapper;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDetalleCreateDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDetalleDto;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import org.mapstruct.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromocionDetalleMapper extends BaseMapper<PromocionDetalle, PromocionDetalleDto, PromocionDetalleCreateDto, PromocionDetalleCreateDto> {
    PromocionDetalleDto toDto(PromocionDetalle entity);
    @Named("toPromocionDetalleDto")
    PromocionDetalleDto toDTO(PromocionDetalle source);
}
