package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.Sucursalfacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalCreateDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalEditDto;
import com.entidades.buenSabor.domain.entities.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalFacadeImp extends BaseFacadeImp<Sucursal, SucursalDto, SucursalCreateDto, SucursalEditDto,Long> implements Sucursalfacade {
    public SucursalFacadeImp(BaseService<Sucursal, Long> baseService, BaseMapper<Sucursal, SucursalDto, SucursalCreateDto, SucursalEditDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public SucursalDto createNew(SucursalCreateDto dto) {
        Sucursal sucursal = baseMapper.toEntityCreate(dto);
        if (dto.getImagen() != null && !dto.getImagen().isEmpty()) {
            sucursal.setUrl_imagen(cloudinaryService.uploadFile(dto.getImagen()));
        }
        Sucursal sucursalCreada = baseService.create(sucursal);
        return baseMapper.toDTO(sucursalCreada);
    }

}
