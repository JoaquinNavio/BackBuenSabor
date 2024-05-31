package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ArticuloManufacturadoFacadeImp;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*ArticuloManufacturadoController: maneja las solicitudes HTTP específicas para ArticuloManufacturado.
* Define métodos adicionales específicos para ArticuloManufacturado,
* como getDetallesById, createWithDetails, y updateWithDetails.*/


/*@RestController: Marca la clase como un controlador de Spring MVC especializado en RESTful web services.
Indica que los métodos de la clase manejan solicitudes HTTP
y que las respuestas se serializan directamente en JSON o XML.*/
@RestController
/*@CrossOrigin("*"): Permite el acceso desde cualquier origen.
Habilita CORS (Cross-Origin Resource Sharing),
lo que permite que recursos en el servidor sean accedidos desde diferentes dominios.
El uso de "*" permite todos los dominios.*/
@CrossOrigin("*")
/*@RequestMapping("/ArticuloManufacturado"): Define la ruta base para todas las solicitudes manejadas por este controlador.
Todas las rutas de los métodos en esta clase comenzarán con /ArticuloManufacturado.*/
@RequestMapping("/ArticuloManufacturado")
public class ArticuloManufacturadoController extends BaseControllerImp<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoCreateDto, Long, ArticuloManufacturadoFacadeImp> {

    /*Metodo constructor: Inicializa el controlador con una instancia de ArticuloManufacturadoFacadeImp.
    * Permite inyectar la dependencia del facade para manejar la lógica de negocio*/
    public ArticuloManufacturadoController(ArticuloManufacturadoFacadeImp facade) {
        //Permite inyectar la dependencia del facade para manejar la lógica de negocio
        super(facade);
    }

    /*getDetallesById(id): Maneja una solicitud GET para obtener los detalles de un ArticuloManufacturado específico por su ID
    * Mapea la URL /ArticuloManufacturado/{id}/Detalles a este método.
    * Usa el id de la ruta para llamar al método getDetallesById del facade.
    * Devuelve los detalles envueltos en un ResponseEntity con estado HTTP 200 OK.*/
    @GetMapping("/{id}/Detalles")
    public ResponseEntity<List<ArticuloManufacturadoDetalleDto>> getDetallesById(@PathVariable Long id){
        System.out.println("INICIANDO GET DETALLES BY ID getDetallesById(@PathVariable Long id) - ArticuloManufacturadoController");
        System.out.println("Llamando a FACADE getDetallesById(id) - ArticuloManufacturadoController");
        return ResponseEntity.ok(facade.getDetallesById(id));
    }

    /*createWithDetails(): Maneja una solicitud POST para crear un ArticuloManufacturado con detalles.
    * Mapea la URL /ArticuloManufacturado/createWithDetails a este método.
    * Usa el cuerpo de la solicitud (deserializado en ArticuloManufacturadoCreateDto)
    para crear un nuevo ArticuloManufacturado llamando al método createWithDetails del facade.
    * Convierte el ArticuloManufacturado creado a su DTO correspondiente.
    * Devuelve el DTO envuelto en un ResponseEntity con estado HTTP 201 CREATED.*/
    @PostMapping("/createWithDetails")
    public ResponseEntity<ArticuloManufacturadoDto> createWithDetails(@RequestBody ArticuloManufacturadoCreateDto dto) {
        System.out.println("INICIANDO CREATE WITH DETAILS createWithDetails(@RequestBody ArticuloManufacturadoCreateDto dto) - ArticuloManufacturadoController");
        System.out.println("Creando un nuevo ArticuloManufacturado llamando al método createWithDetails(DTO) del FACADE - ArticuloManufacturadoController");
        ArticuloManufacturado createdArticuloManufacturado = facade.createWithDetails(dto);
        System.out.println("Convierte el ArticuloManufacturado creado a su DTO correspondiente llamando a convertToDto(createdArticuloManufacturado) propio del controlador - ArticuloManufacturadoController ");
        ArticuloManufacturadoDto createdDto = convertToDto(createdArticuloManufacturado);
        System.out.println("Devuelve el DTO creaado");
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    /*updateWithDetails(id): Maneja una solicitud PUT para actualizar un ArticuloManufacturado con detalles.
    * Mapea la URL /ArticuloManufacturado/updateWithDetails/{id} a este método.
    * Usa el id de la ruta y el cuerpo de la solicitud (deserializado en ArticuloManufacturadoCreateDto)
    para actualizar el ArticuloManufacturado llamando al método updateWithDetails del facade.
    * Convierte el ArticuloManufacturado actualizado a su DTO correspondiente.
    * Devuelve el DTO actualizado envuelto en un ResponseEntity con estado HTTP 200 OK.*/
    @PutMapping("/updateWithDetails/{id}")
    public ResponseEntity<ArticuloManufacturadoDto> updateWithDetails(@PathVariable Long id, @RequestBody ArticuloManufacturadoCreateDto dto) {
        System.out.println("INICIANDO UPDATE WITH DETAILS updateWithDetails(@PathVariable Long id, @RequestBody ArticuloManufacturadoCreateDto dto) - ArticuloManufacturadoController");
        System.out.println("Actualizando y pasando createDto a ArticuloManufacturado y llamando al método updateWithDetails(id, dto) del FACADE - ArticuloManufacturadoController");
        ArticuloManufacturado updatedArticuloManufacturado = facade.updateWithDetails(id, dto);
        System.out.println("Convierte el ArticuloManufacturado actualizado a su DTO correspondiente llamando a convertToDto(updatedArticuloManufacturado) propio del controlador - ArticuloManufacturadoController ");
        ArticuloManufacturadoDto updatedDto = convertToDto(updatedArticuloManufacturado);
        System.out.println("Devuelve el DTO actualizado");
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    /*convertToDto(ArticuloManufacturado):
    Convierte una entidad ArticuloManufacturado a su correspondiente DTO (ArticuloManufacturadoDto).
    * Crea una nueva instancia de ArticuloManufacturadoDto.
    * Copia los valores de los atributos de ArticuloManufacturado al DTO.
    * Devuelve el DTO.*/
    private ArticuloManufacturadoDto convertToDto(ArticuloManufacturado articuloManufacturado) {
        System.out.println("Convirtiendo a DTO la entidad - covertToDto() - ArticuloManufacturadoController");
        //Crea una nueva instancia de ArticuloManufacturadoDto.
        ArticuloManufacturadoDto dto = new ArticuloManufacturadoDto();
        //Copia los valores de los atributos de ArticuloManufacturado al DTO
        dto.setId(articuloManufacturado.getId());
        dto.setDenominacion(articuloManufacturado.getDenominacion());
        dto.setDescripcion(articuloManufacturado.getDescripcion());
        dto.setTiempoEstimadoMinutos(articuloManufacturado.getTiempoEstimadoMinutos());
        dto.setPrecioVenta(articuloManufacturado.getPrecioVenta());
        dto.setPreparacion(articuloManufacturado.getPreparacion());
        //TODO Agregar las conversiones de UnidadMedida y Categoria a DTO si es necesario
        //TODO Agregar la conversión de detalles a DTO si es necesario
        //Devuelve el DTO
        return dto;
    }
}