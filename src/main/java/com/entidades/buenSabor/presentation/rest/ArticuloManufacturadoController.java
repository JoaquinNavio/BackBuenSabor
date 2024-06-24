package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ArticuloManufacturadoFacadeImp;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoEcommerseDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

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
    //@PreAuthorize("hasAuthority('Cocinero') or hasAuthority('Admin')")
    public ResponseEntity<ArticuloManufacturadoDto> createWithDetails(@ModelAttribute ArticuloManufacturadoCreateDto dto) {
        ArticuloManufacturado createdArticuloManufacturado = facade.createWithDetails(dto);
        ArticuloManufacturadoDto createdDto = facade.convertToDto(createdArticuloManufacturado);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateWithDetails/{id}")
    //@PreAuthorize("hasAuthority('Cocinero') or hasAuthority('Admin')")
    public ResponseEntity<ArticuloManufacturadoDto> updateWithDetails(@PathVariable Long id, @RequestBody ArticuloManufacturadoCreateDto dto) {
        ArticuloManufacturado updatedArticuloManufacturado = facade.updateWithDetails(id, dto);
        ArticuloManufacturadoDto updatedDto = facade.convertToDto(updatedArticuloManufacturado);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @PutMapping("/{id}/vincularImagenes")
    @PreAuthorize("hasAuthority('Cocinero') or hasAuthority('Admin')")
    public ResponseEntity<ArticuloManufacturadoDto> vincularImagenes(@PathVariable Long id, @RequestParam(value = "uploads", required = true) MultipartFile[] files) {
        return new ResponseEntity<>(facade.vincularImagenes(files, id), HttpStatus.OK);
    }

    @GetMapping("/Ecommerse")
    public ResponseEntity<List<ArticuloManufacturadoEcommerseDto>> getManufacturadoEcommerse() {
        System.out.println("entro al controlador");
        List<ArticuloManufacturadoEcommerseDto> articulos = facade.getManufacturadosEcommerse();
        return ResponseEntity.ok(articulos);
    }

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<ArticuloManufacturadoDto>> getBySucursalId(@PathVariable Long sucursalId) {
        List<ArticuloManufacturado> articulos = facade.getBySucursalId(sucursalId);
        List<ArticuloManufacturadoDto> articulosDto = articulos.stream()
                .map(facade::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(articulosDto);
    }



}
