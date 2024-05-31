package com.entidades.buenSabor.presentation.rest.Base;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.entities.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/*BaseControllerImp: Implementa los métodos definidos en BaseController
y delega la lógica de negocio a la capa de servicio (facade).
Maneja las solicitudes HTTP y devuelve las respuestas adecuadas.*/

/*@Controller: Indica a Spring que esta clase manejará las solicitudes HTTP entrantes
y actuará como intermediario entre el cliente y la lógica de negocio.*/
@Controller
public abstract class BaseControllerImp <E extends Base,D extends BaseDto, DC, DE, ID extends Serializable, F extends BaseFacadeImp<E,D, DC, DE,ID>> implements BaseController<D,DC, DE, ID> {

    private static final Logger logger = LoggerFactory.getLogger(BaseControllerImp.class);

    //Atributo de la clase, se instancia en el constructor
    protected F facade;
    /*Inicializa el controlador con una instancia de facade,
    que es una capa de servicio que maneja la lógica de negocio.*/
    public BaseControllerImp(F facade){
        this.facade = facade;
    }

    /*@GetMapping, @PostMapping, @PutMapping, @DeleteMapping
    Propósito: Mapean los métodos de la clase a las solicitudes HTTP GET, POST, PUT y DELETE respectivamente.
    Función: Permiten definir rutas específicas y asociar los métodos del controlador a estas rutas.
    Esto facilita la creación de endpoints RESTful.*/


    /*getById(id): Maneja una solicitud GET para obtener una entidad por su ID
    * Registra una entrada en el log indicando el inicio del método.
    * Llama al método getById del facade para obtener la entidad.
    * Devuelve la entidad envuelta en un ResponseEntity con estado HTTP 200 OK*/
    @GetMapping("/{id}")
    /*@PathVariable: Vincula una variable de ruta en la URL de la solicitud con un parámetro del método.
    Permite extraer valores de la URL y usarlos en el método del controlador.
    Por ejemplo, @PathVariable ID id vincula el valor del segmento {id} de la URL al parámetro id*/
    public ResponseEntity<D> getById(@PathVariable ID id){
        logger.info("INICIO GET BY ID {} getById(@PathVariable ID id) - BaseController",id);
        logger.info("Llamando a FACADE getById(id) - BaseController");
        return ResponseEntity.ok(facade.getById(id));
    }

    /*getAll(): Maneja una solicitud GET para obtener todas las entidades
    * Registra una entrada en el log indicando el inicio del método.
    * Llama al método getAll del facade para obtener todas las entidades.
    * Devuelve la lista de entidades envuelta en un ResponseEntity con estado HTTP 200 OK.*/
    @GetMapping
    public ResponseEntity<List<D>> getAll() {
        logger.info("INICIO GET ALL getAll() - BaseController");
        logger.info("Llamando a FACADE getAll() - BaseController");
        return ResponseEntity.ok(facade.getAll());
    }

    /*create(body): Maneja una solicitud POST para crear una nueva entidad.
    * Registra una entrada en el log indicando el inicio del método y la clase de la entidad a crear.
    * Llama al método createNew del facade para crear la entidad.
    * Devuelve la entidad creada envuelta en un ResponseEntity con estado HTTP 200 OK.*/
    @PostMapping()
    /*@RequestBody: Indica que un parámetro del método debe ser vinculado al cuerpo de la solicitud HTTP.
    Permite deserializar el cuerpo de la solicitud JSON en un objeto Java.
    Por ejemplo, @RequestBody DC entity toma el JSON del cuerpo de la solicitud y lo convierte en un objeto DC.*/
    public ResponseEntity<D> create(@RequestBody DC entity){
        logger.info("INICIO CREATE {} create(@RequestBody DC entity) - BaseController",entity.getClass());
        logger.info("Llamando a FACADE createNew(entity) - BaseController");
        return ResponseEntity.ok(facade.createNew(entity));
    }

    /*edit(id): Maneja una solicitud PUT para actualizar una entidad existente.
    * Registra una entrada en el log indicando el inicio del método, la clase de la entidad a editar y el contenido de la entidad.
    * Llama al método update del facade para actualizar la entidad.
    * Devuelve la entidad actualizada envuelta en un ResponseEntity con estado HTTP 200 OK. */
    @PutMapping("/{id}")
    public ResponseEntity<D> edit(@RequestBody DE entity, @PathVariable ID id) {
        logger.info("INICIO EDIT {} edit(@RequestBody DE entity, @PathVariable ID id) - BaseController", entity.getClass());
        logger.info("ENTIDAD QUE TRAE {} - BaseController", entity.toString());
        logger.info("Llamando a FACADE update(entity, id)  - BaseController");
        return ResponseEntity.ok(facade.update(entity, id));
    }

    /*deleteById(id): Maneja una solicitud DELETE para eliminar una entidad por su ID
    * Registra una entrada en el log indicando el inicio del método.
    * Llama al método deleteById del facade para eliminar la entidad.
    * Devuelve una respuesta HTTP con estado 200 OK. */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ID id){
        logger.info("INICIO DELETE BY ID deleteById(@PathVariable ID id) - BaseController");
        logger.info("Llamando a FACADE deleteById(id)  - BaseController");
        facade.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
