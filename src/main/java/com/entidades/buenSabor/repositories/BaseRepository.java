package com.entidades.buenSabor.repositories;


import com.entidades.buenSabor.domain.entities.Base;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/*¿Qué es un repositorio?
En el contexto de Spring Data JPA,
un repositorio es un componente que proporciona una abstracción sobre el acceso a la base de datos.
Simplifica las operaciones de CRUD (Crear, Leer, Actualizar y Borrar) y permite definir consultas personalizadas.
Los repositorios son interfaces que extienden de JpaRepository u otras interfaces de Spring Data,
lo que les permite heredar una amplia gama de métodos para interactuar con la base de datos
sin necesidad de implementar esos métodos manualmente.*/

/*@NoRepositoryBean: Indica que esta interfaz no debe ser considerada una implementación de repositorio concreta por Spring.
 Es decir, Spring no intentará instanciar un bean de esta interfaz directamente.
 Se utiliza para definir una interfaz base que otras interfaces de repositorio pueden extender*/
@NoRepositoryBean
public interface BaseRepository <E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {
    Logger logger = LoggerFactory.getLogger(BaseRepository.class);


    /*@Override: Indica que el método está sobrescribiendo un método definido en una clase o interfaz padre.
    En este caso, delete y getById están sobrescribiendo métodos de JpaRepository.*/
    @Override
    /*@Transactional: Indica que el método debe ejecutarse dentro de una transacción.
    Esto significa que todas las operaciones de base de datos dentro del método
    serán tratadas como una única unidad de trabajo, y pueden ser confirmadas (commit) o revertidas (rollback) juntas.
    En el método delete, @Transactional asegura que la operación
    de marcar la entidad como eliminada y guardarla nuevamente ocurra dentro de una transacción.
    Esto garantiza que ambos pasos se ejecuten correctamente o ninguno se ejecute en caso de un error.*/
    @Transactional
    /*delete(E entity): Sobrescribe el método delete de JpaRepository.
    En lugar de eliminar físicamente la entidad de la base de datos,
    marca el campo eliminado como true y guarda la entidad nuevamente.
    Esto implementa el "borrado lógico".*/
    default void delete(E entity) {
        logger.info("EJECUTANDO DELETE SOBREESCRITO delete(E entity) - BaseRepository");
        logger.info("Marca el campo eliminado de la entidad como true - BaseRepository");
        entity.setEliminado(true);
        logger.info("Guarda la entidad - BaseRepository");
        save(entity);
    }

    /*getById(ID id): Sobrescribe el método getById
    para incluir lógica que verifica si la entidad está marcada como eliminada.
    Si la entidad está eliminada lógicamente, lanza una excepción.*/
    @Override
    default E getById(ID id) {
        logger.info("EJECUTANDO GEY BY ID SOBREESCRITO getById(ID id) - BaseRepository");

        /*Utiliza el método findById heredado de JpaRepository para buscar la entidad por su ID.
        Este método devuelve un Optional<E>, que puede contener la entidad si se encuentra,
        o estar vacío si no se encuentra.*/
        /*¿Qué es Optional?
        Un Optional<E> es un contenedor que puede o no contener un valor no nulo de tipo E.
        Los Optional se utilizan para evitar NullPointerException
        y para expresar la ausencia de un valor de manera más clara.*/
        var optionalEntity = findById(id);

        /*Verifica si optionalEntity está vacío.
        Si es así, registra un mensaje de error y lanza una RuntimeException
        indicando que la entidad no se encuentra o ha sido borrada lógicamente.*/
        if (optionalEntity.isEmpty()){
            String errMsg = "La entidad con el id " + id + " se encuentra borrada logicamente - BaseRepository";
            logger.error(errMsg);
            throw new RuntimeException(errMsg);
        }

        /*Si la entidad se encuentra, se obtiene de Optional usando get()*/
        var entity = optionalEntity.get();

        /*Verifica si la entidad está marcada como eliminada lógicamente (entity.isEliminado()).
        Si es así, registra un mensaje de error y lanza una RuntimeException.*/
        if(entity.isEliminado()){
            String errMsg = "La entidad del tipo " + entity.getClass().getSimpleName() + " con el id " + id + " se encuentra borrada logicamente";
            logger.error(errMsg);
            throw new RuntimeException(errMsg);
        }
        /*Si la entidad existe y no está marcada como eliminada, se devuelve.*/
        return entity;
    }

    /*getAll(): Método personalizado para obtener todas las entidades que no están marcadas como eliminadas.
    Filtra la lista de entidades y excluye las que tienen eliminado igual a true.*/
    default List<E> getAll(){
        logger.info("EJECUTANDO GET ALL PERSONALIZADO getAll()  - BaseRepository");

        /*findAll(): Este método es heredado de JpaRepository
        y se utiliza para obtener todas las entidades del tipo E desde la base de datos.
        Devuelve una lista (List<E>) de todas las entidades.*/
        /*.stream(): Convierte la lista de entidades en un flujo (Stream<E>).
        Los flujos son secuencias de elementos
        que permiten realizar operaciones de procesamiento de datos de manera declarativa.*/
        /*.filter(e -> !e.isEliminado()): Aplica una operación de filtrado al flujo.
        Aquí, se utiliza una expresión lambda (e -> !e.isEliminado())
        para incluir solo las entidades que no están marcadas como eliminadas.
        La expresión lambda verifica que el método isEliminado() devuelva false para cada entidad e en el flujo.*/
        /*.toList(): Convierte el flujo filtrado de vuelta a una lista (List<E>).
        Esta lista contiene solo las entidades que no están marcadas como eliminadas.*/
        var entities = findAll().stream().filter(e -> !e.isEliminado()).toList();
        return entities;
    }
}
