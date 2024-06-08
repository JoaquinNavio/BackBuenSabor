package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ArticuloManufacturadoFacadeImp;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/ArticuloManufacturado")
public class ArticuloManufacturadoController extends BaseControllerImp<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoCreateDto, Long, ArticuloManufacturadoFacadeImp> {

    public ArticuloManufacturadoController(ArticuloManufacturadoFacadeImp facade) {
        super(facade);
    }

    @GetMapping("/{id}/Detalles")
    public ResponseEntity<List<ArticuloManufacturadoDetalleDto>> getDetallesById(@PathVariable Long id) {
        System.out.println("INICIANDO GET DETALLES BY ID getDetallesById(@PathVariable Long id) - ArticuloManufacturadoController");
        System.out.println("Llamando a FACADE getDetallesById(id) - ArticuloManufacturadoController");
        return ResponseEntity.ok(facade.getDetallesById(id));
    }

    @PostMapping("/createWithDetails")
    @PreAuthorize("hasAuthority('Cocinero') or hasAuthority('Admin')")
    public ResponseEntity<ArticuloManufacturadoDto> createWithDetails(@ModelAttribute ArticuloManufacturadoCreateDto dto) {
        System.out.println("INICIANDO CREATE WITH DETAILS createWithDetails(@RequestBody ArticuloManufacturadoCreateDto dto) - ArticuloManufacturadoController");
        System.out.println("Creando un nuevo ArticuloManufacturado llamando al método createWithDetails(DTO) del FACADE - ArticuloManufacturadoController");
        ArticuloManufacturado createdArticuloManufacturado = facade.createWithDetails(dto);
        System.out.println("Convierte el ArticuloManufacturado creado a su DTO correspondiente llamando a convertToDto(createdArticuloManufacturado) propio del controlador - ArticuloManufacturadoController ");
        ArticuloManufacturadoDto createdDto = convertToDto(createdArticuloManufacturado);
        System.out.println("Devuelve el DTO creaado");
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateWithDetails/{id}")
    @PreAuthorize("hasAuthority('Cocinero') or hasAuthority('Admin')")
    public ResponseEntity<ArticuloManufacturadoDto> updateWithDetails(@PathVariable Long id, @RequestBody ArticuloManufacturadoCreateDto dto) {
        System.out.println("INICIANDO UPDATE WITH DETAILS updateWithDetails(@PathVariable Long id, @RequestBody ArticuloManufacturadoCreateDto dto) - ArticuloManufacturadoController");
        System.out.println("Actualizando y pasando createDto a ArticuloManufacturado y llamando al método updateWithDetails(id, dto) del FACADE - ArticuloManufacturadoController");
        ArticuloManufacturado updatedArticuloManufacturado = facade.updateWithDetails(id, dto);
        System.out.println("Convierte el ArticuloManufacturado actualizado a su DTO correspondiente llamando a convertToDto(updatedArticuloManufacturado) propio del controlador - ArticuloManufacturadoController ");
        ArticuloManufacturadoDto updatedDto = convertToDto(updatedArticuloManufacturado);
        System.out.println("Devuelve el DTO actualizado");
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @PutMapping("/{id}/vincularImagenes")
    @PreAuthorize("hasAuthority('Cocinero') or hasAuthority('Admin')")
    public ResponseEntity<ArticuloManufacturadoDto> vincularImagenes(@PathVariable Long id, @RequestParam(value = "uploads", required = true) MultipartFile[] files) {
        return new ResponseEntity<>(facade.vincularImagenes(files, id), HttpStatus.OK);
    }

    private ArticuloManufacturadoDto convertToDto(ArticuloManufacturado articuloManufacturado) {
        System.out.println("Convirtiendo a DTO la entidad - covertToDto() - ArticuloManufacturadoController");
        ArticuloManufacturadoDto dto = new ArticuloManufacturadoDto();
        dto.setId(articuloManufacturado.getId());
        dto.setDenominacion(articuloManufacturado.getDenominacion());
        dto.setDescripcion(articuloManufacturado.getDescripcion());
        dto.setTiempoEstimadoMinutos(articuloManufacturado.getTiempoEstimadoMinutos());
        dto.setPrecioVenta(articuloManufacturado.getPrecioVenta());
        dto.setPreparacion(articuloManufacturado.getPreparacion());
        return dto;
    }
}
