package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.ArticuloManufacturadoDetalleFacade;
import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.mapper.Base.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import org.springframework.stereotype.Service;

@Service
public class ArticuloManufacturadoDetalleFacadeImp extends BaseFacadeImp<ArticuloManufacturadoDetalle, ArticuloManufacturadoDetalleDto, ArticuloManufacturadoDetalleCreateDto, ArticuloManufacturadoDetalleCreateDto, Long> implements ArticuloManufacturadoDetalleFacade {
    public ArticuloManufacturadoDetalleFacadeImp(BaseService<ArticuloManufacturadoDetalle, Long> baseService, BaseMapper<ArticuloManufacturadoDetalle, ArticuloManufacturadoDetalleDto,ArticuloManufacturadoDetalleCreateDto, ArticuloManufacturadoDetalleCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
