package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.Sucursalfacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.mapper.DomicilioMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalCreateDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalDto;
import com.entidades.buenSabor.domain.entities.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalFacadeImp extends BaseFacadeImp<Sucursal, SucursalDto, SucursalCreateDto, SucursalCreateDto, Long> implements Sucursalfacade {
    @Autowired
    public SucursalFacadeImp(BaseService<Sucursal, Long> baseService, BaseMapper<Sucursal, SucursalDto, SucursalCreateDto, SucursalCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private DomicilioMapper domicilioMapper;

    @Override
    public SucursalDto createNew(SucursalCreateDto dto) {
        System.out.println("DTO ANTES DE SACAR IMAGEN");
        System.out.println(dto.toString());
        Sucursal sucursal = baseMapper.toEntityCreate(dto);
        if (dto.getImagen() != null && !dto.getImagen().isEmpty()) {
            sucursal.setUrl_imagen(cloudinaryService.uploadFile(dto.getImagen()));
        }
        System.out.println("SUCURSAL ANTES DE GUARDAR");
        System.out.println(sucursal.toString());

        Sucursal sucursalCreada = baseService.create(sucursal);
        return baseMapper.toDTO(sucursalCreada);
    }

    @Override
    public SucursalDto update(SucursalCreateDto dto, Long id) {
        Sucursal sucursal = baseService.getById(id);

        sucursal.setNombre(dto.getNombre());
        sucursal.setHorarioApertura(dto.getHorarioApertura());
        sucursal.setHorarioCierre(dto.getHorarioCierre());
        sucursal.setEsCasaMatriz(dto.getEsCasaMatriz());

        // Convertir y establecer el domicilio
        sucursal.setDomicilio(domicilioMapper.toEntityCreate(dto.getDomicilio()));

        if (dto.getImagen() != null && !dto.getImagen().isEmpty()) {
            sucursal.setUrl_imagen(cloudinaryService.uploadFile(dto.getImagen()));
        }

        Sucursal sucursalActualizada = baseService.update(sucursal, id);
        return baseMapper.toDTO(sucursalActualizada);
    }
}
