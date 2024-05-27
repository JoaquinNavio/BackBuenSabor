package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.PromocionDetalleFacadeImp;
import com.entidades.buenSabor.domain.dto.PromocionDetalle.PromocionDetalleCreateDto;
import com.entidades.buenSabor.domain.dto.PromocionDetalle.PromocionDetalleDto;

import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promocionDetalle")
@CrossOrigin("*")
public class PromocionDetalleController extends BaseControllerImp<PromocionDetalle, PromocionDetalleDto, PromocionDetalleCreateDto, PromocionDetalleCreateDto, Long, PromocionDetalleFacadeImp> {

    public PromocionDetalleController(PromocionDetalleFacadeImp facade) {
        super(facade);
    }


}
