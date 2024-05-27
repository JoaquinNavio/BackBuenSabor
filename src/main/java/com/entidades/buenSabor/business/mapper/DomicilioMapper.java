package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.mapper.Base.BaseMapper;
import com.entidades.buenSabor.business.service.LocalidadService;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioCreateDto;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioDto;
import com.entidades.buenSabor.domain.entities.Domicilio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {LocalidadService.class})
public interface DomicilioMapper extends BaseMapper<Domicilio, DomicilioDto, DomicilioCreateDto,DomicilioCreateDto> {
    @Mapping(target = "localidad", source = "idLocalidad",qualifiedByName = "getById")
    Domicilio toEntityCreate(DomicilioCreateDto source);
}
