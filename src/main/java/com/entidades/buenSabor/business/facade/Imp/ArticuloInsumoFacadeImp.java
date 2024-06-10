package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.ArticuloInsumoFacade;
import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.ArticuloInsumoService;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.repositories.ArticuloInsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ArticuloInsumoFacadeImp extends BaseFacadeImp<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoCreateDto, Long> implements ArticuloInsumoFacade {
    public ArticuloInsumoFacadeImp(BaseService<ArticuloInsumo, Long> baseService, BaseMapper<ArticuloInsumo, ArticuloInsumoDto,ArticuloInsumoCreateDto,ArticuloInsumoCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    private ArticuloInsumoService articuloInsumoService;

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    public ResponseEntity<ArticuloInsumoDto> createCompleto(ArticuloInsumoCreateDto insumoCreateDto){
        ArticuloInsumoDto insumodto = articuloInsumoService.createCompleto(insumoCreateDto);
        return ResponseEntity.ok(insumodto);
    }

    public List<ArticuloInsumoDto> getArticulosNoParaElaborar() {
        List<ArticuloInsumo> articulos = articuloInsumoRepository.findByEsParaElaborarFalse();
        return baseMapper.toDTOsList(articulos);
    }
}