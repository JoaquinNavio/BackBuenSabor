package com.entidades.buenSabor.business.facade.Base;


import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.entities.Base;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/*BaseFacadeImp:
Esta es la implementación concreta del BaseFacade.
Proporciona la lógica para realizar las operaciones básicas definidas en la interfaz BaseFacade.
En su constructor, recibe instancias de un servicio base (BaseService) y un mapper base (BaseMapper),
que se utilizan para interactuar con la capa de persistencia y convertir entre entidades y DTOs.*/
public abstract class BaseFacadeImp <E extends Base,D extends BaseDto, DC, DE,ID extends Serializable> implements BaseFacade<D, DC, DE,ID> {

    protected BaseService<E,ID> baseService;
    protected BaseMapper<E,D,DC, DE> baseMapper;

    /*Constructor:
    Recibe dos parámetros: un baseService del tipo BaseService y un baseMapper del tipo BaseMapper.
    Estos son necesarios para realizar operaciones de CRUD (Crear, Leer, Actualizar, Eliminar)
    sobre las entidades y mapearlas a DTOs.*/
    public BaseFacadeImp(BaseService<E,ID> baseService, BaseMapper<E,D, DC, DE> baseMapper) {
        this.baseService = baseService;
        this.baseMapper = baseMapper;
    }

    /*createNew(createDto):
    Crea una nueva entidad a partir de un DTO de creación (DC),
    la guarda utilizando el servicio base y devuelve el DTO resultante.*/
    public D createNew(DC request){
        // Convierte a entidad
        System.out.println("INICIANDO CREATE NEW createNew(DC request) FACADE - BaseFacadeImp");
        System.out.println("Convirtiendo a entidad a partir de dto de creacion createDto (DC) con el metodo toEntityCreate(request) del BaseMapper - BaseFacadeImp");
        var entityToCreate = baseMapper.toEntityCreate(request);


        // Crea y guarda la entidad
        System.out.println("Creando y guardando entidad con metodo create(entityToCreate) de BaseService - BaseFacadeImp ");
        var entityCreated = baseService.create(entityToCreate);


        //Convierte entidad a Dto y devuelve
        System.out.println("Convirtiendo entidad a DTO con toDTO(entityCreated) de BaseMapper - BaseFacadeImp");
        return baseMapper.toDTO(entityCreated);
    }

    /*getById(id): Obtiene una entidad por su ID utilizando el servicio base y la convierte en un DTO.*/
    public D getById(ID id){
        // Busca una entidad por id
        System.out.println("Obteniendo entidad por su ID utilizando .getById(id) del BaseService - BaseFacadeImp");
        var entity = baseService.getById(id);

        //Convierte la entidad a DTO y devuelve
        System.out.println("Convirtiendo entidad a DTO con toDTO(entity) de BaseMapper - BaseFacadeImp");
        return baseMapper.toDTO(entity);
    }

    /*getAll():
    Obtiene todas las entidades utilizando el servicio base,
    las convierte en DTOs y las devuelve en forma de lista.*/
    public List<D> getAll(){
        //Trae la lista de entidades
        System.out.println("Obteniendo todas las entidades con getAll() de BaseService - BaseFacadeImp");
        var entities = baseService.getAll();


        //Devuelve una lista de DTO
        /*Itera sobre la lista de entidades,
        utiliza el baseMapper para convertir cada una en un DTO
        y finalmente devuelve una lista de DTOs.*/
        System.out.println("Iterando sobre la lista de entidades y convirtiendo cada entidad en DTO con baseMapper::toDTO para devolve - BaseFacadeImp");
        return entities
                .stream()
                .map(baseMapper::toDTO)
                .collect(Collectors.toList());
    }

    /*deleteById(id): Elimina una entidad por su ID utilizando el servicio base.*/
    public void deleteById(ID id){
        System.out.println("Eliminando entidad con deleteById(id) de BaseService - BaseFacadeImp");
        //var entity = baseService.getById(id);
        baseService.deleteById(id);
    }

    /*update(body,id):
    Actualiza una entidad existente utilizando un DTO de actualización (DE).
    Primero obtiene la entidad existente por su ID,
    la actualiza con los datos del DTO utilizando el mapper
    y luego la actualiza en la base de datos utilizando el servicio base.
    Finalmente, devuelve el DTO actualizado.*/
    public D update(DE request, ID id){
        //Obtiene la entidad existente por su ID utilizando el baseService
        System.out.println("Obteniendo entidad con getById(id) de BaseService - BaseFacadeImp");
        var entityToUpdate = baseService.getById(id);

        //Actualiza los datos del dto
        System.out.println("Actualizando dto con toUpdate(entityToUpdate, request) de BaseMapper - BaseFacadeImp ");
        var entityUpdatedByMapper = baseMapper.toUpdate(entityToUpdate, request);

        //Actualiza la entidad en la base de datos utilizando el baseService
        System.out.println("Actualizando entidad en BD con update(entityUpdatedByMapper, id) de BaseService - BaseFacadeImp");
        var entityUpdatedByService = baseService.update(entityUpdatedByMapper, id);

        //Convierte la entidad a dto y devuelve
        System.out.println("Convirtindo a dto para devolver - BaseFacadeImp");
        return baseMapper.toDTO(entityUpdatedByService);
    }

}
