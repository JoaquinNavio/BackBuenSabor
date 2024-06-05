package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.Insumo.ImagenArticuloCreateDto;
import com.entidades.buenSabor.domain.dto.Insumo.ImagenArticuloDto;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagenArticuloMapper extends BaseMapper<ImagenArticulo, ImagenArticuloDto, ImagenArticuloCreateDto, ImagenArticuloCreateDto>{

}
