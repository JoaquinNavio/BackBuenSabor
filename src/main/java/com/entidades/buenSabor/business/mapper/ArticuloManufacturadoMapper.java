package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.CategoriaService;
import com.entidades.buenSabor.business.service.UnidadMedidaService;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/*@Mapper(componentModel = "spring", uses = {...}):
Define que esta interfaz es un mapper de MapStruct y debe ser administrado por Spring.
Indica los servicios que se utilizarán para mapear campos complejos.*/
@Mapper(componentModel = "spring", uses = {CategoriaService.class,UnidadMedidaService.class, ImagenArticuloMapper.class})
public interface ArticuloManufacturadoMapper extends BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto,ArticuloManufacturadoCreateDto> {

    /*@Mapping(target = "unidadMedida", source = "idUnidadMedida",qualifiedByName = "getById"):
    Se está indicando que el campo unidadMedida del objeto de destino
    se debe asignar utilizando el valor del campo idUnidadMedida del objeto de origen.
    Además, qualifiedByName = "getById" especifica
    que se debe utilizar un método llamado getById para obtener el valor del campo de origen.*/
    @Mapping(target = "unidadMedida", source = "idUnidadMedida",qualifiedByName = "getById")
    @Mapping(target = "categoria", source = "idCategoria",qualifiedByName = "getById")
    //@Mapping(target = "image", source = "idImage", qualifiedByName = "getById")
    /*toEntityCreate: Mapea un ArticuloManufacturadoCreateDto a una entidad ArticuloManufacturado.
    Usa servicios (CategoriaService, UnidadMedidaService, ImageService)
    para obtener objetos completos a partir de sus IDs.*/
    ArticuloManufacturado toEntityCreate(ArticuloManufacturadoCreateDto source);

    @Override
    @Mapping(target = "unidadMedida", source = "idUnidadMedida", qualifiedByName = "getById")
    @Mapping(target = "categoria", source = "idCategoria", qualifiedByName = "getById")
    //@Mapping(target = "image", source = "idImage", qualifiedByName = "getById")
    //toUpdate: Similar a toEntityCreate, pero actualiza una entidad existente con los datos de un DTO de creación.
    ArticuloManufacturado toUpdate(@MappingTarget ArticuloManufacturado entity, ArticuloManufacturadoCreateDto source);

}
