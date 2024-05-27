package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.ArticuloManufacturadoFacade;
import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.mapper.ArticuloManufacturadoDetalleMapper;
import com.entidades.buenSabor.business.mapper.Base.BaseMapper;
import com.entidades.buenSabor.business.service.ArticuloManufacturadoService;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloManufacturadoFacadeImp extends BaseFacadeImp<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoCreateDto, Long> implements ArticuloManufacturadoFacade {

    private final ArticuloManufacturadoDetalleMapper articuloManufacturadoDetalleMapper;

    public ArticuloManufacturadoFacadeImp(BaseService<ArticuloManufacturado, Long> baseService, BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoCreateDto> baseMapper, ArticuloManufacturadoDetalleMapper articuloManufacturadoDetalleMapper) {
        super(baseService, baseMapper);
        this.articuloManufacturadoDetalleMapper = articuloManufacturadoDetalleMapper;
    }

    @Override
    public List<ArticuloManufacturadoDetalleDto> getDetallesById(Long id) {
        return ((ArticuloManufacturadoService) baseService).getDetallesById(id).stream().map(articuloManufacturadoDetalleMapper::toDTO).toList();
    }

    @Override
    public ArticuloManufacturado createWithDetails(ArticuloManufacturadoCreateDto dto) {
        return ((ArticuloManufacturadoService) baseService).createWithDetails(dto);
    }

    @Override
    public ArticuloManufacturado updateWithDetails(Long id, ArticuloManufacturadoCreateDto dto) {
        return ((ArticuloManufacturadoService) baseService).updateWithDetails(id, dto);
    }
}