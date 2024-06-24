package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioCreateDto;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioDto;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoCreateDto;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoDto;
import com.entidades.buenSabor.domain.entities.Domicilio;
import com.entidades.buenSabor.domain.entities.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper extends BaseMapper<Empleado, EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto> {

    @Mapping(target = "sucursal.id", source = "sucursal_id")
    Empleado toEntityCreate(EmpleadoCreateDto source);

    @Mapping(target = "sucursal_id", source = "sucursal.id")
    EmpleadoDto toDTO(Empleado entity);

    @Override
    Empleado toEntity(EmpleadoDto dto);

    @Override
    List<EmpleadoDto> toDTOsList(List<Empleado> entities);

    @Mapping(target = "localidad.id", source = "idLocalidad")
    Domicilio toDomicilio(DomicilioCreateDto domicilioCreateDto);

    List<Domicilio> toDomicilios(List<DomicilioCreateDto> domicilioCreateDtos);
    List<DomicilioDto> toDomicilioDtos(List<Domicilio> domicilios);
}
