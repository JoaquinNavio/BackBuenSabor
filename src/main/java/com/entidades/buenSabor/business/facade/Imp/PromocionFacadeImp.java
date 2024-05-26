package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.ArticuloManufacturadoFacade;
import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.PromocionFacade;
import com.entidades.buenSabor.business.mapper.ArticuloManufacturadoDetalleMapper;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.mapper.PromocionDetalleMapper;
import com.entidades.buenSabor.business.mapper.PromocionMapper;
import com.entidades.buenSabor.business.service.ArticuloManufacturadoService;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.PromocionService;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionCreateDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDetalleDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.Promocion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionFacadeImp extends BaseFacadeImp<Promocion, PromocionDto, PromocionCreateDto, PromocionCreateDto, Long> implements PromocionFacade {

    public PromocionFacadeImp(BaseService<Promocion, Long> baseService, BaseMapper<Promocion, PromocionDto, PromocionCreateDto, PromocionCreateDto> baseMapper, ArticuloManufacturadoDetalleMapper articuloManufacturadoDetalleMapper) {
        super(baseService, baseMapper);
        this.promocionDetalleMapper = promocionDetalleMapper;
    }


    private PromocionDetalleMapper promocionDetalleMapper = null;

    public List<PromocionDetalleDto> getDetallesById(Long id) {
        return ((PromocionService)baseService).getDetallesById(id).stream().map(promocionDetalleMapper::toDTO).toList();
    }
}
