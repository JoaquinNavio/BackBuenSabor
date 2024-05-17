package com.entidades.buenSabor.business.facade;


import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaCreateDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;

public interface CategoriaFacade extends BaseFacade<CategoriaDto, CategoriaCreateDto, CategoriaCreateDto, Long> {
}
