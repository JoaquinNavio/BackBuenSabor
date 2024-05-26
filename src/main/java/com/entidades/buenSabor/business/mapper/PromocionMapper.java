package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.Promocion.PromocionCreateDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDto;
import com.entidades.buenSabor.domain.entities.Promocion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PromocionMapper extends BaseMapper<Promocion, PromocionDto, PromocionCreateDto, PromocionCreateDto> {
    @Override
    Promocion toEntityCreate(PromocionCreateDto dto);

    @Override
    PromocionDto toDTO(Promocion entity);

    @Override
    Promocion toUpdate(@MappingTarget Promocion entity, PromocionCreateDto dto);
}
