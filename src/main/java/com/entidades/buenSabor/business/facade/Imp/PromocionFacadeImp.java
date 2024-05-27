package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.PromocionFacade;
import com.entidades.buenSabor.business.mapper.ArticuloManufacturadoDetalleMapper;
import com.entidades.buenSabor.business.mapper.Base.BaseMapper;
import com.entidades.buenSabor.business.mapper.PromocionDetalleMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.PromocionService;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionCreateDto;
import com.entidades.buenSabor.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDto;
import com.entidades.buenSabor.domain.entities.Promocion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionFacadeImp extends BaseFacadeImp<Promocion, PromocionDto, PromocionCreateDto, PromocionCreateDto, Long> implements PromocionFacade {
    private PromocionDetalleMapper promocionDetalleMapper = null;

    public PromocionFacadeImp(BaseService<Promocion, Long> baseService, BaseMapper<Promocion, PromocionDto, PromocionCreateDto, PromocionCreateDto> baseMapper, ArticuloManufacturadoDetalleMapper articuloManufacturadoDetalleMapper) {
        super(baseService, baseMapper);
        this.promocionDetalleMapper = promocionDetalleMapper;
    }


    public List<PromocionDetalleDto> getDetallesById(Long id) {
        return ((PromocionService)baseService).getDetallesById(id).stream().map(promocionDetalleMapper::toDTO).toList();
    }

    @Override
    public Promocion createWithDetails(PromocionCreateDto dto) {
        return ((PromocionService) baseService).createWithDetails(dto);
    }

    @Override
    public Promocion updateWithDetails(Long id, PromocionCreateDto dto) {
        return ((PromocionService) baseService).updateWithDetails(id, dto);
    }
}
