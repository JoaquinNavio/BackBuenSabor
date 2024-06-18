package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioCreateDto;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioDto;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoCreateDto;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoDto;
import com.entidades.buenSabor.domain.entities.Domicilio;
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
    @Mapping(target = "domicilios", source = "domicilios")
    Empleado toEntityCreate(EmpleadoCreateDto source);

    @Mapping(target = "sucursal_id", source = "sucursal.id")
    @Mapping(target = "domicilios", source = "domicilios")
    EmpleadoDto toDTO(Empleado entity);

    @Override
    Empleado toEntity(EmpleadoDto dto);

    @Override
    List<EmpleadoDto> toDTOsList(List<Empleado> entities);

    @Mapping(target = "localidad.id", source = "idLocalidad") // Agregar esta línea
    Domicilio toDomicilio(DomicilioCreateDto domicilioCreateDto);

    List<Domicilio> toDomicilios(List<DomicilioCreateDto> domicilioCreateDtos);
    List<DomicilioDto> toDomicilioDtos(List<Domicilio> domicilios);
}