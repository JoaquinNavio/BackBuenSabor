package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.EmpresaFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.mapper.EmpresaMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.business.service.EmpresaService;
import com.entidades.buenSabor.domain.dto.Empresa.EmpresaCreateDto;
import com.entidades.buenSabor.domain.dto.Empresa.EmpresaDto;
import com.entidades.buenSabor.domain.dto.Empresa.EmpresaLargeDto;
import com.entidades.buenSabor.domain.entities.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmpresaFacadeImpl extends BaseFacadeImp<Empresa, EmpresaDto, EmpresaCreateDto, EmpresaCreateDto,Long> implements EmpresaFacade {

    public EmpresaFacadeImpl(BaseService<Empresa, Long> baseService, BaseMapper<Empresa, EmpresaDto,EmpresaCreateDto, EmpresaCreateDto>baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    EmpresaMapper empresaMapper;

    @Autowired
    EmpresaService empresaService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public EmpresaLargeDto getEmpresaSucursales(Long idEmpresa) {
        return empresaMapper.toLargeDto(empresaService.getEmpresaSucursales(idEmpresa));
    }

    @Override
    public EmpresaDto createNew(EmpresaCreateDto dto) {
        Empresa empresa = baseMapper.toEntityCreate(dto);
        empresa.setUrl_imagen(cloudinaryService.uploadFile(dto.getImagen()));
        Empresa empresaCreada = baseService.create(empresa);
        return baseMapper.toDTO(empresaCreada);
    }

    @Override
    public EmpresaDto update(EmpresaCreateDto dto, Long id) {
        Empresa empresa = baseService.getById(id);
        baseMapper.toEntityCreate(dto);
        empresa.setNombre(dto.getNombre());
        empresa.setRazonSocial(dto.getRazonSocial());
        empresa.setCuil(dto.getCuil());
        MultipartFile imagen = dto.getImagen();
        if (imagen != null && !imagen.isEmpty()) {
            String urlImagen = cloudinaryService.uploadFile(imagen);
            empresa.setUrl_imagen(urlImagen);
        }

        Empresa empresaActualizada = baseService.update(empresa, id);
        return baseMapper.toDTO(empresaActualizada);
    }

}
