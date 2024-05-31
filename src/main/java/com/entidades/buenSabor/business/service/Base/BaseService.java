package com.entidades.buenSabor.business.service.Base;

import com.entidades.buenSabor.domain.entities.Base;
import org.mapstruct.Named;

import java.io.Serializable;
import java.util.List;

/*El servicio proporciona una capa de abstracción entre los controladores y los repositorios,
encapsulando la lógica empresarial y proporcionando métodos para interactuar con las entidades en la base de datos.*/


/*BaseService:
Define las operaciones comunes que deben realizar todos los servicios,
como crear, obtener, actualizar y eliminar entidades.*/
public interface BaseService <E extends Base, ID extends Serializable>{
    /*create(E request):
    Este método crea una nueva entidad en la base de datos utilizando los datos proporcionados en el objeto request.*/
    public E create(E request);

    //getAll(): Retorna una lista de todas las entidades presentes en la base de datos.
    public List<E> getAll();

    //deleteById(ID id): Elimina lógicamente una entidad de la base de datos basándose en su ID.
    public void deleteById(ID id);

    /*update(E request, ID id):
    Actualiza una entidad existente en la base de datos con los datos proporcionados en el objeto request.*/
    public E update(E request, ID id);

    //getById(ID id): Obtiene una entidad de la base de datos basándose en su ID
    @Named("getById")// Esta notacion califica al metodo para luego se utilizado en clase mappper
    public E getById(ID id);
}
