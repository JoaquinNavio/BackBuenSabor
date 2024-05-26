package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;

import java.util.List;

public interface PromocionService extends BaseService<Promocion, Long> {
    List<PromocionDetalle> getDetallesById(Long id);

}
