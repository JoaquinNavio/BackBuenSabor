package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalCreateDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalEditDto;

public interface Sucursalfacade extends BaseFacade<SucursalDto, SucursalCreateDto, SucursalCreateDto, Long> {
    // Métodos adicionales para la fachada de Sucursal, si es necesario
}
