package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;

import java.util.List;

public interface ArticuloManufacturadoService extends BaseService<ArticuloManufacturado, Long> {
    List<ArticuloManufacturadoDetalle> getDetallesById(Long id);
    ArticuloManufacturado createWithDetails(ArticuloManufacturadoCreateDto dto);
    ArticuloManufacturado updateWithDetails(Long id,ArticuloManufacturadoCreateDto dto);

}
