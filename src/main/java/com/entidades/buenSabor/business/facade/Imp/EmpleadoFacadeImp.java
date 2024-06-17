package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.EmpleadoFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.mapper.EmpleadoMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.Imp.EmpleadoServiceImp;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoCreateDto;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoDto;
import com.entidades.buenSabor.domain.entities.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EmpleadoFacadeImp extends BaseFacadeImp<Empleado, EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto,Long> implements EmpleadoFacade {

    private final EmpleadoServiceImp empleadoServiceImp;
    private final EmpleadoMapper empleadoMapper;

    @Autowired
    public EmpleadoFacadeImp(BaseService<Empleado, Long> baseService, BaseMapper<Empleado, EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto> baseMapper,
                             EmpleadoServiceImp empleadoServiceImp, EmpleadoMapper empleadoMapper) {
        super(baseService, baseMapper);
        this.empleadoServiceImp = empleadoServiceImp;
        this.empleadoMapper = empleadoMapper;
    }


    @Override
    public List<Empleado> getEmpleadosBySucursal(Long idSucursal) {
        return empleadoServiceImp.getEmpleadoBySucursal(idSucursal);
    }

    @Override
    public EmpleadoDto findById(Long id) {
        Empleado empleado = empleadoServiceImp.findById(id).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        return empleadoMapper.toDTO(empleado);
    }

    @Override
    public EmpleadoDto create(EmpleadoCreateDto empleadoCreateDto) {
        Empleado empleado = empleadoMapper.toEntityCreate(empleadoCreateDto);
        MultipartFile imagenFile = empleadoCreateDto.getImagen();
        Empleado savedEmpleado = empleadoServiceImp.create(empleado, imagenFile);
        return empleadoMapper.toDTO(savedEmpleado);
    }
}
