package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.PromocionFacadeImp;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionCreateDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDetalleDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDto;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promociones")
@CrossOrigin("*")
public class PromocionController extends BaseControllerImp<Promocion, PromocionDto, PromocionCreateDto, PromocionCreateDto, Long, PromocionFacadeImp> {
    public PromocionController(PromocionFacadeImp facade) {
        super(facade);
    }

    @GetMapping("/{id}/Detalles")
    public ResponseEntity<List<PromocionDetalleDto>> getDetallesById(@PathVariable Long id){
        return ResponseEntity.ok(facade.getDetallesById(id));
    }


    @PostMapping("/createWithDetails")
    public ResponseEntity<PromocionDto> createWithDetails(@RequestBody PromocionCreateDto dto) {
        Promocion createdPromocion = facade.createWithDetails(dto);
        PromocionDto createdDto = convertToDto(createdPromocion);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateWithDetails/{id}")
    public ResponseEntity<PromocionDto> updateWithDetails(@PathVariable Long id, @RequestBody PromocionCreateDto dto) {
        Promocion updatedPromocion = facade.updateWithDetails(id, dto);
        PromocionDto updatedDto = convertToDto(updatedPromocion);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    private PromocionDto convertToDto(Promocion promocion) {
        PromocionDto dto = new PromocionDto();
        dto.setId(promocion.getId());
        dto.setDenominacion(promocion.getDenominacion());
        dto.setFechaDesde(promocion.getFechaDesde());
        dto.setFechaHasta(promocion.getFechaHasta());
        dto.setHoraDesde(promocion.getHoraDesde());
        dto.setHoraHasta(promocion.getHoraHasta());
        dto.setDescripcionDescuento(promocion.getDescripcionDescuento());
        dto.setPrecioPromocional(promocion.getPrecioPromocional());
        dto.setTipoPromocion(promocion.getTipoPromocion());

        return dto;
    }
}
