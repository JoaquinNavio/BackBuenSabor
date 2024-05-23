package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ArticuloManufacturadoDetalleFacadeImp;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.dto.Empresa.EmpresaLargeDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/ArticuloManufacturadoDetalle")
public class ArticuloManufacturadoDetalleController extends BaseControllerImp<ArticuloManufacturadoDetalle, ArticuloManufacturadoDetalleDto, ArticuloManufacturadoDetalleCreateDto, ArticuloManufacturadoDetalleCreateDto, Long, ArticuloManufacturadoDetalleFacadeImp> {
    public ArticuloManufacturadoDetalleController(ArticuloManufacturadoDetalleFacadeImp facade) {
        super(facade);
    }
}
