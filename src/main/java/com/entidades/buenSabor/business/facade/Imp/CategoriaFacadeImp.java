package com.entidades.buenSabor.business.facade.Imp;


import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.CategoriaFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.CategoriaService;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaCreateDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaFacadeImp extends BaseFacadeImp<Categoria, CategoriaDto, CategoriaCreateDto, CategoriaCreateDto,Long> implements CategoriaFacade {
    public CategoriaFacadeImp(BaseService<Categoria, Long> baseService, BaseMapper<Categoria, CategoriaDto, CategoriaCreateDto, CategoriaCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public CategoriaDto createNew(CategoriaCreateDto request) {
        var entityToCreate = baseMapper.toEntityCreate(request);
        if (request.getCategoriaPadreId() != null) {
            Categoria categoriaPadre = baseService.getById(request.getCategoriaPadreId());
            entityToCreate.setCategoriaPadre(categoriaPadre);
        }

        if (request.getSucursal_id() != null) {
            Sucursal sucursal = sucursalRepository.findById(request.getSucursal_id())
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
            entityToCreate.setSucursal(sucursal);
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

    @Override
    public List<CategoriaDto> getAllNoElaborar(){
        List<Categoria> categorias =  categoriaService.getAllNoElaborar();
        return baseMapper.toDTOsList(categorias);
    }

    @Override
    public List<CategoriaDto> getAllElaborar(){
        List<Categoria> categorias =  categoriaService.getAllElaborar();
        return baseMapper.toDTOsList(categorias);
    }
}
