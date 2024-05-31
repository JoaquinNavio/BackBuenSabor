package com.entidades.buenSabor.presentation.rest.Base;

import com.entidades.buenSabor.domain.dto.BaseDto;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

/*¿Qué es un controlador?
En el contexto de una aplicación web,
un controlador es una clase que maneja las solicitudes HTTP entrantes y determina cómo responder a ellas.
Los controladores son parte del patrón MVC (Modelo-Vista-Controlador) y son responsables de:
* Recibir las solicitudes HTTP.
* Llamar a los servicios o facades para procesar la lógica de negocio.
* Devolver una respuesta HTTP adecuada al cliente.*/

/*BaseController: Define métodos CRUD básicos (getById, getAll, create, edit, deleteById)
 que los controladores específicos deben implementar.*/
public interface BaseController <D extends BaseDto, DC, DE, ID extends Serializable> {
    ResponseEntity<D> getById(ID id);

    ResponseEntity<List<D>> getAll();

    ResponseEntity<D> create(DC entity);

    ResponseEntity<D> edit(DE entity, ID id);

    ResponseEntity<?> deleteById(ID id);
}