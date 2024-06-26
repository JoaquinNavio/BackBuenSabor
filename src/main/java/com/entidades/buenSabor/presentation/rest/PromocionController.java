package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.PromocionFacadeImp;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionCreateDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDetalleDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDto;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promociones")
@CrossOrigin("*")
public class PromocionController extends BaseControllerImp<Promocion, PromocionDto, PromocionCreateDto, PromocionCreateDto, Long, PromocionFacadeImp> {
    public PromocionController(PromocionFacadeImp facade) {
        super(facade);
    }

    @GetMapping("/{id}/Detalles")
    public ResponseEntity<List<PromocionDetalleDto>> getDetallesById(@PathVariable Long id) {
        return ResponseEntity.ok(facade.getDetallesById(id));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<PromocionDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(facade.getById(id));
    }


    @PostMapping("/createWithDetails")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<PromocionDto> createWithDetails(@RequestBody PromocionCreateDto dto) {
        Promocion createdPromocion = facade.createWithDetails(dto);
        PromocionDto createdDto = facade.convertToDto(createdPromocion);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateWithDetails/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<PromocionDto> updateWithDetails(@PathVariable Long id, @RequestBody PromocionCreateDto dto) {
        Promocion updatedPromocion = facade.updateWithDetails(id, dto);
        PromocionDto updatedDto = facade.convertToDto(updatedPromocion);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<PromocionDto>> getBySucursalId(@PathVariable Long sucursalId) {
        List<Promocion> promociones = facade.getBySucursalId(sucursalId);
        List<PromocionDto> promocionesDto = promociones.stream()
                .map(facade::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(promocionesDto);
    }
}
