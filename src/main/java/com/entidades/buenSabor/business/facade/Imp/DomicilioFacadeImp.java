package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.DomicilioFacade;
import com.entidades.buenSabor.business.mapper.Base.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioCreateDto;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioDto;
import com.entidades.buenSabor.domain.entities.Domicilio;
import org.springframework.stereotype.Service;

@Service
public class DomicilioFacadeImp extends BaseFacadeImp<Domicilio, DomicilioDto, DomicilioCreateDto,DomicilioCreateDto, Long> implements DomicilioFacade {
    public DomicilioFacadeImp(BaseService<Domicilio, Long> baseService, BaseMapper<Domicilio, DomicilioDto,DomicilioCreateDto, DomicilioCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }

}
