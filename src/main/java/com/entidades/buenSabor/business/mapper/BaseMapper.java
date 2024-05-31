package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.entities.Base;
import org.mapstruct.MappingTarget;

import java.util.List;
/*¿Para qué sirven los mappers?
* Automatización de Conversiones:
Facilitan la conversión entre entidades y DTOs, evitando el código repetitivo y propenso a errores.
* Mantenimiento Simplificado:
Al centralizar la lógica de mapeo,
cualquier cambio en el proceso de conversión solo necesita actualizarse en un lugar.
* Claridad y Separación de Preocupaciones:
Mantienen la lógica de negocio y la lógica de presentación separadas, mejorando la claridad del código.*/


//BaseMapper: Proveer una interfaz genérica para operaciones de mapeo básicas.
public interface BaseMapper<E extends Base,D extends BaseDto, DC, DE>{
    //toDTO(E source): Convierte una entidad en un DTO.
    public D toDTO(E source);

    //toEntity(D source): Convierte un DTO en una entidad.
    public E toEntity(D source);

    //toEntityCreate(DC source): Convierte un DTO de creación(createDto) en una entidad.
    public E toEntityCreate(DC source);


    //toUpdate(@MappingTarget E entity, DE source): Actualiza una entidad con datos de un DTO.
    //@MappingTarget se utiliza para reemplazar los atributos del dto sobre la entidad
    public E toUpdate(@MappingTarget E entity, DE source);

    //toDTOsList(List<E> source): Convierte una lista de entidades en una lista de DTOs.
    public List<D> toDTOsList(List<E> source);
}
