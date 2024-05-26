package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ArticuloManufacturadoFacadeImp;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/ArticuloManufacturado")
public class ArticuloManufacturadoController extends BaseControllerImp<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoCreateDto, Long, ArticuloManufacturadoFacadeImp> {
    public ArticuloManufacturadoController(ArticuloManufacturadoFacadeImp facade) {
        super(facade);
    }

    @GetMapping("/{id}/Detalles")
    public ResponseEntity<List<ArticuloManufacturadoDetalleDto>> getDetallesById(@PathVariable Long id){
        return ResponseEntity.ok(facade.getDetallesById(id));
    }

    @PostMapping("/createWithDetails")
    public ResponseEntity<ArticuloManufacturadoDto> createWithDetails(@RequestBody ArticuloManufacturadoCreateDto dto) {
        ArticuloManufacturado createdArticuloManufacturado = facade.createWithDetails(dto);
        ArticuloManufacturadoDto createdDto = convertToDto(createdArticuloManufacturado);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateWithDetails/{id}")
    public ResponseEntity<ArticuloManufacturadoDto> updateWithDetails(@PathVariable Long id, @RequestBody ArticuloManufacturadoCreateDto dto) {
        ArticuloManufacturado updatedArticuloManufacturado = facade.updateWithDetails(id, dto);
        ArticuloManufacturadoDto updatedDto = convertToDto(updatedArticuloManufacturado);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    private ArticuloManufacturadoDto convertToDto(ArticuloManufacturado articuloManufacturado) {
        ArticuloManufacturadoDto dto = new ArticuloManufacturadoDto();
        dto.setId(articuloManufacturado.getId());
        dto.setDenominacion(articuloManufacturado.getDenominacion());
        dto.setDescripcion(articuloManufacturado.getDescripcion());
        dto.setTiempoEstimadoMinutos(articuloManufacturado.getTiempoEstimadoMinutos());
        dto.setPrecioVenta(articuloManufacturado.getPrecioVenta());
        dto.setPreparacion(articuloManufacturado.getPreparacion());
        // Agrega las conversiones de UnidadMedida y Categoria a DTO si es necesario
        // Agrega la conversión de detalles a DTO si es necesario
        return dto;
    }
}