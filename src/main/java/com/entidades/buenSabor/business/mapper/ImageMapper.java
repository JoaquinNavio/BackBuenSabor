package com.entidades.buenSabor.business.mapper;


import com.entidades.buenSabor.business.mapper.Base.BaseMapper;
import com.entidades.buenSabor.domain.dto.Image.ImageCreateDto;
import com.entidades.buenSabor.domain.dto.Image.ImageDto;
import com.entidades.buenSabor.domain.entities.Image;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ImageMapper extends BaseMapper<Image, ImageDto, ImageCreateDto,ImageCreateDto> {

}
