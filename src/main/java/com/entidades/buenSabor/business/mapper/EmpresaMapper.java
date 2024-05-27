package com.entidades.buenSabor.business.mapper;


import com.entidades.buenSabor.business.mapper.Base.BaseMapper;
import com.entidades.buenSabor.domain.dto.Empresa.EmpresaCreateDto;
import com.entidades.buenSabor.domain.dto.Empresa.EmpresaDto;

import com.entidades.buenSabor.domain.dto.Empresa.EmpresaLargeDto;
import com.entidades.buenSabor.domain.entities.Empresa;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpresaMapper extends BaseMapper<Empresa, EmpresaDto, EmpresaCreateDto,EmpresaCreateDto> {
    EmpresaLargeDto toLargeDto(Empresa empresa);
}
