package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDetalleCreateDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDetalleDto;

import java.util.List;

public interface PromocionDetalleFacade extends BaseFacade<PromocionDetalleDto, PromocionDetalleCreateDto, PromocionDetalleCreateDto, Long> {

}
