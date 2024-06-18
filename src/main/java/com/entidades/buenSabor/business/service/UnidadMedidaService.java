package com.entidades.buenSabor.business.service;


import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaCreateDto;
import com.entidades.buenSabor.domain.entities.UnidadMedida;

import java.util.List;

public interface UnidadMedidaService extends BaseService<UnidadMedida, Long> {
    UnidadMedida create(UnidadMedidaCreateDto unidadMedidaCreateDto);

    List<UnidadMedida> findBySucursalId(Long sucursalId);
}
