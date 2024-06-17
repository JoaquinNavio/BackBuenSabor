package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoCreateDto;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoDto;
import com.entidades.buenSabor.domain.entities.Empleado;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmpleadoFacade extends BaseFacade<EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto, Long> {
    List<Empleado> getEmpleadosBySucursal(Long id);
    EmpleadoDto findById(Long id);
    EmpleadoDto create(EmpleadoCreateDto empleadoCreateDto);
}
