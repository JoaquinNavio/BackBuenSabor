package com.entidades.buenSabor.business.facade.Imp;


import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.CategoriaFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaCreateDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.entities.Categoria;
import org.springframework.stereotype.Service;

@Service
public class CategoriaFacadeImp extends BaseFacadeImp<Categoria, CategoriaDto, CategoriaCreateDto, CategoriaCreateDto,Long> implements CategoriaFacade {
    public CategoriaFacadeImp(BaseService<Categoria, Long> baseService, BaseMapper<Categoria, CategoriaDto, CategoriaCreateDto, CategoriaCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Override
    public CategoriaDto createNew(CategoriaCreateDto request) {
        var entityToCreate = baseMapper.toEntityCreate(request);
        if (request.getCategoriaPadreId() != null) {
            Categoria categoriaPadre = baseService.getById(request.getCategoriaPadreId());
            entityToCreate.setCategoriaPadre(categoriaPadre);
        }
        var entityCreated = baseService.create(entityToCreate);
        return baseMapper.toDTO(entityCreated);
    }

    @Override
    public CategoriaDto update(CategoriaCreateDto request, Long id) {
        var entityToUpdate = baseService.getById(id);
        var entityUpdatedByMapper = baseMapper.toUpdate(entityToUpdate, request);
        if (request.getCategoriaPadreId() != null) {
            Categoria categoriaPadre = baseService.getById(request.getCategoriaPadreId());
            entityUpdatedByMapper.setCategoriaPadre(categoriaPadre);
        } else {
            entityUpdatedByMapper.setCategoriaPadre(null); // Manejar el caso donde se pueda querer eliminar la categoriaPadre
        }
        var entityUpdatedByService = baseService.update(entityUpdatedByMapper, id);
        return baseMapper.toDTO(entityUpdatedByService);
    }
}
