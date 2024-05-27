package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.PromocionDetalleFacade;
import com.entidades.buenSabor.business.mapper.PromocionDetalleMapper;
import com.entidades.buenSabor.business.service.PromocionDetalleService;

import com.entidades.buenSabor.domain.dto.PromocionDetalle.PromocionDetalleCreateDto;
import com.entidades.buenSabor.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import org.springframework.stereotype.Service;

@Service
public class PromocionDetalleFacadeImp extends BaseFacadeImp<PromocionDetalle, PromocionDetalleDto, PromocionDetalleCreateDto, PromocionDetalleCreateDto, Long> implements PromocionDetalleFacade {

    public PromocionDetalleFacadeImp(PromocionDetalleService promocionDetalleService, PromocionDetalleMapper promocionDetalleMapper) {
        super(promocionDetalleService, promocionDetalleMapper);

    }
}