package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionCreateDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDetalleDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalCreateDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalEditDto;
import com.entidades.buenSabor.domain.entities.Promocion;

import java.util.List;

public interface PromocionFacade extends BaseFacade<PromocionDto, PromocionCreateDto, PromocionCreateDto, Long> {
    List<PromocionDetalleDto> getDetallesById(Long id);
    Promocion createWithDetails(PromocionCreateDto dto);
    Promocion updateWithDetails(Long id,PromocionCreateDto dto);
}

