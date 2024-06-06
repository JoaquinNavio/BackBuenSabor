package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import org.springframework.http.ResponseEntity;

public interface ArticuloInsumoService extends BaseService<ArticuloInsumo, Long> {

    ArticuloInsumoDto createCompleto(ArticuloInsumoCreateDto insumoCreateDto);
}
