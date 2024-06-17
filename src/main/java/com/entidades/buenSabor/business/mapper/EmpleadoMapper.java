package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoCreateDto;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoDto;
import com.entidades.buenSabor.domain.entities.Empleado;
import com.entidades.buenSabor.domain.entities.ImagenPersona;
import com.entidades.buenSabor.domain.entities.Sucursal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Mapper(componentModel = "spring")
public interface EmpleadoMapper extends BaseMapper<Empleado, EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto> {

    @Mapping(target = "sucursal.id", source = "sucursal_id")
    Empleado toEntityCreate(EmpleadoCreateDto source);

    @Override
    EmpleadoDto toDTO(Empleado entity);

    @Override
    Empleado toEntity(EmpleadoDto dto);

    @Override
    List<EmpleadoDto> toDTOsList(List<Empleado> entities);
}