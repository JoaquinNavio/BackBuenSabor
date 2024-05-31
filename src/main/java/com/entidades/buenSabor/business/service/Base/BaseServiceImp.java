package com.entidades.buenSabor.business.service.Base;
import com.entidades.buenSabor.domain.entities.Base;
import com.entidades.buenSabor.repositories.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/*@Service:
Indica que esta clase es un componente de servicio de Spring,
lo que permite la inyección de dependencias y otras funcionalidades proporcionadas por el contenedor de Spring.*/
@Service
public abstract class BaseServiceImp<E extends Base,ID extends Serializable> implements BaseService<E, ID> {

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImp.class);

    //repositorio que permite la interacción con la base de datos.
    @Autowired
    protected BaseRepository<E,ID> baseRepository;

    /*create(E request):
    Guarda una nueva entidad en la base de datos utilizando baseRepository.save(request)*/
    @Override
    public E create(E request){
        logger.info("EJECUTANDO create(E request) - BaseServiceImp");
        logger.info("Guardando entidad con save(request) de BaseRepository - BaseServiceImp");
        var newEntity = baseRepository.save(request);
        logger.info("Creada entidad, retornando {}",newEntity);
        return newEntity;
    }


    /*getById(ID id): Obtiene una entidad por su ID utilizando el método baseRepository.getById(id)*/
    @Override
    public E getById(ID id){
        logger.info("EJECUTANDO getById(ID id) - BaseServiceImp");
        logger.info("Obteniendo entidad con getById(id) de BaseRepository - BaseServiceImp");
        var entity = baseRepository.getById(id);
        logger.info("Obtenida entidad, retornando {}",entity);
        return entity;
    }


    /*getAll(): Obtiene todas las entidades de la base de datos utilizando el método baseRepository.getAll().*/
    @Override
    public List<E> getAll(){
        logger.info("EJECUTANDO getAll() - BaseServiceImp");
        logger.info("Obteniendo entidades con getAll() de BaseRepository - BaseServiceImp");
        var entities = baseRepository.getAll();
        logger.info("Obtenidas entidades, retornando {}",entities);
        return entities;
    }

    /*deleteById(ID id): Borra lógicamente una entidad utilizando el método baseRepository.delete(entity)*/
    @Override
    public void deleteById(ID id){
        logger.info("EJECUTANDO deleteById(ID id) - BaseServiceImp");
        logger.info("Obteniendo entidad con getById(id) propio de BaseServiceImp - BaseServiceImp");
        var entity = getById(id);
        logger.info("Eliminando entidad logicamente con delete(entity) de BaseRepository - BaseServiceImp");
        baseRepository.delete(entity);
        logger.info("Borrada logicamente la entidad {}",entity);
    }

    /*update(E request, ID id):
    Actualiza una entidad existente en la base de datos utilizando el método baseRepository.save(request).*/
    @Override
    public E update(E request, ID id){
        logger.info("EJECUTANDO update(E request, ID id) - BaseServiceImp");
        logger.info("Obteniendo entidad con findById de BaseRepository - BaseServiceImp");
        /*baseRepository.findById((ID) request.getId()):
        Se utiliza el método findById del repositorio para obtener una entidad opcional (Optional)
        basada en el ID proporcionado en el objeto request*/
        var optionalEntity = baseRepository.findById((ID) request.getId());

        if (optionalEntity.isEmpty()){
            logger.error("No se encontro una entidad con el id " + request.getId());
            throw new RuntimeException("No se encontro una entidad con el id " + request.getId());
        }

        logger.info("Guardando cambios en entidad con save(request) de BaseRepository - BaseServiceImp");
        /* baseRepository.save(request):
        Si la entidad existe en la base de datos,
        se actualiza utilizando el método save del repositorio (baseRepository).
        Este método guarda los cambios realizados en la entidad y devuelve la entidad actualizada.*/
        var newEntity = baseRepository.save(request);
        logger.info("Actualizada entidad, retornando {}",newEntity);
        return newEntity;
    }
}
