package com.entidades.buenSabor.business.facade;


import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaCreateDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;

import java.util.List;

public interface CategoriaFacade extends BaseFacade<CategoriaDto, CategoriaCreateDto, CategoriaCreateDto, Long> {
    List<CategoriaDto> getAllNoElaborar();
    List<CategoriaDto> getAllElaborar();
}
