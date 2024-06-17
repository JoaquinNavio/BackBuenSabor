package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.Empleado;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


public interface EmpleadoService extends BaseService<Empleado, Long> {
    List<Empleado> getEmpleadoBySucursal(Long idSucursal);
    Optional<Empleado> findById(Long id);
    Empleado create(Empleado empleado, MultipartFile imagenFile);
}
