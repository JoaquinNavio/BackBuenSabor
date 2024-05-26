package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.PromocionFacadeImp;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionCreateDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDetalleDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDto;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
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
}
