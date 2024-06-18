package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.UnidadMedidaFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.mapper.UnidadMedidaMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.UnidadMedidaService;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaCreateDto;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaDto;
import com.entidades.buenSabor.domain.entities.UnidadMedida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnidadMedidaFacadeImp extends BaseFacadeImp<UnidadMedida, UnidadMedidaDto, UnidadMedidaCreateDto, UnidadMedidaCreateDto, Long> implements UnidadMedidaFacade {
    private final UnidadMedidaService unidadMedidaService;

    @Autowired
    public UnidadMedidaFacadeImp(UnidadMedidaService unidadMedidaService, UnidadMedidaMapper unidadMedidaMapper) {
        super(unidadMedidaService, unidadMedidaMapper);
        this.unidadMedidaService = unidadMedidaService;
    }

    @Override
    public UnidadMedidaDto createNew(UnidadMedidaCreateDto request) {
        UnidadMedida createdEntity = unidadMedidaService.create(request);
        return baseMapper.toDTO(createdEntity);
    }
}
