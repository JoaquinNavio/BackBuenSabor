package com.entidades.buenSabor.business.mapper;



import com.entidades.buenSabor.domain.dto.Categoria.CategoriaCreateDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.entities.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaDto, CategoriaCreateDto, CategoriaCreateDto> {

    @Mapping(source = "sucursal.id", target = "sucursal_id")
    @Mapping(target = "categoriaPadre", ignore = true) // Ignorar categoría padre aquí, lo manejaremos manualmente en el servicio
    CategoriaDto toDTO(Categoria categoria);

    @Mapping(source = "sucursal_id", target = "sucursal.id")
    Categoria toEntityCreate(CategoriaCreateDto dto);
}
