package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;

import java.util.List;

public interface CategoriaService extends BaseService<Categoria, Long> {
    List<Categoria> getAllNoElaborar();
    List<Categoria> getAllElaborar();
    List<Categoria> findBySucursalId(Long sucursalId);
}
