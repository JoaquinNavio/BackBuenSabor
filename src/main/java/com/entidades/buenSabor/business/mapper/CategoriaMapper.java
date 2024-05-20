package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.CategoriaService;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaCreateDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.entities.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring"/*, uses = {CategoriaService.class}*/)
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaDto, CategoriaCreateDto,CategoriaCreateDto>{

    /*@Mapping(target = "subcategoria", source = "idCategoriaPadre",qualifiedByName = "getById")
    Categoria toEntityCreate(CategoriaCreateDto source);*/
}
