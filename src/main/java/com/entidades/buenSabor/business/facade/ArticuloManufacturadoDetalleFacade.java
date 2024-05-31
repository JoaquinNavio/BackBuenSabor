package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;

/*ArticuloManufacturadoDetalleFacade:
Para la gestión de entidades de tipo ArticuloManufacturadoDetalle.
No agrega métodos adicionales, ya que hereda las operaciones básicas de BaseFacade.*/
public interface ArticuloManufacturadoDetalleFacade extends BaseFacade<ArticuloManufacturadoDetalleDto, ArticuloManufacturadoDetalleCreateDto, ArticuloManufacturadoDetalleCreateDto, Long> {
}
