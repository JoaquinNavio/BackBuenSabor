package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArticuloInsumoService extends BaseService<ArticuloInsumo, Long> {

    ArticuloInsumoDto createCompleto(ArticuloInsumoCreateDto insumoCreateDto);
    List<ArticuloInsumo> findBySucursalId(Long sucursalId);
}
